package com.skybot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CMDExecutor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CMDExecutor.class);
	private static final ExecutorService EXECUTOR_SERVICE = Executors.newWorkStealingPool(64);

	/**
	 * 执行CMD命令并返回结果
	 *
	 * @param params CMD命令参数
	 * @return 执行结果对象，包含退出码、标准输出和错误输出
	 */
	public static CommandResult exec(String... params)
	{
		List<String> command = new ArrayList<>();
		command.add("cmd");
		command.add("/c");
		command.addAll(Arrays.asList(params));
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Process process = null;
		try
		{
			process = processBuilder.start();
			Process finalProcess = process;
			Future<String> outputFuture = EXECUTOR_SERVICE.submit(() -> readStream(finalProcess.getInputStream(), "UTF-8"));
			Future<String> errorFuture = EXECUTOR_SERVICE.submit(() -> readStream(finalProcess.getErrorStream(), "UTF-8"));
			int exitCode = process.waitFor();
			String output = outputFuture.get();
			String error = errorFuture.get();

			LOGGER.info("命令执行完成，退出码: {}, 输出: {}, 错误: {}",
				exitCode, output, error);

			return new CommandResult(exitCode, output, error);
		}
		catch (IOException e)
		{
			LOGGER.error("执行CMD命令时发生IO异常: {}", command, e);
			return new CommandResult(-1, "", "IO异常: " + e.getMessage());
		}
		catch (InterruptedException e)
		{
			LOGGER.error("执行CMD命令时线程被中断: {}", command, e);
			Thread.currentThread().interrupt(); // 恢复中断状态
			return new CommandResult(-2, "", "线程被中断: " + e.getMessage());
		}
		catch (ExecutionException e)
		{
			LOGGER.error("处理命令输出时发生异常: {}", command, e);
			return new CommandResult(-3, "", "处理输出异常: " + e.getMessage());
		}
		finally
		{
			if (process != null)
			{
				process.destroy(); // 确保进程被销毁
			}
		}
	}

	/**
	 * 读取输入流并返回字符串
	 *
	 * @param inputStream 输入流
	 * @param charset     字符集
	 * @return 读取到的字符串
	 */
	private static String readStream(InputStream inputStream, String charset)
	{
		try (
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
		)
		{

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = bufferedInputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, bytesRead);
			}
			return outputStream.toString(charset);
		}
		catch (IOException e)
		{
			LOGGER.error("读取流时发生异常", e);
			return "读取流异常: " + e.getMessage();
		}
	}

	/**
	 * 命令执行结果封装类
	 */
	public static class CommandResult
	{
		private final int exitCode;
		private final String output;
		private final String error;

		public CommandResult(int exitCode, String output, String error)
		{
			this.exitCode = exitCode;
			this.output = output;
			this.error = error;
		}

		public int getExitCode()
		{
			return exitCode;
		}

		public String getOutput()
		{
			return output;
		}

		public String getError()
		{
			return error;
		}

		@Override
		public String toString()
		{
			return "CommandResult{" +
				"exitCode=" + exitCode +
				", output='" + output + '\'' +
				", error='" + error + '\'' +
				'}';
		}
	}

	// 示例用法
	public static void main(String[] args)
	{
		CommandResult result = CMDExecutor.exec("dir");
		System.out.println("退出码: " + result.getExitCode());
		System.out.println("标准输出: " + result.getOutput());
		System.out.println("错误输出: " + result.getError());
	}
}

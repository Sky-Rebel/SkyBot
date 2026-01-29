package com.skybot.bot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

public class CMDExecutor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CMDExecutor.class);

	private static final int TASK_SUBMITTED = 0;

	private static final int ERROR_FILE_NOT_EXIST = -1;

	private static final int ERROR_PROCESS_START_EXCEPTION = -2;

	private static final int ERROR_PROCESS_INTERRUPTED_EXCEPTION = -3;

	private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

	public static int execute(String name, boolean removeEmpty,  String workDir, String command, String... args)
	{
		EXECUTOR_SERVICE.submit(()->
		{
			Process process = null;
			BufferedReader bufferedReader = null;
			try
			{
				File file = new File(workDir);
				if (!file.exists())
				{
					LOGGER.error("CMD命令工作目录无存！");
					return ERROR_FILE_NOT_EXIST;
				}
				List<String> arrayList = new ArrayList<>();
				arrayList.add("cmd.exe");
				arrayList.add("/c");
				if (command != null) arrayList.add(command);
				if (args != null) arrayList.addAll(Arrays.stream(args).toList());
				ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
				processBuilder.directory(file);
				processBuilder.redirectErrorStream(true);
				process = processBuilder.start();
				InputStream inputStream = process.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
				bufferedReader = new BufferedReader(inputStreamReader);
				String line;
				while ((line = bufferedReader.readLine()) != null)
				{
					if (removeEmpty)
					{
						if (line.isEmpty()) continue;
						line = line.replaceAll("\n", "");
						line = line.trim();
					}
					System.out.println(name == null ? "" : (name + " -> ") + line);
				}
				return process.waitFor();
			}
			catch (IOException e)
			{
				LOGGER.error("CMD命令进程启动失败！", e);
				return ERROR_PROCESS_START_EXCEPTION;
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				LOGGER.error("CMD命令进程异常中断！");
				return ERROR_PROCESS_INTERRUPTED_EXCEPTION;
			}
			finally
			{
				if (bufferedReader != null)
				{
					try
					{
						bufferedReader.close();
					}
					catch (IOException e)
					{
						LOGGER.error("CMD命令进程字符输入流关闭失败！");
					}
				}
				if (process != null) process.destroy();
			}
		});
		return TASK_SUBMITTED;
	}

	public static void startBat(String name, boolean removeEmpty, String workDir, String... args)
	{
		execute(name, removeEmpty, workDir, "start", args);
	}

	public static void shutdownExecutor()
	{
		EXECUTOR_SERVICE.shutdown();
		try
		{
			if (!EXECUTOR_SERVICE.awaitTermination(5, TimeUnit.SECONDS))
			{
				EXECUTOR_SERVICE.shutdownNow();
			}
		}
		catch (InterruptedException e)
		{
			EXECUTOR_SERVICE.shutdownNow();
		}
	}
}
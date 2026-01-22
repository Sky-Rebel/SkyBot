package com.skybot.bot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CMDExecutor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CMDExecutor.class);

	private static final Map<String, Process> STRING_PROCESS_MAP = new HashMap<>();

	public static final int ERROR_FILE_NOT_EXIST = -1;

	public static final int ERROR_PROCESS_START_EXCEPTION = -2;

	public static final int ERROR_PROCESS_INTERRUPTED_EXCEPTION = -3;

	public static int execute(String name, boolean removeEmpty,  String workDir, String command, String... args)
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
			LOGGER.error("CMD命令进程异常中断！");
			Thread.currentThread().interrupt();
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
	}

	public static void startBat(String name, boolean removeEmpty, String workDir, String batFile, String... args)
	{
		execute(name, removeEmpty, workDir, batFile, args);
	}
}
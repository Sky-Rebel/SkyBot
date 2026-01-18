package com.skybot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class CMDExecutor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CMDExecutor.class);

	public static void runBat(String workDir, String batFile, String arg)
	{
		File file = new File(workDir, batFile);
		try
		{
			if (!file.exists())
			{
				LOGGER.error("无法找到Bat文件 -> {}", file.getAbsolutePath());
				return;
			}
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start",  file.getAbsolutePath(), arg);
			processBuilder.directory(file.getParentFile());
			processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
			processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
			processBuilder.start();
			LOGGER.info("Bat文件启动成功 -> {}", file.getAbsolutePath());
		}
		catch (IOException e)
		{
			LOGGER.error("Bat文件启动失败 -> {}", file.getAbsolutePath(), e);
		}
	}
}
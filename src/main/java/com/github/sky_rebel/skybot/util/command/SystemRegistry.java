package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;

import java.util.LinkedList;
import java.util.List;

public class SystemRegistry
{
	private static final Logger LOGGER = SkybotLogger.getLogger(SystemRegistry.class);

	private static List<SystemInfo> systemInfoList = new LinkedList<>();

	static
	{
		SystemInfo systemInfo = new SystemInfo();
		systemInfo.name = "霖全能机器人";
		systemInfo.command = "菜单";
		systemInfo.description = "欢迎使用本公益机器人！";
		systemInfoList.add(systemInfo);
	}

	public static void register(SystemInfo systemInfo)
	{
		if (isRegistered(systemInfo))
		{
			LOGGER.warn("系统既已注册 -> {}", systemInfo.name);
			return;
		}
		systemInfoList.getFirst().systemInfoList.add(systemInfo);
	}

	public static List<SystemInfo> getSystemInfoList()
	{
		return systemInfoList;
	}

	private static boolean isRegistered(SystemInfo systemInfo)
	{
		return systemInfoList.getFirst().systemInfoList.stream().anyMatch(systemInfo1 -> systemInfo1.name.equals(systemInfo.name));
	}
}
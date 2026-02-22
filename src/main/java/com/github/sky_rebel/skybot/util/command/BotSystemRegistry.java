package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.util.data.SingleValueManage;
import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;

import java.util.LinkedList;
import java.util.List;

public class BotSystemRegistry
{
	private static final Logger LOGGER = SkybotLogger.getLogger(BotSystemRegistry.class);

	private static List<BotSystemInfo> botSystemInfoList = new LinkedList<>();

	static
	{
		BotSystemInfo botSystemInfo = new BotSystemInfo();
		botSystemInfo.id = 1000;
		botSystemInfo.name = SingleValueManage.getString("base_system_name", "Lin");
		botSystemInfo.command = SingleValueManage.getString("base_system_command", "enum");
		botSystemInfo.description = "";
		botSystemInfoList.add(botSystemInfo);
	}

	public static void register(BotSystemInfo botSystemInfo)
	{
		if (isRegistered(botSystemInfo))
		{
			LOGGER.warn("系统既已注册 -> {}", botSystemInfo.name);
			return;
		}
		botSystemInfoList.getFirst().botSystemInfoList.add(botSystemInfo);
	}

	public static List<BotSystemInfo> getSystemInfoList()
	{
		return botSystemInfoList;
	}

	private static boolean isRegistered(BotSystemInfo botSystemInfo)
	{
		return botSystemInfoList.getFirst().botSystemInfoList.stream().anyMatch(botSystemInfo1 -> botSystemInfo1.name.equals(botSystemInfo.name));
	}
}
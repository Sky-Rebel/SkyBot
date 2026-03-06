package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.util.data.SingleValueManage;
import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BotSystemRegistry
{
	private static final Logger LOGGER = SkybotLogger.getLogger(BotSystemRegistry.class);

	private static final List<BotSystemInfo> groupSystemInfoList = new ArrayList<>();

	private static final List<BotSystemInfo> privateSystemInfoList = new ArrayList<>();

	private static final String DEF_BASE_SYSTEM_NAME = "base_system_name";

	private static final String DEF_BASE_SYSTEM_COMMAND = "base_system_command";

	private static final String DEF_BASE_SYSTEM_DESCRIPTION = "base_system_description";

	private static final String BASE_SYSTEM_NAME = SingleValueManage.getString("base_system_name", DEF_BASE_SYSTEM_NAME);

	private static final String BASE_SYSTEM_COMMAND = SingleValueManage.getString("base_system_command", DEF_BASE_SYSTEM_COMMAND);

	private static final String BASE_SYSTEM_DESCRIPTION = SingleValueManage.getString("base_system_description", DEF_BASE_SYSTEM_DESCRIPTION);

	public static MessageArrayEdit sendGroupSystemEnum(OB11GroupMessageEvent ob11GroupMessageEvent, BotSystemInfo botSystemInfo)
	{
		MessageArrayEdit messageArrayEdit = new MessageArrayEdit(ob11GroupMessageEvent);
		messageArrayEdit.addTextAndLine(botSystemInfo.getName()).addTab(3);
		List<BotFunctionInfo> functionInfoList = botSystemInfo.getBotFunctionInfoList();
		AtomicInteger atomicInteger = new AtomicInteger();
		StringBuilder commandList = new StringBuilder();
		if (!functionInfoList.isEmpty())
		{
			functionInfoList.forEach(botFunctionInfo ->
			{
				int times = atomicInteger.getAndIncrement();
				commandList.append("[" + ++times + "] " + botFunctionInfo.getName() + "\n");
			});
		}
		else commandList.append("该系统暂未注册功能");
		messageArrayEdit.addTextAndLine(commandList.toString().trim()).addTab(2);
		messageArrayEdit.addTextAndLine(botSystemInfo.getDescription()).addTab(3);
		messageArrayEdit.addTime().sendMessage();
		return messageArrayEdit;
	}

	public static void registerGroupSystem(BotSystemInfo botSystemInfo)
	{
		if (!isRegisteredGroupSystem(botSystemInfo))
		{
			groupSystemInfoList.add(botSystemInfo);
		}
	}

	public static void registerPrivateSystem(BotSystemInfo botSystemInfo)
	{
		if (!isRegisteredPrivateSystem(botSystemInfo))
		{
			privateSystemInfoList.add(botSystemInfo);
		}
	}

	public static List<BotSystemInfo> getGroupSystemInfoList()
	{
		return groupSystemInfoList;
	}

	public static List<BotSystemInfo> getPrivateSystemInfoList()
	{
		return privateSystemInfoList;
	}

	public static String getBaseSystemName()
	{
		return BASE_SYSTEM_NAME;
	}

	public static String getBaseSystemCommand()
	{
		return BASE_SYSTEM_COMMAND;
	}

	public static String getBaseSystemDescription()
	{
		return BASE_SYSTEM_DESCRIPTION;
	}

	private static boolean isRegisteredGroupSystem(BotSystemInfo botSystemInfo)
	{
		return groupSystemInfoList.stream().anyMatch(botSystemInfo1 -> botSystemInfo1.getId() == botSystemInfo.getId());
	}

	private static boolean isRegisteredPrivateSystem(BotSystemInfo botSystemInfo)
	{
		return privateSystemInfoList.stream().anyMatch(botSystemInfo1 -> botSystemInfo1.getId() == botSystemInfo.getId());
	}
}
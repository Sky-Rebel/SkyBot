package com.github.sky_rebel.skybot.util.command;

import java.util.LinkedList;
import java.util.List;

public class BotFunctionRegistry
{
	private static List<BotFunctionInfo> botFunctionInfoList = new LinkedList<>();

	public static void registerCommand(BotFunctionInfo botFunctionInfo)
	{
		botFunctionInfoList.add(botFunctionInfo);
	}
}
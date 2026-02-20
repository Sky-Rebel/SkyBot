package com.github.sky_rebel.skybot.util.command;

import java.util.LinkedList;
import java.util.List;

public class FunctionRegistry
{
	private static List<FunctionInfo> functionInfoList = new LinkedList<>();

	public static void registerCommand(FunctionInfo functionInfo)
	{
		functionInfoList.add(functionInfo);
	}
}
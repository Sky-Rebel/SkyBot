package com.github.sky_rebel.skybot.util.command;

import java.util.ArrayList;
import java.util.List;

public class GroupFunctionRegistry
{
	private static List<FunctionInfo> functionInfoList = new ArrayList<>();

	public static void registerCommand(FunctionInfo functionInfo)
	{
		functionInfoList.add(functionInfo);
	}
}

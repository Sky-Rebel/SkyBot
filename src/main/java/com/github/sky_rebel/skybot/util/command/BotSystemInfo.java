package com.github.sky_rebel.skybot.util.command;

import java.util.LinkedList;
import java.util.List;

public class BotSystemInfo
{
	public int id;

	public String name;

	public String command;

	public String description;

	public List<BotSystemInfo> botSystemInfoList = new LinkedList<>();

	public List<BotFunctionInfo> botFunctionInfoList = new LinkedList<>();
}
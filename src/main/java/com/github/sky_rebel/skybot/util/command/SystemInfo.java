package com.github.sky_rebel.skybot.util.command;

import java.util.LinkedList;
import java.util.List;

public class SystemInfo
{
	public String name;

	public String command;

	public String description;

	public List<SystemInfo> systemInfoList = new LinkedList<>();

	public List<FunctionInfo> functionInfoList = new LinkedList<>();
}

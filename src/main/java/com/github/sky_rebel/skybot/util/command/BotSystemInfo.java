package com.github.sky_rebel.skybot.util.command;

import java.util.ArrayList;
import java.util.List;

public class BotSystemInfo
{
	private int id;

	private String name;

	private String command;

	private String description;

	private List<BotFunctionInfo> botFunctionInfoList = new ArrayList<>();

	public int getFunctionCount()
	{
		return botFunctionInfoList.size();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		setCommand(name);
	}

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<BotFunctionInfo> getBotFunctionInfoList()
	{
		return botFunctionInfoList;
	}

	public void addBotFunctionInfo(BotFunctionInfo botFunctionInfo)
	{
		this.botFunctionInfoList.add(botFunctionInfo);
	}
}
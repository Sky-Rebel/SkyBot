package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotFunctionInfo
{
	private String name;

	private String command;

	private Pattern pattern;

	private Matcher matcher;

	private boolean needParam;

	private Consumer<OB11GroupMessageEvent> consumer;

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
		this.pattern = Pattern.compile(command);
	}

	public Consumer<OB11GroupMessageEvent> getConsumer()
	{
		return consumer;
	}

	public void setConsumer(Consumer<OB11GroupMessageEvent> consumer)
	{
		this.consumer = consumer;
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder("BotFunctionInfo");
		stringBuilder.append("{");
		stringBuilder.append("command").append("=").append(getCommand());
		stringBuilder.append(",").append("consumer").append("=").append(getConsumer());
		stringBuilder.append('}');
		return stringBuilder.toString();
	}

	public boolean isNeedParam()
	{
		return needParam;
	}

	public void setNeedParam(boolean needParam)
	{
		this.needParam = needParam;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Pattern getPattern()
	{
		return pattern;
	}

	public void setPattern(Pattern pattern)
	{
		this.pattern = pattern;
	}

	public Matcher getMatcher()
	{
		return matcher;
	}

	public void setMatcher(Matcher matcher)
	{
		this.matcher = matcher;
	}
}
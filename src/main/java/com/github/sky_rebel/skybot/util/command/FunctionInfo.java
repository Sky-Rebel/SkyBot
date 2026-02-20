package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;

import java.util.function.Consumer;

public class FunctionInfo
{
	public String command;

	public Consumer<OB11GroupMessageEvent> consumer;

	@Override
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("FunctionInfo");
		stringBuffer.append("{");
		stringBuffer.append("command").append("=").append(command);
		stringBuffer.append(",").append("consumer").append("=").append(consumer);
		stringBuffer.append('}');
		return stringBuffer.toString();
	}
}
package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;

import java.util.List;
import java.util.function.Consumer;

public class FunctionInfo
{
	public String command;

	public Consumer<OB11GroupMessageEvent> consumer;
}
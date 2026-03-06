package com.github.sky_rebel.skybot.event.handling.handler;

import com.github.sky_rebel.skybot.bot.Bot;
import com.github.sky_rebel.skybot.event.handling.listener.OB11GroupMessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11MessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11PrivateMessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.manage.OB11EventListenerManage;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;
import com.github.sky_rebel.script.ScriptRunning;
import com.github.sky_rebel.skybot.util.command.BotFunctionInfo;
import com.github.sky_rebel.skybot.util.command.BotSystemInfo;
import com.github.sky_rebel.skybot.util.command.BotSystemRegistry;

import java.util.List;
import java.util.regex.Matcher;

public class OB11MessageEventHandler
{
	public static void onGroupMessage(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		ScriptRunning.run(bot, ob11GroupMessageEvent);
		List<OB11MessageEventListener> ob11MessageEventListenerList = OB11EventListenerManage.getMessageEventListenerList();
		ob11MessageEventListenerList.forEach(ob11MessageEventListener -> ob11MessageEventListener.onGroupMessage(bot, ob11GroupMessageEvent));
		List<OB11GroupMessageEventListener> ob11GroupMessageEventListenerList = OB11EventListenerManage.getGroupMessageEventListenerList();
		ob11GroupMessageEventListenerList.forEach(ob11GroupMessageEventListener -> ob11GroupMessageEventListener.onGroupMessage(bot, ob11GroupMessageEvent));
		for (BotSystemInfo botSystemInfo : BotSystemRegistry.getGroupSystemInfoList())
		{
			if (botSystemInfo.getCommand().equals(ob11GroupMessageEvent.getRawMessage()))
			{
				BotSystemRegistry.sendGroupSystemEnum(ob11GroupMessageEvent, botSystemInfo);
			}
			for (BotFunctionInfo botFunctionInfo : botSystemInfo.getBotFunctionInfoList())
			{
				Matcher matcher = botFunctionInfo.getPattern().matcher(ob11GroupMessageEvent.getRawMessage());
				if (matcher.find())
				{
					botFunctionInfo.setMatcher(matcher);
					botFunctionInfo.getConsumer().accept(ob11GroupMessageEvent);
				}
			}
		}
	}

	public static void onPrivateMessage(Bot bot, OB11PrivateMessageEvent ob11PrivateMessageEvent)
	{
		List<OB11MessageEventListener> ob11MessageEventListenerList = OB11EventListenerManage.getMessageEventListenerList();
		ob11MessageEventListenerList.forEach(ob11MessageEventListener -> ob11MessageEventListener.onPrivateMessage(bot, ob11PrivateMessageEvent));
		List<OB11PrivateMessageEventListener> ob11PrivateMessageEventListenerList = OB11EventListenerManage.getPrivateMessageEventListenerList();
		ob11PrivateMessageEventListenerList.forEach(ob11PrivateMessageEventListener -> ob11PrivateMessageEventListener.onPrivateMessage(bot, ob11PrivateMessageEvent));
	}
}
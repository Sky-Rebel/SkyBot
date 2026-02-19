package com.github.sky_rebel.skybot.event.handling.handler;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.listener.OB11MessageSentEventListener;
import com.github.sky_rebel.skybot.event.handling.manage.OB11MessageSentEventListenerManage;
import com.github.sky_rebel.skybot.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.skybot.event.message_sent.OB11PrivateMessageSentEvent;

import java.util.List;

public class OB11MessageSentEventHandler
{
	public static void onGroupSentMessage(Bot bot, OB11GroupMessageSentEvent ob11GroupMessageSentEvent)
	{
		List<OB11MessageSentEventListener> ob11MessageSentEventListenerList = OB11MessageSentEventListenerManage.getMessageSentEventListenerList();
		ob11MessageSentEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onGroupSentMessage(bot, ob11GroupMessageSentEvent));
	}

	public static void onPrivateSentMessage(Bot bot, OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent)
	{
		List<OB11MessageSentEventListener> ob11MessageSentEventListenerList = OB11MessageSentEventListenerManage.getMessageSentEventListenerList();
		ob11MessageSentEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onPrivateSentMessage(bot, ob11PrivateMessageSentEvent));
	}
}

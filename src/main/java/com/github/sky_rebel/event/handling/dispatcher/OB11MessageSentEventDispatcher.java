package com.github.sky_rebel.event.handling.dispatcher;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.event.handling.listener.OB11MessageSentEventListener;
import com.github.sky_rebel.event.handling.manage.OB11MessageSentEventListenerManage;
import com.github.sky_rebel.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.event.message_sent.OB11PrivateMessageSentEvent;

import java.util.List;

public class OB11MessageSentEventDispatcher
{
	/**
	 * 通知既已注册的群聊自发消息事件监听器
	 * @param ob11GroupMessageSentEvent 群聊自发消息事件数据类
	 */
	public static void onGroupSentMessage(Bot bot, OB11GroupMessageSentEvent ob11GroupMessageSentEvent)
	{
		List<OB11MessageSentEventListener> ob11MessageSentEventListenerList = OB11MessageSentEventListenerManage.getOb11MessageSentEventListenerList();
		ob11MessageSentEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onGroupSentMessage(bot, ob11GroupMessageSentEvent));
	}

	/**
	 * 通知既已注册的私聊自发消息事件监听器
	 * @param ob11PrivateMessageSentEvent 私聊自发消息事件数据类
	 */
	public static void onPrivateSentMessage(Bot bot, OB11PrivateMessageSentEvent ob11PrivateMessageSentEvent)
	{
		List<OB11MessageSentEventListener> ob11MessageSentEventListenerList = OB11MessageSentEventListenerManage.getOb11MessageSentEventListenerList();
		ob11MessageSentEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onPrivateSentMessage(bot, ob11PrivateMessageSentEvent));
	}
}

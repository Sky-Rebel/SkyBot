package com.github.sky_rebel.bot.event.handling.manage;

import com.github.sky_rebel.bot.event.handling.listener.OB11MessageSentEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageSentEventListenerManage
{
	private static final List<OB11MessageSentEventListener> OB11_MESSAGE_SENT_EVENT_LISTENER_LIST = new ArrayList<>();

	/**
	 * 注册自发消息事件监听器
	 * @param ob11MessageSentEventListener 自发消息事件监听器列表
	 */
	public static void registerListener(OB11MessageSentEventListener ob11MessageSentEventListener)
	{
		if (ob11MessageSentEventListener != null && !OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.contains(ob11MessageSentEventListener))
		{
			OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.add(ob11MessageSentEventListener);
		}
	}

	/**
	 * 移除自发消息事件监听器
	 * @param ob11MessageSentEventListener 自发消息事件监听器列表
	 */
	public static void unregisterListener(OB11MessageSentEventListener ob11MessageSentEventListener)
	{
		if (ob11MessageSentEventListener != null && OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.contains(ob11MessageSentEventListener))
		{
			OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.add(ob11MessageSentEventListener);
		}
	}

	/**
	 * 获取自发消息事件监听器列表
	 * @return 自发消息事件监听器列表
	 */
	public static List<OB11MessageSentEventListener> getOb11MessageSentEventListenerList()
	{
		return OB11_MESSAGE_SENT_EVENT_LISTENER_LIST;
	}
}

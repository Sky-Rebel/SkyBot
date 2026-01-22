package com.skybot.bot.event.handling.manage;

import com.skybot.bot.event.handling.listener.OB11RequestEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11RequestEventListenerManage
{
	private static final List<OB11RequestEventListener> OB11_REQUEST_EVENT_LISTENER_LIST = new ArrayList<>();

	/**
	 * 请求事件监听器
	 * @param ob11RequestEventListener 请求事件监听器实例
	 */
	public static void registerListener(OB11RequestEventListener ob11RequestEventListener)
	{
		if (ob11RequestEventListener != null && !OB11_REQUEST_EVENT_LISTENER_LIST.contains(ob11RequestEventListener))
		{
			OB11_REQUEST_EVENT_LISTENER_LIST.add(ob11RequestEventListener);
		}
	}

	/**
	 * 请求事件监听器
	 * @param ob11RequestEventListener 请求事件监听器实例
	 */
	public static void unregisterListener(OB11RequestEventListener ob11RequestEventListener)
	{
		if (ob11RequestEventListener != null && OB11_REQUEST_EVENT_LISTENER_LIST.contains(ob11RequestEventListener))
		{
			OB11_REQUEST_EVENT_LISTENER_LIST.add(ob11RequestEventListener);
		}
	}

	/**
	 * 获取请求事件监听器列表
	 * @return 请求事件监听器列表
	 */
	public static List<OB11RequestEventListener> getOb11RequestEventListenerList()
	{
		return OB11_REQUEST_EVENT_LISTENER_LIST;
	}
}
package com.skybot.bot.event.handling.manage;

import com.skybot.bot.event.handling.listener.OB11MetaEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11MetaEventListenerManage
{
	private static final List<OB11MetaEventListener> OB11_META_EVENT_LISTENER_LIST = new ArrayList<>();

	/**
	 * 元事件监听器
	 * @param ob11MetaEventListener 元事件监听器实例
	 */
	public static void registerListener(OB11MetaEventListener ob11MetaEventListener)
	{
		if (ob11MetaEventListener != null && !OB11_META_EVENT_LISTENER_LIST.contains(ob11MetaEventListener))
		{
			OB11_META_EVENT_LISTENER_LIST.add(ob11MetaEventListener);
		}
	}

	/**
	 * 元事件监听器
	 * @param ob11MetaEventListener 元事件监听器实例
	 */
	public static void unregisterListener(OB11MetaEventListener ob11MetaEventListener)
	{
		if (ob11MetaEventListener != null && OB11_META_EVENT_LISTENER_LIST.contains(ob11MetaEventListener))
		{
			OB11_META_EVENT_LISTENER_LIST.remove(ob11MetaEventListener);
		}
	}

	/**
	 * 获取元事件监听器列表
	 * @return 元事件监听器列表
	 */
	public static List<OB11MetaEventListener> getMetaEventListenerList()
	{
		return OB11_META_EVENT_LISTENER_LIST;
	}
}
package com.skybot.bot.event.handling.manage;

import com.skybot.bot.event.handling.listener.OB11NoticeEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11NoticeEventListenerManage
{
	private static final List<OB11NoticeEventListener> OB11_NOTICE_EVENT_LISTENER_LIST = new ArrayList<>();

	/**
	 * 注册通知事件监听器
	 * @param ob11NoticeEventListener 通知事件监听器实例
	 */
	public static void registerListener(OB11NoticeEventListener ob11NoticeEventListener)
	{
		if (ob11NoticeEventListener != null && !OB11_NOTICE_EVENT_LISTENER_LIST.contains(ob11NoticeEventListener))
		{
			OB11_NOTICE_EVENT_LISTENER_LIST.add(ob11NoticeEventListener);
		}
	}

	/**
	 * 移除通知事件监听器
	 * @param ob11NoticeEventListener 通知事件监听器实例
	 */
	public static void unregisterListener(OB11NoticeEventListener ob11NoticeEventListener)
	{
		if (ob11NoticeEventListener != null && OB11_NOTICE_EVENT_LISTENER_LIST.contains(ob11NoticeEventListener))
		{
			OB11_NOTICE_EVENT_LISTENER_LIST.remove(ob11NoticeEventListener);
		}
	}

	/**
	 * 获取通知事件监听器列表
	 * @return 通知事件监听器列表
	 */
	public static List<OB11NoticeEventListener> getOb11NoticeEventListenerList()
	{
		return OB11_NOTICE_EVENT_LISTENER_LIST;
	}
}
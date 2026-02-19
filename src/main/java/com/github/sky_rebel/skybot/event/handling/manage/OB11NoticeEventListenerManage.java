package com.github.sky_rebel.skybot.event.handling.manage;

import com.github.sky_rebel.skybot.event.handling.listener.OB11NoticeEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11NoticeEventListenerManage
{
	private static final List<OB11NoticeEventListener> OB11_NOTICE_EVENT_LISTENER_LIST = new ArrayList<>();

	public static void registerListener(OB11NoticeEventListener ob11NoticeEventListener)
	{
		if (ob11NoticeEventListener != null && !OB11_NOTICE_EVENT_LISTENER_LIST.contains(ob11NoticeEventListener))
		{
			OB11_NOTICE_EVENT_LISTENER_LIST.add(ob11NoticeEventListener);
		}
	}

	public static void unregisterListener(OB11NoticeEventListener ob11NoticeEventListener)
	{
		if (ob11NoticeEventListener != null && OB11_NOTICE_EVENT_LISTENER_LIST.contains(ob11NoticeEventListener))
		{
			OB11_NOTICE_EVENT_LISTENER_LIST.remove(ob11NoticeEventListener);
		}
	}

	public static List<OB11NoticeEventListener> getOb11NoticeEventListenerList()
	{
		return OB11_NOTICE_EVENT_LISTENER_LIST;
	}
}
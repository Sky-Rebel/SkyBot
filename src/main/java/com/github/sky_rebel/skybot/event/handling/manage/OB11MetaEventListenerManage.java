package com.github.sky_rebel.skybot.event.handling.manage;

import com.github.sky_rebel.skybot.event.handling.listener.OB11MetaEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11MetaEventListenerManage
{
	private static final List<OB11MetaEventListener> OB11_META_EVENT_LISTENER_LIST = new ArrayList<>();

	public static void registerListener(OB11MetaEventListener ob11MetaEventListener)
	{
		if (ob11MetaEventListener != null && !OB11_META_EVENT_LISTENER_LIST.contains(ob11MetaEventListener))
		{
			OB11_META_EVENT_LISTENER_LIST.add(ob11MetaEventListener);
		}
	}

	public static void unregisterListener(OB11MetaEventListener ob11MetaEventListener)
	{
		if (ob11MetaEventListener != null && OB11_META_EVENT_LISTENER_LIST.contains(ob11MetaEventListener))
		{
			OB11_META_EVENT_LISTENER_LIST.remove(ob11MetaEventListener);
		}
	}

	public static List<OB11MetaEventListener> getMetaEventListenerList()
	{
		return OB11_META_EVENT_LISTENER_LIST;
	}
}
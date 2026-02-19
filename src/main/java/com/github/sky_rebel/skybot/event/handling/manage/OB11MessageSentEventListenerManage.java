package com.github.sky_rebel.skybot.event.handling.manage;

import com.github.sky_rebel.skybot.event.handling.listener.OB11MessageSentEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageSentEventListenerManage
{
	private static final List<OB11MessageSentEventListener> OB11_MESSAGE_SENT_EVENT_LISTENER_LIST = new ArrayList<>();

	public static void registerListener(OB11MessageSentEventListener ob11MessageSentEventListener)
	{
		if (ob11MessageSentEventListener != null && !OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.contains(ob11MessageSentEventListener))
		{
			OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.add(ob11MessageSentEventListener);
		}
	}

	public static void unregisterListener(OB11MessageSentEventListener ob11MessageSentEventListener)
	{
		if (ob11MessageSentEventListener != null && OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.contains(ob11MessageSentEventListener))
		{
			OB11_MESSAGE_SENT_EVENT_LISTENER_LIST.add(ob11MessageSentEventListener);
		}
	}

	public static List<OB11MessageSentEventListener> getMessageSentEventListenerList()
	{
		return OB11_MESSAGE_SENT_EVENT_LISTENER_LIST;
	}
}

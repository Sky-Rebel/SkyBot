package com.github.sky_rebel.skybot.event.handling.manage;

import com.github.sky_rebel.skybot.event.handling.listener.message.OB11GroupMessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.message.OB11MessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.message.OB11PrivateMessageEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageEventManage
{
	private static final List<OB11MessageEventListener> OB11_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11GroupMessageEventListener> OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11PrivateMessageEventListener> OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	public static void registerListener(OB11MessageEventListener ob11MessageEventListener)
	{
		if (ob11MessageEventListener != null && !OB11_MESSAGE_EVENT_LISTENER_LIST.contains(ob11MessageEventListener))
		{
			OB11_MESSAGE_EVENT_LISTENER_LIST.add(ob11MessageEventListener);
		}
	}

	public static void unregisterListener(OB11MessageEventListener ob11MessageEventListener)
	{
		if (ob11MessageEventListener != null && OB11_MESSAGE_EVENT_LISTENER_LIST.contains(ob11MessageEventListener))
		{
			OB11_MESSAGE_EVENT_LISTENER_LIST.remove(ob11MessageEventListener);
		}
	}

	public static void registerListener(OB11GroupMessageEventListener ob11GroupMessageEventListener)
	{
		if (ob11GroupMessageEventListener != null && !OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.contains(ob11GroupMessageEventListener))
		{
			OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.add(ob11GroupMessageEventListener);
		}
	}

	public static void unregisterListener(OB11GroupMessageEventListener ob11GroupMessageEventListener)
	{
		if (ob11GroupMessageEventListener != null && OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.contains(ob11GroupMessageEventListener))
		{
			OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.remove(ob11GroupMessageEventListener);
		}
	}

	public static void registerListener(OB11PrivateMessageEventListener ob11PrivateMessageEventListener)
	{
		if (ob11PrivateMessageEventListener != null && !OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.contains(ob11PrivateMessageEventListener))
		{
			OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.add(ob11PrivateMessageEventListener);
		}
	}

	public static void unregisterListener(OB11PrivateMessageEventListener ob11PrivateMessageEventListener)
	{
		if (ob11PrivateMessageEventListener != null && OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.contains(ob11PrivateMessageEventListener))
		{
			OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.remove(ob11PrivateMessageEventListener);
		}
	}

	public static List<OB11MessageEventListener> getMessageEventListenerList()
	{
		return OB11_MESSAGE_EVENT_LISTENER_LIST;
	}

	public static List<OB11GroupMessageEventListener> getGroupMessageEventListenerList()
	{
		return OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST;
	}

	public static List<OB11PrivateMessageEventListener> getPrivateMessageEventListenerList()
	{
		return OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST;
	}
}
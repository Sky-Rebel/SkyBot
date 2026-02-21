package com.github.sky_rebel.skybot.event.handling.manage;

import com.github.sky_rebel.skybot.event.handling.listener.OB11MessageSentEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11MetaEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11NoticeEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11RequestEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.message.OB11GroupMessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.message.OB11MessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.message.OB11PrivateMessageEventListener;
import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OB11EventListenerManage
{
	private static final Logger LOGGER = SkybotLogger.getLogger(OB11EventListenerManage.class);

	private static final Map<Class<?>, List<?>> OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP = new HashMap<>();

	private static final List<OB11MetaEventListener> OB11_META_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11NoticeEventListener> OB11_NOTICE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11MessageEventListener> OB11_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11RequestEventListener> OB11_REQUEST_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11MessageSentEventListener> OB11_MESSAGE_SENT_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11GroupMessageEventListener> OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11PrivateMessageEventListener> OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	static
	{
		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11MetaEventListener.class, OB11_META_EVENT_LISTENER_LIST);

		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11NoticeEventListener.class, OB11_NOTICE_EVENT_LISTENER_LIST);

		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11MessageEventListener.class, OB11_MESSAGE_EVENT_LISTENER_LIST);

		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11RequestEventListener.class, OB11_REQUEST_EVENT_LISTENER_LIST);

		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11MessageSentEventListener.class, OB11_MESSAGE_SENT_EVENT_LISTENER_LIST);

		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11GroupMessageEventListener.class, OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST);

		OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.put(OB11PrivateMessageEventListener.class, OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST);
	}

	public static void registerListener(OB11MetaEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static void registerListener(OB11NoticeEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static void registerListener(OB11RequestEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static void registerListener(OB11MessageEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static void registerListener(OB11MessageSentEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static void registerListener(OB11GroupMessageEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static void registerListener(OB11PrivateMessageEventListener listener)
	{
		registerListener((Object) listener);
	}

	public static List<OB11MetaEventListener> getMetaEventListenerList()
	{
		return OB11_META_EVENT_LISTENER_LIST;
	}

	public static List<OB11NoticeEventListener> getNoticeEventListenerList()
	{
		return OB11_NOTICE_EVENT_LISTENER_LIST;
	}

	public static List<OB11RequestEventListener> getRequestEventListenerList()
	{
		return OB11_REQUEST_EVENT_LISTENER_LIST;
	}

	public static List<OB11MessageEventListener> getMessageEventListenerList()
	{
		return OB11_MESSAGE_EVENT_LISTENER_LIST;
	}

	public static List<OB11MessageSentEventListener> getMessageSentEventListenerList()
	{
		return OB11_MESSAGE_SENT_EVENT_LISTENER_LIST;
	}

	public static List<OB11GroupMessageEventListener> getGroupMessageEventListenerList()
	{
		return OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST;
	}

	public static List<OB11PrivateMessageEventListener> getPrivateMessageEventListenerList()
	{
		return OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST;
	}

	private static <T> void registerListener(T listener, Class<T> listenerType, List<T> listenerList)
	{
		if (listenerList.contains(listener))
		{
			LOGGER.error("类型为({})的监听器({})既已注册 -> {}", listenerType.getSimpleName(), listener.getClass().getSimpleName());
			return;
		}
		listenerList.add(listener);
	}

	@SuppressWarnings("unchecked")
	private static void registerListener(Object listener)
	{
		try
		{
			if (listener == null)
			{
				LOGGER.error("无法注册空的监听器实例");
				return;
			}
			boolean isRegistered = false;
			for (Map.Entry<Class<?>, List<?>> entry : OB11_EVENT_LISTENER_TYPE_AND_LIST_MAP.entrySet())
			{
				Class<?> listenerType = entry.getKey();
				List<?> listenerList = entry.getValue();
				if (listenerType.isInstance(listener))
				{
					registerListener(listenerType.cast(listener), (Class<Object>) listenerType, (List<Object>) listenerList);
					isRegistered = true;
					break;
				}
			}
			if (!isRegistered)
			{
				LOGGER.error("无法识别的监听器类型 -> {}", listener.getClass().getSimpleName());
			}
		}
		catch (Exception e)
		{
			LOGGER.error("监听器注册异常", e);
		}
	}
}
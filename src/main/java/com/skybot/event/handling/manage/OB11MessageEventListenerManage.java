package com.skybot.event.handling.manage;

import com.skybot.event.handling.listener.message.OB11MessageEventListener;
import com.skybot.event.handling.listener.message.OB11GroupMessageEventListener;
import com.skybot.event.handling.listener.message.OB11PrivateMessageEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageEventListenerManage
{
	private static final List<OB11MessageEventListener> OB11_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11GroupMessageEventListener> OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11PrivateMessageEventListener> OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST = new ArrayList<>();

	/**
	 * 注册消息事件监听器
	 * @param ob11MessageEventListener 消息事件监听器实例
	 */
	public static void registerListener(OB11MessageEventListener ob11MessageEventListener)
	{
		if (ob11MessageEventListener != null && !OB11_MESSAGE_EVENT_LISTENER_LIST.contains(ob11MessageEventListener))
		{
			OB11_MESSAGE_EVENT_LISTENER_LIST.add(ob11MessageEventListener);
		}
	}

	/**
	 * 移除消息事件监听器
	 * @param ob11MessageEventListener 消息事件监听器实例
	 */
	public static void unregisterListener(OB11MessageEventListener ob11MessageEventListener)
	{
		if (ob11MessageEventListener != null && OB11_MESSAGE_EVENT_LISTENER_LIST.contains(ob11MessageEventListener))
		{
			OB11_MESSAGE_EVENT_LISTENER_LIST.remove(ob11MessageEventListener);
		}
	}

	/**
	 * 注册群聊消息事件监听器
	 * @param ob11GroupMessageEventListener 群聊消息事件监听器实例
	 */
	public static void registerListener(OB11GroupMessageEventListener ob11GroupMessageEventListener)
	{
		if (ob11GroupMessageEventListener != null && !OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.contains(ob11GroupMessageEventListener))
		{
			OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.add(ob11GroupMessageEventListener);
		}
	}

	/**
	 * 移除群聊消息事件监听器
	 * @param ob11GroupMessageEventListener 群聊消息事件监听器实例
	 */
	public static void unregisterListener(OB11GroupMessageEventListener ob11GroupMessageEventListener)
	{
		if (ob11GroupMessageEventListener != null && OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.contains(ob11GroupMessageEventListener))
		{
			OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST.remove(ob11GroupMessageEventListener);
		}
	}

	/**
	 * 注册私聊消息事件监听器
	 * @param ob11PrivateMessageEventListener 私聊消息事件监听器实例
	 */
	public static void registerListener(OB11PrivateMessageEventListener ob11PrivateMessageEventListener)
	{
		if (ob11PrivateMessageEventListener != null && !OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.contains(ob11PrivateMessageEventListener))
		{
			OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.add(ob11PrivateMessageEventListener);
		}
	}

	/**
	 * 移除私聊消息事件监听器
	 * @param ob11PrivateMessageEventListener 私聊消息事件监听器实例
	 */
	public static void unregisterListener(OB11PrivateMessageEventListener ob11PrivateMessageEventListener)
	{
		if (ob11PrivateMessageEventListener != null && OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.contains(ob11PrivateMessageEventListener))
		{
			OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST.remove(ob11PrivateMessageEventListener);
		}
	}

	/**
	 * 获取消息事件监听器列表
	 * @return 消息事件监听器列表
	 */
	public static List<OB11MessageEventListener> getMessageEventListenerList()
	{
		return OB11_MESSAGE_EVENT_LISTENER_LIST;
	}

	/**
	 * 获取群聊消息事件监听器列表
	 * @return 群聊消息事件监听器列表
	 */
	public static List<OB11GroupMessageEventListener> getGroupMessageEventListenerList()
	{
		return OB11_GROUP_MESSAGE_EVENT_LISTENER_LIST;
	}

	/**
	 * 获取私聊消息事件监听器列表
	 * @return 私聊消息事件监听器列表
	 */
	public static List<OB11PrivateMessageEventListener> getPrivateMessageEventListenerList()
	{
		return OB11_PRIVATE_MESSAGE_EVENT_LISTENER_LIST;
	}
}
package com.skybot.event.handling.manage;

import com.skybot.event.handling.listener.request.OB11RequestEventListener;
import com.skybot.event.handling.listener.request.OB11FriendAddRequestEventListener;
import com.skybot.event.handling.listener.request.OB11GroupAddRequestEventListener;
import com.skybot.event.handling.listener.request.OB11GroupInviteRequestEventListener;

import java.util.ArrayList;
import java.util.List;

public class OB11RequestEventListenerManage
{
	private static final List<OB11RequestEventListener> OB11_REQUEST_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11FriendAddRequestEventListener> OB11_FRIEND_ADD_REQUEST_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11GroupAddRequestEventListener> OB11_GROUP_ADD_REQUEST_EVENT_LISTENER_LIST = new ArrayList<>();

	private static final List<OB11GroupInviteRequestEventListener> OB11_GROUP_INVITE_REQUEST_EVENT_LISTENER_LIST = new ArrayList<>();

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
	 * 注册好友申请请求事件监听器
	 * @param ob11FriendAddRequestEventListener 好友申请请求事件监听器实例
	 */
	public static void registerListener(OB11FriendAddRequestEventListener ob11FriendAddRequestEventListener)
	{
		if (ob11FriendAddRequestEventListener != null && !OB11_FRIEND_ADD_REQUEST_EVENT_LISTENER_LIST.contains(ob11FriendAddRequestEventListener))
		{
			OB11_FRIEND_ADD_REQUEST_EVENT_LISTENER_LIST.add(ob11FriendAddRequestEventListener);
		}
	}

	/**
	 * 移除好友申请请求事件监听器
	 * @param ob11FriendAddRequestEventListener 好友申请请求事件监听器实例
	 */
	public static void unregisterListener(OB11FriendAddRequestEventListener ob11FriendAddRequestEventListener)
	{
		if (ob11FriendAddRequestEventListener != null && OB11_FRIEND_ADD_REQUEST_EVENT_LISTENER_LIST.contains(ob11FriendAddRequestEventListener))
		{
			OB11_FRIEND_ADD_REQUEST_EVENT_LISTENER_LIST.add(ob11FriendAddRequestEventListener);
		}
	}

	/**
	 * 入群申请请求事件监听器
	 * @param ob11GroupAddRequestEventListener 入群申请请求事件监听器实例
	 */
	public static void registerListener(OB11GroupAddRequestEventListener ob11GroupAddRequestEventListener)
	{
		if (ob11GroupAddRequestEventListener != null && !OB11_GROUP_ADD_REQUEST_EVENT_LISTENER_LIST.contains(ob11GroupAddRequestEventListener))
		{
			OB11_GROUP_ADD_REQUEST_EVENT_LISTENER_LIST.add(ob11GroupAddRequestEventListener);
		}
	}

	/**
	 * 入群申请请求事件监听器
	 * @param ob11GroupAddRequestEventListener 入群申请请求事件监听器实例
	 */
	public static void unregisterListener(OB11GroupAddRequestEventListener ob11GroupAddRequestEventListener)
	{
		if (ob11GroupAddRequestEventListener != null && OB11_GROUP_ADD_REQUEST_EVENT_LISTENER_LIST.contains(ob11GroupAddRequestEventListener))
		{
			OB11_GROUP_ADD_REQUEST_EVENT_LISTENER_LIST.remove(ob11GroupAddRequestEventListener);
		}
	}

	/**
	 * 入群邀请请求事件监听器
	 * @param ob11GroupInviteRequestEventListener 入群邀请请求事件监听器实例
	 */
	public static void registerListener(OB11GroupInviteRequestEventListener ob11GroupInviteRequestEventListener)
	{
		if (ob11GroupInviteRequestEventListener != null && !OB11_GROUP_INVITE_REQUEST_EVENT_LISTENER_LIST.contains(ob11GroupInviteRequestEventListener))
		{
			OB11_GROUP_INVITE_REQUEST_EVENT_LISTENER_LIST.add(ob11GroupInviteRequestEventListener);
		}
	}

	/**
	 * 入群邀请请求事件监听器
	 * @param ob11GroupInviteRequestEventListener 入群邀请请求事件监听器实例
	 */
	public static void unregisterListener(OB11GroupInviteRequestEventListener ob11GroupInviteRequestEventListener)
	{
		if (ob11GroupInviteRequestEventListener != null && OB11_GROUP_INVITE_REQUEST_EVENT_LISTENER_LIST.contains(ob11GroupInviteRequestEventListener))
		{
			OB11_GROUP_INVITE_REQUEST_EVENT_LISTENER_LIST.remove(ob11GroupInviteRequestEventListener);
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

	/**
	 * 获取注册好友申请请求事件监听器列表
	 * @return 注册好友申请请求事件监听器列表
	 */
	public static List<OB11FriendAddRequestEventListener> getOb11FriendAddRequestEventListenerList()
	{
		return OB11_FRIEND_ADD_REQUEST_EVENT_LISTENER_LIST;
	}

	/**
	 * 获取入群申请请求事件监听器列表
	 * @return 入群申请请求事件监听器列表
	 */
	public static List<OB11GroupAddRequestEventListener> getOb11GroupAddRequestEventListenerList()
	{
		return OB11_GROUP_ADD_REQUEST_EVENT_LISTENER_LIST;
	}

	/**
	 * 获取入群邀请请求事件监听器列表
	 * @return 入群邀请请求事件监听器列表
	 */
	public static List<OB11GroupInviteRequestEventListener> getOb11GroupInviteRequestEventListenerList()
	{
		return OB11_GROUP_INVITE_REQUEST_EVENT_LISTENER_LIST;
	}
}
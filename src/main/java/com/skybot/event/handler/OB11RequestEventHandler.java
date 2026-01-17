package com.skybot.event.handler;

import com.skybot.event.request.OB11FriendRequestEvent;
import com.skybot.event.request.OB11GroupRequestEvent;

public class OB11RequestEventHandler
{
	/**
	 * 好友请求事件
	 * @param ob11FriendRequestEvent 好友请求事件数据类
	 */
	public static void onFriendRequest(OB11FriendRequestEvent ob11FriendRequestEvent)
	{
		System.out.println("OB11FriendRequestEvent" + " -> " + ob11FriendRequestEvent);
	}

	/**
	 * 申请入群事件
	 * @param ob11GroupRequestEvent 申请入群事件数据类
	 */
	public static void onGroupAddRequest(OB11GroupRequestEvent ob11GroupRequestEvent)
	{
		System.out.println("OB11GroupRequestEvent" + " -> " + ob11GroupRequestEvent);
	}

	/**
	 * 邀请入群事件
	 * @param ob11GroupRequestEvent 邀请入群事件数据类
	 */
	public static void onGroupInviteRequest(OB11GroupRequestEvent ob11GroupRequestEvent)
	{
		System.out.println("OB11GroupRequestEvent" + " -> " + ob11GroupRequestEvent);
	}
}
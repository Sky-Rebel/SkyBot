package com.github.sky_rebel.event.handling.dispatcher;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.event.handling.listener.OB11RequestEventListener;
import com.github.sky_rebel.event.handling.manage.OB11RequestEventListenerManage;
import com.github.sky_rebel.event.request.OB11FriendAddRequestEvent;
import com.github.sky_rebel.event.request.OB11GroupAddRequestEvent;
import com.github.sky_rebel.event.request.OB11GroupInviteRequestEvent;

import java.util.List;

public class OB11RequestEventDispatcher
{
	/**
	 * 通知既已注册的好友申请请求事件监听器
	 * @param ob11FriendAddRequestEvent 好友申请请求事件数据类
	 */
	public static void onFriendRequest(Bot bot, OB11FriendAddRequestEvent ob11FriendAddRequestEvent)
	{
		List<OB11RequestEventListener> ob11RequestEventListenerList = OB11RequestEventListenerManage.getOb11RequestEventListenerList();
		ob11RequestEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onFriendRequest(bot, ob11FriendAddRequestEvent));
	}

	/**
	 * 通知既已注册的入群申请请求事件监听器
	 * @param ob11GroupAddRequestEvent 入群申请请求事件数据类
	 */
	public static void onGroupAddRequest(Bot bot, OB11GroupAddRequestEvent ob11GroupAddRequestEvent)
	{
		List<OB11RequestEventListener> ob11RequestEventListenerList = OB11RequestEventListenerManage.getOb11RequestEventListenerList();
		ob11RequestEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onGroupAddRequest(bot, ob11GroupAddRequestEvent));
	}

	/**
	 * 通知既已注册的入群邀请请求事件监听器
	 * @param ob11GroupInviteRequestEvent 入群邀请请求事件数据类
	 */
	public static void onGroupInviteRequest(Bot bot, OB11GroupInviteRequestEvent ob11GroupInviteRequestEvent)
	{
		List<OB11RequestEventListener> ob11RequestEventListenerList = OB11RequestEventListenerManage.getOb11RequestEventListenerList();
		ob11RequestEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onGroupInviteRequest(bot, ob11GroupInviteRequestEvent));
	}
}
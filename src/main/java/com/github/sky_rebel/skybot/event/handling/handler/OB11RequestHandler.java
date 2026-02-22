package com.github.sky_rebel.skybot.event.handling.handler;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.listener.OB11RequestEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.manage.OB11EventListenerManage;
import com.github.sky_rebel.skybot.event.request.OB11FriendAddRequestEvent;
import com.github.sky_rebel.skybot.event.request.OB11GroupAddRequestEvent;
import com.github.sky_rebel.skybot.event.request.OB11GroupInviteRequestEvent;

import java.util.List;

public class OB11RequestHandler
{
	public static void onFriendRequest(Bot bot, OB11FriendAddRequestEvent ob11FriendAddRequestEvent)
	{
		List<OB11RequestEventListener> ob11RequestEventListenerList = OB11EventListenerManage.getRequestEventListenerList();
		ob11RequestEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onFriendRequest(bot, ob11FriendAddRequestEvent));
	}

	public static void onGroupAddRequest(Bot bot, OB11GroupAddRequestEvent ob11GroupAddRequestEvent)
	{
		List<OB11RequestEventListener> ob11RequestEventListenerList = OB11EventListenerManage.getRequestEventListenerList();
		ob11RequestEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onGroupAddRequest(bot, ob11GroupAddRequestEvent));
	}

	public static void onGroupInviteRequest(Bot bot, OB11GroupInviteRequestEvent ob11GroupInviteRequestEvent)
	{
		List<OB11RequestEventListener> ob11RequestEventListenerList = OB11EventListenerManage.getRequestEventListenerList();
		ob11RequestEventListenerList.forEach(ob11RequestEventListener -> ob11RequestEventListener.onGroupInviteRequest(bot, ob11GroupInviteRequestEvent));
	}
}
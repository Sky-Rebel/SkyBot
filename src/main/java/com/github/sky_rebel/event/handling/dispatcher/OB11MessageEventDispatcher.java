package com.github.sky_rebel.event.handling.dispatcher;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.event.handling.listener.message.OB11MessageEventListener;
import com.github.sky_rebel.event.handling.listener.message.OB11GroupMessageEventListener;
import com.github.sky_rebel.event.handling.listener.message.OB11PrivateMessageEventListener;
import com.github.sky_rebel.event.handling.manage.OB11MessageEventListenerManage;
import com.github.sky_rebel.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.event.message.OB11PrivateMessageEvent;

import java.util.List;

public class OB11MessageEventDispatcher
{
	/**
	 * 通知既已注册的群聊消息事件监听器
	 * @param ob11GroupMessageEvent 群聊消息事件数据类
	 */
	public static void onGroupMessage(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		List<OB11MessageEventListener> ob11MessageEventListenerList = OB11MessageEventListenerManage.getMessageEventListenerList();
		ob11MessageEventListenerList.forEach(ob11MessageEventListener -> ob11MessageEventListener.onGroupMessage(bot, ob11GroupMessageEvent));
		List<OB11GroupMessageEventListener> ob11GroupMessageEventListenerList = OB11MessageEventListenerManage.getGroupMessageEventListenerList();
		ob11GroupMessageEventListenerList.forEach(ob11GroupMessageEventListener -> ob11GroupMessageEventListener.onGroupMessage(bot, ob11GroupMessageEvent));
	}

	/**
	 * 通知既已注册的私聊消息事件监听器
	 * @param ob11PrivateMessageEvent 私聊消息事件数据类
	 */
	public static void onPrivateMessage(Bot bot, OB11PrivateMessageEvent ob11PrivateMessageEvent)
	{
		List<OB11MessageEventListener> ob11MessageEventListenerList = OB11MessageEventListenerManage.getMessageEventListenerList();
		ob11MessageEventListenerList.forEach(ob11MessageEventListener -> ob11MessageEventListener.onPrivateMessage(bot, ob11PrivateMessageEvent));
		List<OB11PrivateMessageEventListener> ob11PrivateMessageEventListenerList = OB11MessageEventListenerManage.getPrivateMessageEventListenerList();
		ob11PrivateMessageEventListenerList.forEach(ob11PrivateMessageEventListener -> ob11PrivateMessageEventListener.onPrivateMessage(bot, ob11PrivateMessageEvent));
	}
}
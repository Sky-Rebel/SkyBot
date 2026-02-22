package com.github.sky_rebel.skybot.event.handling.handler;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.listener.OB11GroupMessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11MessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.OB11PrivateMessageEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.manage.OB11EventListenerManage;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;
import com.github.sky_rebel.script.ScriptRunning;

import java.util.List;

public class OB11MessageEventHandler
{
	public static void onGroupMessage(Bot bot, OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		ScriptRunning.run(bot, ob11GroupMessageEvent);
		List<OB11MessageEventListener> ob11MessageEventListenerList = OB11EventListenerManage.getMessageEventListenerList();
		ob11MessageEventListenerList.forEach(ob11MessageEventListener -> ob11MessageEventListener.onGroupMessage(bot, ob11GroupMessageEvent));
		List<OB11GroupMessageEventListener> ob11GroupMessageEventListenerList = OB11EventListenerManage.getGroupMessageEventListenerList();
		ob11GroupMessageEventListenerList.forEach(ob11GroupMessageEventListener -> ob11GroupMessageEventListener.onGroupMessage(bot, ob11GroupMessageEvent));
	}

	public static void onPrivateMessage(Bot bot, OB11PrivateMessageEvent ob11PrivateMessageEvent)
	{
		List<OB11MessageEventListener> ob11MessageEventListenerList = OB11EventListenerManage.getMessageEventListenerList();
		ob11MessageEventListenerList.forEach(ob11MessageEventListener -> ob11MessageEventListener.onPrivateMessage(bot, ob11PrivateMessageEvent));
		List<OB11PrivateMessageEventListener> ob11PrivateMessageEventListenerList = OB11EventListenerManage.getPrivateMessageEventListenerList();
		ob11PrivateMessageEventListenerList.forEach(ob11PrivateMessageEventListener -> ob11PrivateMessageEventListener.onPrivateMessage(bot, ob11PrivateMessageEvent));
	}
}
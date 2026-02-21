package com.github.sky_rebel.skybot.event.handling.handler;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.listener.OB11MetaEventListener;
import com.github.sky_rebel.skybot.event.handling.manage.OB11EventListenerManage;
import com.github.sky_rebel.skybot.event.meta.OB11HeartbeatEvent;
import com.github.sky_rebel.skybot.event.meta.OB11LifeCycleEvent;

import java.util.List;

public class OB11MataEventHandler
{
	public static void onEnable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11EventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onEnable(bot, ob11LifeCycleEvent));
	}

	public static void onDisable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11EventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onDisable(bot, ob11LifeCycleEvent));
	}

	public static void onConnect(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11EventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onConnect(bot, ob11LifeCycleEvent));
	}

	public static void onHeartbeat(Bot bot, OB11HeartbeatEvent ob11HeartbeatEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11EventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onHeartbeat(bot, ob11HeartbeatEvent));
	}
}
package com.github.sky_rebel.bot.event.handling.dispatcher;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.event.handling.listener.OB11MetaEventListener;
import com.github.sky_rebel.bot.event.handling.manage.OB11MetaEventListenerManage;
import com.github.sky_rebel.bot.event.meta.OB11HeartbeatEvent;
import com.github.sky_rebel.bot.event.meta.OB11LifeCycleEvent;

import java.util.List;

public class OB11MateEventDispatcher
{
	/**
	 * 通知既已注册的Bot启用事件监听器
	 * @param ob11LifeCycleEvent Bot启用事件数据类
	 */
	public static void onEnable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11MetaEventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onEnable(bot, ob11LifeCycleEvent));
	}

	/**
	 * 通知既已注册的Bot禁用事件监听器
	 * @param ob11LifeCycleEvent Bot禁用事件数据类
	 */
	public static void onDisable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11MetaEventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onDisable(bot, ob11LifeCycleEvent));
	}
	/**
	 * 通知既已注册的Bot连接事件监听器
	 * @param ob11LifeCycleEvent Bot连接事件数据类
	 */
	public static void onConnect(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11MetaEventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onConnect(bot, ob11LifeCycleEvent));
	}
	/**
	 * 通知既已注册的Bot心跳事件监听器
	 * @param ob11HeartbeatEvent Bot心跳事件数据类
	 */
	public static void onHeartbeat(Bot bot, OB11HeartbeatEvent ob11HeartbeatEvent)
	{
		List<OB11MetaEventListener> ob11MetaEventListenerList = OB11MetaEventListenerManage.getMetaEventListenerList();
		ob11MetaEventListenerList.forEach(ob11MetaEventListener -> ob11MetaEventListener.onHeartbeat(bot, ob11HeartbeatEvent));
	}
}
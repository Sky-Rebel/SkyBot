package com.skybot.event.handler;

import com.skybot.event.meta.OB11HeartbeatEvent;
import com.skybot.event.meta.OB11LifeCycleEvent;

public class OB11MetaEventHandler
{
	/**
	 * Bot启用事件
	 * @param ob11LifeCycleEvent Bot启用事件数据类
	 */
	public static void onEnable(OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		System.out.println("onEnable -> " + ob11LifeCycleEvent);
	}

	/**
	 * Bot禁用事件
	 * @param ob11LifeCycleEvent Bot禁用事件数据类
	 */
	public static void onDisable(OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		System.out.println("onDisable -> " + ob11LifeCycleEvent);
	}

	/**
	 * Bot连接事件
	 * @param ob11LifeCycleEvent Bot连接事件数据类
	 */
	public static void onConnect(OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		System.out.println("onConnect -> " + ob11LifeCycleEvent);
	}

	/**
	 * Bot心跳事件
	 * @param ob11HeartbeatEvent Bot心跳事件数据类
	 */
	public static void onHeartbeat(OB11HeartbeatEvent ob11HeartbeatEvent)
	{
		System.out.println("onHeartbeat -> " + ob11HeartbeatEvent);
	}
}
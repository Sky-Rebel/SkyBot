package com.skybot.bot.event.handling.listener;

import com.skybot.bot.event.meta.OB11HeartbeatEvent;
import com.skybot.bot.event.meta.OB11LifeCycleEvent;

public interface OB11MetaEventListener
{
	/**
	 * Bot启用事件（未实现）
	 * @param ob11LifeCycleEvent Bot启用事件数据类
	 */
	void onEnable(OB11LifeCycleEvent ob11LifeCycleEvent);

	/**
	 * Bot禁用事件（未实现）
	 * @param ob11LifeCycleEvent Bot禁用事件数据类
	 */
	void onDisable(OB11LifeCycleEvent ob11LifeCycleEvent);
	/**
	 * Bot连接事件
	 * @param ob11LifeCycleEvent Bot连接事件数据类
	 */
	void onConnect(OB11LifeCycleEvent ob11LifeCycleEvent);
	/**
	 * Bot心跳事件
	 * @param ob11HeartbeatEvent Bot心跳事件数据类
	 */
	void onHeartbeat(OB11HeartbeatEvent ob11HeartbeatEvent);
}
package com.github.sky_rebel.bot.event.handling.listener;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.event.meta.OB11HeartbeatEvent;
import com.github.sky_rebel.bot.event.meta.OB11LifeCycleEvent;

public interface OB11MetaEventListener
{
	/**
	 * Bot启用事件
	 * @deprecated Napcat暂未实现
	 * @param ob11LifeCycleEvent Bot启用事件数据类
	 */
	@Deprecated
	void onEnable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent);

	/**
	 * Bot禁用事件（未实现）
	 * @deprecated Napcat暂未实现
	 * @param ob11LifeCycleEvent Bot禁用事件数据类
	 */
	@Deprecated
	void onDisable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent);

	/**
	 * Bot连接事件
	 * @param ob11LifeCycleEvent Bot连接事件数据类
	 */
	void onConnect(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent);

	/**
	 * Bot心跳事件
	 * @param ob11HeartbeatEvent Bot心跳事件数据类
	 */
	void onHeartbeat(Bot bot, OB11HeartbeatEvent ob11HeartbeatEvent);
}
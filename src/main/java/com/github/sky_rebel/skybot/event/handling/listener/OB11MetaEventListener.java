package com.github.sky_rebel.skybot.event.handling.listener;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.meta.OB11HeartbeatEvent;
import com.github.sky_rebel.skybot.event.meta.OB11LifeCycleEvent;

public interface OB11MetaEventListener
{
	@Deprecated
	void onEnable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent);

	@Deprecated
	void onDisable(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent);

	void onConnect(Bot bot, OB11LifeCycleEvent ob11LifeCycleEvent);

	void onHeartbeat(Bot bot, OB11HeartbeatEvent ob11HeartbeatEvent);
}
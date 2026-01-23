package com.skybot;

import com.skybot.api.OB11MessageService;
import com.skybot.event.handling.listener.OB11MetaEventListener;
import com.skybot.event.meta.OB11HeartbeatEvent;
import com.skybot.event.meta.OB11LifeCycleEvent;

public class OB11MetaEventListenerImpl implements OB11MetaEventListener
{
	@Override
	public void onEnable(OB11LifeCycleEvent ob11LifeCycleEvent)
	{

	}

	@Override
	public void onDisable(OB11LifeCycleEvent ob11LifeCycleEvent)
	{

	}

	@Override
	public void onConnect(OB11LifeCycleEvent ob11LifeCycleEvent)
	{
		long MAIN_GROUP = 634447585;
		OB11MessageService.sendGroupTextMessage(MAIN_GROUP, "WS Client Connect Event -> " + ob11LifeCycleEvent.time);
	}

	@Override
	public void onHeartbeat(OB11HeartbeatEvent ob11HeartbeatEvent)
	{

	}
}
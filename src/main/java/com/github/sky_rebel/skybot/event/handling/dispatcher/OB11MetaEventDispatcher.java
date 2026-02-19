package com.github.sky_rebel.skybot.event.handling.dispatcher;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.handler.OB11MataEventHandler;
import com.github.sky_rebel.skybot.event.meta.OB11HeartbeatEvent;
import com.github.sky_rebel.skybot.event.meta.OB11LifeCycleEvent;
import org.json.JSONObject;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

public class OB11MetaEventDispatcher
{
	public static void dispatch(Bot bot, JSONObject ob11EventPostData)
	{
		final String metaEventType = ob11EventPostData.getString("meta_event_type");
		if (metaEventType.equals(OB11LifeCycleEvent.META_EVENT_TYPE))
		{
			OB11LifeCycleEvent ob11LifeCycleEvent = new OB11LifeCycleEvent();
			ob11LifeCycleEvent.bot = bot;
			ob11LifeCycleEvent.time = ob11EventPostData.getLong("time");
			ob11LifeCycleEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11LifeCycleEvent.lifeCycleSubType = OB11LifeCycleEvent.LifeCycleSubType.valueOf(ob11EventPostData.getString("sub_type").toUpperCase());
			switch (ob11LifeCycleEvent.lifeCycleSubType)
			{
				case ENABLE -> OB11MataEventHandler.onEnable(bot, ob11LifeCycleEvent);
				case DISABLE -> OB11MataEventHandler.onDisable(bot, ob11LifeCycleEvent);
				case CONNECT -> OB11MataEventHandler.onConnect(bot, ob11LifeCycleEvent);
				default -> LOGGER.warn("未知生命周期事件类型！");
			}
		}
		else if (metaEventType.equals(OB11HeartbeatEvent.META_EVENT_TYPE))
		{
			OB11HeartbeatEvent ob11HeartbeatEvent = new OB11HeartbeatEvent();
			ob11HeartbeatEvent.bot = bot;
			ob11HeartbeatEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11HeartbeatEvent.interval = ob11EventPostData.getInt("interval");
			ob11HeartbeatEvent.time = ob11EventPostData.getLong("time");
			JSONObject status = ob11EventPostData.getJSONObject("status");
			OB11HeartbeatEvent.HeartbeatStatus heartbeatStatus = new OB11HeartbeatEvent.HeartbeatStatus();
			heartbeatStatus.good = status.getBoolean("good");
			heartbeatStatus.online = status.getBoolean("online");
			ob11HeartbeatEvent.heartbeatStatus = heartbeatStatus;
			OB11MataEventHandler.onHeartbeat(bot, ob11HeartbeatEvent);
		}
		else LOGGER.warn("未知元事件类型！");
	}
}
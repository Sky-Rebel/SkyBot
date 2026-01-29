package com.skybot.event.handling.handler;

import com.skybot.event.handling.dispatcher.OB11MateEventDispatcher;
import com.skybot.event.meta.OB11HeartbeatEvent;
import com.skybot.event.meta.OB11LifeCycleEvent;
import org.json.JSONObject;

import static com.skybot.event.handling.handler.OB11EventHandler.LOGGER;

public class OB11MetaEventHandler
{
	/**
	 * 分发元事件
	 * @param ob11EventPostData Napcat客户端上报的原始数据
	 */
	public static void dispatch(JSONObject ob11EventPostData)
	{
		final String metaEventType = ob11EventPostData.getString("meta_event_type");
		if (metaEventType.equals(OB11LifeCycleEvent.META_EVENT_TYPE))
		{
			OB11LifeCycleEvent ob11LifeCycleEvent = new OB11LifeCycleEvent();
			ob11LifeCycleEvent.time = ob11EventPostData.getLong("time");
			ob11LifeCycleEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11LifeCycleEvent.lifeCycleSubType = OB11LifeCycleEvent.LifeCycleSubType.valueOf(ob11EventPostData.getString("sub_type").toUpperCase());
			switch (ob11LifeCycleEvent.lifeCycleSubType)
			{
				case ENABLE -> OB11MateEventDispatcher.onEnable(ob11LifeCycleEvent);
				case DISABLE -> OB11MateEventDispatcher.onDisable(ob11LifeCycleEvent);
				case CONNECT -> OB11MateEventDispatcher.onConnect(ob11LifeCycleEvent);
				default -> LOGGER.warn("未知生命周期事件类型！");
			}
		}
		else if (metaEventType.equals(OB11HeartbeatEvent.META_EVENT_TYPE))
		{
			OB11HeartbeatEvent ob11HeartbeatEvent = new OB11HeartbeatEvent();
			ob11HeartbeatEvent.selfId = ob11EventPostData.getLong("self_id");
			ob11HeartbeatEvent.interval = ob11EventPostData.getInt("interval");
			ob11HeartbeatEvent.time = ob11EventPostData.getLong("time");
			JSONObject status = ob11EventPostData.getJSONObject("status");
			OB11HeartbeatEvent.HeartbeatStatus heartbeatStatus = new OB11HeartbeatEvent.HeartbeatStatus();
			heartbeatStatus.good = status.getBoolean("good");
			heartbeatStatus.online = status.getBoolean("online");
			ob11HeartbeatEvent.heartbeatStatus = heartbeatStatus;
			OB11MateEventDispatcher.onHeartbeat(ob11HeartbeatEvent);
		}
		else LOGGER.warn("未知元事件类型！");
	}
}
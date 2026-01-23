package com.skybot.event.meta;

import com.skybot.event.OB11BaseMetaEvent;

public class OB11HeartbeatEvent extends OB11BaseMetaEvent
{
	public static final String META_EVENT_TYPE = "heartbeat";

	public HeartbeatStatus heartbeatStatus;

	public int interval;

	public static class HeartbeatStatus
	{
		public boolean undefined;

		public boolean good;
	}
}
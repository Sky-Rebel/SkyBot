package com.github.sky_rebel.bot.event.meta;

import com.github.sky_rebel.bot.event.OB11BaseMetaEvent;

public class OB11HeartbeatEvent extends OB11BaseMetaEvent
{
	public static final String META_EVENT_TYPE = "heartbeat";

	public HeartbeatStatus heartbeatStatus;

	public int interval;

	public static class HeartbeatStatus
	{
		public boolean online;

		public boolean good;
	}
}
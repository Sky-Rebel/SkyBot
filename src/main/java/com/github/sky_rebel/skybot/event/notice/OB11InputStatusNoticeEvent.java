package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11InputStatusNoticeEvent extends OB11BaseNoticeEvent
{
	public String status_text;

	public int event_type;

	public long userId;

	public long groupId;
}
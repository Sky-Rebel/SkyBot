package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupBanNoticeEvent extends OB11BaseNoticeEvent
{
	public long operatorId;

	public int duration;

	public long groupId;
}
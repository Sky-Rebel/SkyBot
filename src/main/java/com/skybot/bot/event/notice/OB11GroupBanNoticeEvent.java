package com.skybot.bot.event.notice;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11GroupBanNoticeEvent extends OB11BaseNoticeEvent
{
	public long operatorId;

	public int duration;

	public String subType;

	public long groupId;
}
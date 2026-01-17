package com.skybot.event.notice;

import com.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupBanNoticeEvent extends OB11BaseNoticeEvent
{
	public long operatorId;

	public int duration;

	public String subType;

	public long groupId;
}
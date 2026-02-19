package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupEssenceNoticeEvent extends OB11BaseNoticeEvent
{
	public long messageId;

	public long senderId;

	public long operatorId;

	public String subType;
}
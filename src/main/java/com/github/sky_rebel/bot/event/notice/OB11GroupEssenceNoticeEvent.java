package com.github.sky_rebel.bot.event.notice;

import com.github.sky_rebel.bot.event.OB11BaseNoticeEvent;

public class OB11GroupEssenceNoticeEvent extends OB11BaseNoticeEvent
{
	public long messageId;

	public long senderId;

	public long operatorId;

	public String subType;
}
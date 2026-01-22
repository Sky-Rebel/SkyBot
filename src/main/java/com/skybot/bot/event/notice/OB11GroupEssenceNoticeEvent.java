package com.skybot.bot.event.notice;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11GroupEssenceNoticeEvent extends OB11BaseNoticeEvent
{
	public long messageId;

	public long senderId;

	public long operatorId;

	public String subType;
}
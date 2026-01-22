package com.skybot.bot.event.notice;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11GroupIncreaseNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public long operatorId;
}
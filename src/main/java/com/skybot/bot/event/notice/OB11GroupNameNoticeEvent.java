package com.skybot.bot.event.notice;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11GroupNameNoticeEvent extends OB11BaseNoticeEvent
{
	public String newName;

	public long userId;

	public long groupId;
}
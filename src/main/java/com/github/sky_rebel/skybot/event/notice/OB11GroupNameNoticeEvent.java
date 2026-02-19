package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupNameNoticeEvent extends OB11BaseNoticeEvent
{
	public String newName;

	public long userId;

	public long groupId;
}
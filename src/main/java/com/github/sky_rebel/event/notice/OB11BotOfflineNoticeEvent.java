package com.github.sky_rebel.event.notice;

import com.github.sky_rebel.event.OB11BaseNoticeEvent;

public class OB11BotOfflineNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public String tag;

	public String message;
}
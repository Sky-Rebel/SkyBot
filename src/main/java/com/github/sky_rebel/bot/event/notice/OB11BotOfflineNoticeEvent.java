package com.github.sky_rebel.bot.event.notice;

import com.github.sky_rebel.bot.event.OB11BaseNoticeEvent;

public class OB11BotOfflineNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public String tag;

	public String message;
}
package com.skybot.bot.event.notice;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11BotOfflineNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public String tag;

	public String message;
}
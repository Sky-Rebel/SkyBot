package com.skybot.event.notice;

import com.skybot.event.OB11BaseNoticeEvent;

public class OB11BotOfflineNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public String tag;

	public String message;
}
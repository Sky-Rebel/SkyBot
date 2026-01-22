package com.skybot.bot.event.notice.notify;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11InputStatusNotifyNoticeEvent extends OB11BaseNoticeEvent
{
	public String status_text;

	public int event_type;

	public long userId;

	public long groupId;
}
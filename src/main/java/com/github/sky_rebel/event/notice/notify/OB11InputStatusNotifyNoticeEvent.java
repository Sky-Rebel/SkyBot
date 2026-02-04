package com.github.sky_rebel.event.notice.notify;

import com.github.sky_rebel.event.OB11BaseNoticeEvent;

public class OB11InputStatusNotifyNoticeEvent extends OB11BaseNoticeEvent
{
	public String status_text;

	public int event_type;

	public long userId;

	public long groupId;
}
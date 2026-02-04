package com.github.sky_rebel.bot.event.notice.notify;

import com.github.sky_rebel.bot.event.OB11BaseNoticeEvent;

public class OB11ProfileLikeNotifyNoticeEvent extends OB11BaseNoticeEvent
{
	public long operatorId;

	public String operatorNick;

	public int times;
}
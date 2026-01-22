package com.skybot.bot.event.notice.notify;

import com.skybot.bot.event.OB11BaseNoticeEvent;

public class OB11ProfileLikeNotifyNoticeEvent extends OB11BaseNoticeEvent
{
	public long operatorId;

	public String operatorNick;

	public int times;
}
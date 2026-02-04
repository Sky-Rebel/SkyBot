package com.github.sky_rebel.bot.event.notice;

import com.github.sky_rebel.bot.event.OB11BaseNoticeEvent;

public class OB11GroupGrayTipNoticeEvent extends OB11BaseNoticeEvent
{
	public long groupId;

	public long userId;

	public long messageId;

	public String busiId;

	public String content;

//	public unknown rawInfo;
}
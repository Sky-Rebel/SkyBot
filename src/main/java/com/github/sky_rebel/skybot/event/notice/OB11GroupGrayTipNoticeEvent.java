package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupGrayTipNoticeEvent extends OB11BaseNoticeEvent
{
	public long groupId;

	public long userId;

	public long messageId;

	public String busiId;

	public String content;

//	public unknown rawInfo;
}
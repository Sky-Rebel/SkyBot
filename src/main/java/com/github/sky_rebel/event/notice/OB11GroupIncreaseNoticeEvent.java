package com.github.sky_rebel.event.notice;

import com.github.sky_rebel.event.OB11BaseNoticeEvent;

public class OB11GroupIncreaseNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public long operatorId;
}
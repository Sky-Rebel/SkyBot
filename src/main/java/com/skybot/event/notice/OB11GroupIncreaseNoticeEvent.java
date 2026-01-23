package com.skybot.event.notice;

import com.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupIncreaseNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public long operatorId;
}
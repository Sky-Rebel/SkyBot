package com.github.sky_rebel.skybot.event.notice;

import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupDecreaseNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public long operatorId;

	public enum DecreaseSubType
	{
		LEAVE,

		KICK,

		KICK_ME
	}
}
package com.skybot.event.notice;

import com.skybot.event.OB11BaseNoticeEvent;

public class OB11GroupDecreaseNoticeEvent extends OB11BaseNoticeEvent
{
	public long userId;

	public long groupId;

	public long operatorId;

	public enum DecreaseSubType
	{
		/**
		 * 主动退群
		 */
		LEAVE,

		/**
		 * 被动退群
		 */
		KICK,
		/**
		 * Bot自身被动退群
		 */
		KICK_ME
	}
}
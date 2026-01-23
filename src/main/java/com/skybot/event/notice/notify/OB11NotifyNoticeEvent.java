package com.skybot.event.notice.notify;

public class OB11NotifyNoticeEvent
{
	public enum EventType
	{
		/**
		 * 戳一戳事件
		 */
		POKE,

		/**
		 * 输入状态更新事件
		 */
		INPUT_STATUS,

		/**
		 * 群聊头衔变更事件
		 */
		TITLE,

		/**
		 * Bot收到点赞事件
		 */
		PROFILE_LIKE,

		/**
		 * 群组名字变更事件
		 */
		GROUP_NAME

	}
}

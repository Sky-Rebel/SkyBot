package com.skybot.event;

public class OB11BaseNoticeEvent extends OB11BaseEvent
{
	public enum EventType
	{
		/**
		 * 好友添加成功事件
		 */
		FRIEND_ADD,

		/**
		 * 好友消息撤回事件
		 */
		FRIEND_RECALL,

		/**
		 * 离线文件上传事件（未实现）
		 */
		OFFLINE_FILE,

		/**
		 * 客户端状态变更事件（未实现）
		 */
		CLIENT_STATUS,

		/**
		 * 群组管理变动事件
		 */
		GROUP_ADMIN,

		/**
		 * 群组禁言事件
		 */
		GROUP_BAN,

		/**
		 * 群组名片更新事件
		 */
		GROUP_CARD,

		/**
		 * 群组成员减少事件
		 */
		GROUP_DECREASE,

		/**
		 * 群组成员增加事件
		 */
		GROUP_INCREASE,

		/**
		 * 群组消息撤回事件
		 */
		GROUP_RECALL,

		/**
		 * 群组文件上传事件
		 */
		GROUP_UPLOAD,

		/**
		 * 群组精华消息设置事件
		 */
		ESSENCE,


		/**
		 * 其他通知事件
		 */
		NOTIFY
	}

	@Override
	public String toString()
	{
		return "OB11BaseNoticeEvent{" +
			"time=" + time +
			", selfId=" + selfId +
			'}';
	}
}
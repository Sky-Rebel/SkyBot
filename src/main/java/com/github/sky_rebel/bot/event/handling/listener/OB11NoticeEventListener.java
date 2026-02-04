package com.github.sky_rebel.bot.event.handling.listener;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.event.notice.*;
import com.github.sky_rebel.bot.event.notice.notify.OB11GroupTitleNotifyNoticeEvent;
import com.github.sky_rebel.bot.event.notice.notify.OB11InputStatusNotifyNoticeEvent;
import com.github.sky_rebel.bot.event.notice.notify.OB11ProfileLikeNotifyNoticeEvent;
import com.github.sky_rebel.bot.event.notice.notify.poke.OB11FriendPokeNotifyNoticeEvent;
import com.github.sky_rebel.bot.event.notice.notify.poke.OB11GroupPokeNotifyNoticeEvent;

public interface OB11NoticeEventListener
{
	/**
	 * Bot离线事件
	 * @param ob11BotOfflineNoticeEvent Bot离线事件数据类
	 */
	void onBotOffline(Bot bot, OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent);

	/**
	 * Bot收到点赞事件
	 * @param ob11ProfileLikeNotifyNoticeEvent Bot收到点赞事件数据类
	 */
	void onBotProfileLiked(Bot bot, OB11ProfileLikeNotifyNoticeEvent ob11ProfileLikeNotifyNoticeEvent);

	/**
	 * 好友添加成功事件
	 * @param ob11FriendAddNoticeEvent 好友添加成功事件数据类
	 */
	void onFriendAdded(Bot bot, OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent);

	/**
	 * 好友消息撤回事件
	 * @param ob11FriendRecallNoticeEvent 好友消息撤回事件数据类
	 */
	void onFriendMessageRecalled(Bot bot, OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent);

	/**
	 * 私聊戳一戳事件
	 * @param ob11GroupPokeNoticeEvent 私聊戳一戳事件数据类
	 */
	void onFriendPoke(Bot bot, OB11FriendPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent);

	/**
	 * 输入状态更新事件
	 * @param ob11InputStatusNotifyNoticeEvent 输入状态更新事件数据类
	 */
	void onFriendInputStatusUpdated(Bot bot, OB11InputStatusNotifyNoticeEvent ob11InputStatusNotifyNoticeEvent);

	/**
	 * 群聊设置管理事件
	 * @param ob11GroupAdminNoticeEvent 群聊设置管理事件数据类
	 */
	void onGroupAdminAssigned(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent);

	/**
	 * 群聊取消管理事件
	 * @param ob11GroupAdminNoticeEvent 群聊取消管理事件数据类
	 */
	void onGroupAdminRevoked(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent);

	/**
	 * 群聊设置禁言事件
	 * @param ob11GroupBanNoticeEvent 群聊设置禁言事件数据类
	 */
	void onGroupMemberMuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent);

	/**
	 * 群聊取消禁言事件
	 * @param ob11GroupBanNoticeEvent 群聊取消禁言事件数据类
	 */
	void onGroupMemberUnmuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent);

	/**
	 * 群聊名字更新事件
	 * @param ob11GroupNameNoticeEvent 群聊名字更新事件数据类
	 */
	void onGroupNameUpdated(Bot bot, OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent);

	/**
	 * 群聊名片更新事件
	 * @param ob11GroupCardNoticeEvent 群聊名片更新事件数据类
	 */
	void onGroupMemberCardUpdated(Bot bot, OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent);

	/**
	 * 群聊头衔变更事件
	 * @param ob11GroupTitleNotifyNoticeEvent 群聊头衔变更事件数据类
	 */
	void onGroupMemberTitleUpdated(Bot bot, OB11GroupTitleNotifyNoticeEvent ob11GroupTitleNotifyNoticeEvent);

	/**
	 * 群聊增加成员事件（管理员同意入群）
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	void onGroupMemberApproved(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent);

	/**
	 * 群聊增加成员事件（管理员邀请入群）
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	void onGroupMemberInvited(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent);

	/**
	 * 群聊减少成员事件（主动退出）
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	void onGroupMemberQuit(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent);

	/**
	 * 群聊减少成员事件（被动退出）
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	void onGroupMemberKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent);

	/**
	 * 群聊减少成员事件（Bot自身被动退出）
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	void onGroupBotKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent);

	/**
	 * 群聊消息撤回事件
	 * @param ob11GroupRecallNoticeEvent 群聊消息撤回事件数据类
	 */
	void onGroupMessageRecalled(Bot bot, OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent);

	/**
	 * 群聊文件上传事件
	 * @param ob11GroupUploadNoticeEvent 群聊文件上传事件数据类
	 */
	void onGroupFileUploaded(Bot bot, OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent);

	/**
	 * 群聊表情回应事件
	 * @param ob11GroupMsgEmojiLikeNoticeEvent 群聊表情回应事件数据类
	 */
	void onGroupMessageEmojiLiked(Bot bot, OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent);

	/**
	 * 群聊设置精华消息事件
	 * @param ob11GroupEssenceNoticeEvent 群聊设置精华消息事件
	 */
	void onGroupEssenceMessageAdded(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent);

	/**
	 * 群聊取消精华消息事件（未实现）
	 * @param ob11GroupEssenceNoticeEvent 群聊取消精华消息事件
	 */
	void onGroupEssenceMessageRemoved(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent);

	/**
	 * 群聊戳一戳事件
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	void onGroupPoke(Bot bot, OB11GroupPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent);
}
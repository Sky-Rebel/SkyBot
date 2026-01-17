package com.skybot.event.handler;

import com.skybot.event.notice.*;
import com.skybot.event.notice.notify.OB11GroupTitleNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11InputStatusNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11ProfileLikeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11FriendPokeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11GroupPokeNotifyNoticeEvent;

public class OB11NoticeEventHandler
{
	/**
	 * 好友添加成功事件
	 * @param ob11FriendAddNoticeEvent 好友添加成功事件数据类
	 */
	public static void onFriendAdd(OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent)
	{

	}

	/**
	 * 好友消息撤回事件
	 * @param ob11FriendRecallNoticeEvent 好友消息撤回事件数据类
	 */
	public static void onFriendRecall(OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent)
	{

	}

	/**
	 * 群聊设置管理事件
	 * @param ob11GroupAdminNoticeEvent 群聊设置管理事件数据类
	 */
	public static void onGroupAdminSet(OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{

	}

	/**
	 * 群聊取消管理事件
	 * @param ob11GroupAdminNoticeEvent 群聊取消管理事件数据类
	 */
	public static void onGroupAdminUnset(OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{

	}

	/**
	 * 群聊设置禁言事件
	 * @param ob11GroupBanNoticeEvent 群聊设置管理事件数据类
	 */
	public static void onGroupBan(OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{

	}

	/**
	 * 群聊取消禁言事件
	 * @param ob11GroupBanNoticeEvent 群聊取消管理事件数据类
	 */
	public static void onGroupLiftBan(OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{

	}

	/**
	 * Bot离线事件
	 * @param ob11BotOfflineNoticeEvent Bot离线事件数据类
	 */
	public static void onBotOffline(OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent)
	{

	}

	/**
	 * 群聊名片更新事件
	 * @param ob11GroupCardNoticeEvent 群聊名片更新事件数据类
	 */
	public static void onGroupCard(OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent)
	{

	}

	/**
	 * 群聊减少成员事件（主动退出）
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupLeaveDecrease(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{

	}

	/**
	 * 群聊减少成员事件（被动退出）
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupKickDecrease(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{

	}

	/**
	 * 群聊减少成员事件（Bot自身被动退出）
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupKickMeDecrease(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{

	}

	/**
	 * 群聊增加成员事件（管理员同意入群）
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	public static void onGroupApproveIncrease(OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{

	}

	/**
	 * 群聊增加成员事件（管理员邀请入群）
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	public static void onGroupInviteIncrease(OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{

	}

	/**
	 * 群聊消息撤回事件
	 * @param ob11GroupRecallNoticeEvent 群聊消息撤回事件数据类
	 */
	public static void onGroupRecall(OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent)
	{

	}

	/**
	 * 群聊文件上传事件
	 * @param ob11GroupUploadNoticeEvent 群聊文件上传事件数据类
	 */
	public static void onGroupUpload(OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent)
	{

	}

	/**
	 * 群聊表情回应事件
	 * @param ob11GroupMsgEmojiLikeNoticeEvent 群聊表情回应事件数据类
	 */
	public static void onGroupMsgEmojiLike(OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent)
	{

	}

	/**
	 * 群聊设置精华消息事件
	 * @param ob11GroupEssenceNoticeEvent 群聊设置精华消息事件
	 */
	public static void onGroupAddEssence(OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{

	}

	/**
	 * 群聊取消精华消息事件（未实现）
	 * @param ob11GroupEssenceNoticeEvent 群聊取消精华消息事件
	 */
	public static void onGroupDeleteEssence(OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{

	}

	/**
	 * 群聊戳一戳事件
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	public static void onGroupPokeNotify(OB11GroupPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{
		
	}

	/**
	 * 私聊戳一戳事件
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	public static void onFriendPokeNotify(OB11FriendPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{

	}

	/**
	 * 输入状态更新事件
	 * @param ob11InputStatusNotifyNoticeEvent 输入状态更新事件数据类
	 */
	public static void onInputStatusNotify(OB11InputStatusNotifyNoticeEvent ob11InputStatusNotifyNoticeEvent)
	{

	}

	/**
	 * 群聊头衔变更事件
	 * @param ob11GroupTitleNotifyNoticeEvent 群聊头衔变更事件数据类
	 */
	public static void onTitle(OB11GroupTitleNotifyNoticeEvent ob11GroupTitleNotifyNoticeEvent)
	{

	}

	/**
	 * Bot收到点赞事件
	 * @param ob11ProfileLikeNotifyNoticeEvent Bot收到点赞事件数据类
	 */
	public static void onProfileLikeNotify(OB11ProfileLikeNotifyNoticeEvent ob11ProfileLikeNotifyNoticeEvent)
	{

	}
}
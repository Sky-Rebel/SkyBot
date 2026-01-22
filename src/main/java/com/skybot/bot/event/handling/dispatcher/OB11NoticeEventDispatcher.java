package com.skybot.bot.event.handling.dispatcher;

import com.skybot.bot.event.handling.listener.OB11NoticeEventListener;
import com.skybot.bot.event.handling.manage.OB11NoticeEventListenerManage;
import com.skybot.bot.event.notice.*;
import com.skybot.event.notice.*;
import com.skybot.bot.event.notice.notify.OB11GroupTitleNotifyNoticeEvent;
import com.skybot.bot.event.notice.notify.OB11InputStatusNotifyNoticeEvent;
import com.skybot.bot.event.notice.notify.OB11ProfileLikeNotifyNoticeEvent;
import com.skybot.bot.event.notice.notify.poke.OB11FriendPokeNotifyNoticeEvent;
import com.skybot.bot.event.notice.notify.poke.OB11GroupPokeNotifyNoticeEvent;

import java.util.List;

public class OB11NoticeEventDispatcher
{
	/**
	 * 通知既已注册的好友添加成功事件监听器
	 * @param ob11FriendAddNoticeEvent 好友添加成功事件数据类
	 */
	public static void onFriendAdded(OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendAdded(ob11FriendAddNoticeEvent));
	}

	/**
	 * 通知既已注册的好友消息撤回事件监听器
	 * @param ob11FriendRecallNoticeEvent 好友消息撤回事件数据类
	 */
	public static void onFriendMessageRecalled(OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendMessageRecalled(ob11FriendRecallNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊设置管理事件监听器
	 * @param ob11GroupAdminNoticeEvent 群聊设置管理事件数据类
	 */
	public static void onGroupAdminAssigned(OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupAdminAssigned(ob11GroupAdminNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊取消管理事件监听器
	 * @param ob11GroupAdminNoticeEvent 群聊取消管理事件数据类
	 */
	public static void onGroupAdminRevoked(OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupAdminRevoked(ob11GroupAdminNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊设置禁言事件监听器
	 * @param ob11GroupBanNoticeEvent 群聊设置管理事件数据类
	 */
	public static void onGroupMemberMuted(OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberMuted(ob11GroupBanNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊取消禁言事件监听器
	 * @param ob11GroupBanNoticeEvent 群聊取消管理事件数据类
	 */
	public static void onGroupMemberUnmuted(OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberUnmuted(ob11GroupBanNoticeEvent));
	}

	/**
	 * 通知既已注册的Bot离线事件监听器
	 * @param ob11BotOfflineNoticeEvent Bot离线事件数据类
	 */
	public static void onBotOffline(OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onBotOffline(ob11BotOfflineNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊名字更新事件监听器
	 * @param ob11GroupNameNoticeEvent 群聊名字更新事件数据类
	 */
	public static void onGroupNameUpdated(OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupNameUpdated(ob11GroupNameNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊名片更新事件监听器
	 * @param ob11GroupCardNoticeEvent 群聊名片更新事件数据类
	 */
	public static void onGroupMemberCardUpdated(OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberCardUpdated(ob11GroupCardNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊减少成员事件（主动退出）监听器
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupMemberQuit(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberQuit(ob11GroupDecreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊减少成员事件（被动退出）监听器
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupMemberKicked(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberKicked(ob11GroupDecreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊减少成员事件（Bot自身被动退出）监听器
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupBotKicked(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupBotKicked(ob11GroupDecreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊增加成员事件（管理员同意入群）监听器
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	public static void onGroupMemberApproved(OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberApproved(ob11GroupIncreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊增加成员事件（管理员邀请入群）监听器
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	public static void onGroupMemberInvited(OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberInvited(ob11GroupIncreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊消息撤回事件监听器
	 * @param ob11GroupRecallNoticeEvent 群聊消息撤回事件数据类
	 */
	public static void onGroupMessageRecalled(OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMessageRecalled(ob11GroupRecallNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊文件上传事件监听器
	 * @param ob11GroupUploadNoticeEvent 群聊文件上传事件数据类
	 */
	public static void onGroupFileUploaded(OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupFileUploaded(ob11GroupUploadNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊表情回应事件监听器
	 * @param ob11GroupMsgEmojiLikeNoticeEvent 群聊表情回应事件数据类
	 */
	public static void onGroupMessageEmojiLiked(OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMessageEmojiLiked(ob11GroupMsgEmojiLikeNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊设置精华消息事件监听器
	 * @param ob11GroupEssenceNoticeEvent 群聊设置精华消息事件
	 */
	public static void onGroupEssenceMessageAdded(OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupEssenceMessageAdded(ob11GroupEssenceNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊取消精华消息事件（未实现）监听器
	 * @param ob11GroupEssenceNoticeEvent 群聊取消精华消息事件
	 */
	public static void onGroupEssenceMessageRemoved(OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupEssenceMessageRemoved(ob11GroupEssenceNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊戳一戳事件监听器
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	public static void onGroupPoke(OB11GroupPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupPoke(ob11GroupPokeNoticeEvent));
	}

	/**
	 * 通知既已注册的私聊戳一戳事件监听器
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	public static void onFriendPoke(OB11FriendPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendPoke(ob11GroupPokeNoticeEvent));
	}

	/**
	 * 通知既已注册的输入状态更新事件监听器
	 * @param ob11InputStatusNotifyNoticeEvent 输入状态更新事件数据类
	 */
	public static void onFriendInputStatusUpdated(OB11InputStatusNotifyNoticeEvent ob11InputStatusNotifyNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendInputStatusUpdated(ob11InputStatusNotifyNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊头衔变更事件监听器
	 * @param ob11GroupTitleNotifyNoticeEvent 群聊头衔变更事件数据类
	 */
	public static void onGroupMemberTitleUpdated(OB11GroupTitleNotifyNoticeEvent ob11GroupTitleNotifyNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberTitleUpdated(ob11GroupTitleNotifyNoticeEvent));
	}

	/**
	 * 通知既已注册的Bot收到点赞事件监听器
	 * @param ob11ProfileLikeNotifyNoticeEvent Bot收到点赞事件数据类
	 */
	public static void onProfileLikeNotify(OB11ProfileLikeNotifyNoticeEvent ob11ProfileLikeNotifyNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onBotProfileLiked(ob11ProfileLikeNotifyNoticeEvent));
	}
}
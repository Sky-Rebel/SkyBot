package com.skybot.event.handling.dispatcher;

import com.skybot.bot.Bot;
import com.skybot.event.handling.listener.OB11NoticeEventListener;
import com.skybot.event.handling.manage.OB11NoticeEventListenerManage;
import com.skybot.event.notice.*;
import com.skybot.event.notice.notify.OB11GroupTitleNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11InputStatusNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11ProfileLikeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11FriendPokeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11GroupPokeNotifyNoticeEvent;

import java.util.List;

public class OB11NoticeEventDispatcher
{
	/**
	 * 通知既已注册的好友添加成功事件监听器
	 * @param ob11FriendAddNoticeEvent 好友添加成功事件数据类
	 */
	public static void onFriendAdded(Bot bot, OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendAdded(bot, ob11FriendAddNoticeEvent));
	}

	/**
	 * 通知既已注册的好友消息撤回事件监听器
	 * @param ob11FriendRecallNoticeEvent 好友消息撤回事件数据类
	 */
	public static void onFriendMessageRecalled(Bot bot, OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendMessageRecalled(bot, ob11FriendRecallNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊设置管理事件监听器
	 * @param ob11GroupAdminNoticeEvent 群聊设置管理事件数据类
	 */
	public static void onGroupAdminAssigned(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupAdminAssigned(bot, ob11GroupAdminNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊取消管理事件监听器
	 * @param ob11GroupAdminNoticeEvent 群聊取消管理事件数据类
	 */
	public static void onGroupAdminRevoked(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupAdminRevoked(bot, ob11GroupAdminNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊设置禁言事件监听器
	 * @param ob11GroupBanNoticeEvent 群聊设置管理事件数据类
	 */
	public static void onGroupMemberMuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberMuted(bot, ob11GroupBanNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊取消禁言事件监听器
	 * @param ob11GroupBanNoticeEvent 群聊取消管理事件数据类
	 */
	public static void onGroupMemberUnmuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberUnmuted(bot, ob11GroupBanNoticeEvent));
	}

	/**
	 * 通知既已注册的Bot离线事件监听器
	 * @param ob11BotOfflineNoticeEvent Bot离线事件数据类
	 */
	public static void onBotOffline(Bot bot, OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onBotOffline(bot, ob11BotOfflineNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊名字更新事件监听器
	 * @param ob11GroupNameNoticeEvent 群聊名字更新事件数据类
	 */
	public static void onGroupNameUpdated(Bot bot, OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupNameUpdated(bot, ob11GroupNameNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊名片更新事件监听器
	 * @param ob11GroupCardNoticeEvent 群聊名片更新事件数据类
	 */
	public static void onGroupMemberCardUpdated(Bot bot, OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberCardUpdated(bot, ob11GroupCardNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊减少成员事件（主动退出）监听器
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupMemberQuit(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberQuit(bot, ob11GroupDecreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊减少成员事件（被动退出）监听器
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupMemberKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberKicked(bot, ob11GroupDecreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊减少成员事件（Bot自身被动退出）监听器
	 * @param ob11GroupDecreaseNoticeEvent 群聊减少成员事件数据类
	 */
	public static void onGroupBotKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupBotKicked(bot, ob11GroupDecreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊增加成员事件（管理员同意入群）监听器
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	public static void onGroupMemberApproved(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberApproved(bot, ob11GroupIncreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊增加成员事件（管理员邀请入群）监听器
	 * @param ob11GroupIncreaseNoticeEvent 群聊增加成员事件数据类
	 */
	public static void onGroupMemberInvited(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberInvited(bot, ob11GroupIncreaseNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊消息撤回事件监听器
	 * @param ob11GroupRecallNoticeEvent 群聊消息撤回事件数据类
	 */
	public static void onGroupMessageRecalled(Bot bot, OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMessageRecalled(bot, ob11GroupRecallNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊文件上传事件监听器
	 * @param ob11GroupUploadNoticeEvent 群聊文件上传事件数据类
	 */
	public static void onGroupFileUploaded(Bot bot, OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupFileUploaded(bot, ob11GroupUploadNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊表情回应事件监听器
	 * @param ob11GroupMsgEmojiLikeNoticeEvent 群聊表情回应事件数据类
	 */
	public static void onGroupMessageEmojiLiked(Bot bot, OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMessageEmojiLiked(bot, ob11GroupMsgEmojiLikeNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊设置精华消息事件监听器
	 * @param ob11GroupEssenceNoticeEvent 群聊设置精华消息事件
	 */
	public static void onGroupEssenceMessageAdded(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupEssenceMessageAdded(bot, ob11GroupEssenceNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊取消精华消息事件（未实现）监听器
	 * @param ob11GroupEssenceNoticeEvent 群聊取消精华消息事件
	 */
	public static void onGroupEssenceMessageRemoved(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupEssenceMessageRemoved(bot, ob11GroupEssenceNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊戳一戳事件监听器
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	public static void onGroupPoke(Bot bot, OB11GroupPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupPoke(bot, ob11GroupPokeNoticeEvent));
	}

	/**
	 * 通知既已注册的私聊戳一戳事件监听器
	 * @param ob11GroupPokeNoticeEvent 群聊戳一戳事件数据类
	 */
	public static void onFriendPoke(Bot bot, OB11FriendPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendPoke(bot, ob11GroupPokeNoticeEvent));
	}

	/**
	 * 通知既已注册的输入状态更新事件监听器
	 * @param ob11InputStatusNotifyNoticeEvent 输入状态更新事件数据类
	 */
	public static void onFriendInputStatusUpdated(Bot bot, OB11InputStatusNotifyNoticeEvent ob11InputStatusNotifyNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendInputStatusUpdated(bot, ob11InputStatusNotifyNoticeEvent));
	}

	/**
	 * 通知既已注册的群聊头衔变更事件监听器
	 * @param ob11GroupTitleNotifyNoticeEvent 群聊头衔变更事件数据类
	 */
	public static void onGroupMemberTitleUpdated(Bot bot, OB11GroupTitleNotifyNoticeEvent ob11GroupTitleNotifyNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberTitleUpdated(bot, ob11GroupTitleNotifyNoticeEvent));
	}

	/**
	 * 通知既已注册的Bot收到点赞事件监听器
	 * @param ob11ProfileLikeNotifyNoticeEvent Bot收到点赞事件数据类
	 */
	public static void onProfileLikeNotify(Bot bot, OB11ProfileLikeNotifyNoticeEvent ob11ProfileLikeNotifyNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11NoticeEventListenerManage.getOb11NoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onBotProfileLiked(bot, ob11ProfileLikeNotifyNoticeEvent));
	}
}
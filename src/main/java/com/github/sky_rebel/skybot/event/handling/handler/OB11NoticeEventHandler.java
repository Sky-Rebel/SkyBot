package com.github.sky_rebel.skybot.event.handling.handler;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.listener.OB11NoticeEventListener;
import com.github.sky_rebel.skybot.event.handling.listener.manage.OB11EventListenerManage;
import com.github.sky_rebel.skybot.event.notice.OB11BotOfflineNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11FriendAddNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11FriendRecallNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupAdminNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupBanNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupCardNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupDecreaseNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupEssenceNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupIncreaseNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupMsgEmojiLikeNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupNameNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupRecallNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupUploadNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupTitleNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11InputStatusNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11ProfileLikeNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11FriendPokeNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupPokeNoticeEvent;

import java.util.List;

public class OB11NoticeEventHandler
{
	public static void onFriendAdded(Bot bot, OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendAdded(bot, ob11FriendAddNoticeEvent));
	}

	public static void onFriendMessageRecalled(Bot bot, OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendMessageRecalled(bot, ob11FriendRecallNoticeEvent));
	}

	public static void onGroupAdminAssigned(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupAdminAssigned(bot, ob11GroupAdminNoticeEvent));
	}

	public static void onGroupAdminRevoked(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupAdminRevoked(bot, ob11GroupAdminNoticeEvent));
	}

	public static void onGroupMemberMuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberMuted(bot, ob11GroupBanNoticeEvent));
	}

	public static void onGroupMemberUnmuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberUnmuted(bot, ob11GroupBanNoticeEvent));
	}

	public static void onBotOffline(Bot bot, OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onBotOffline(bot, ob11BotOfflineNoticeEvent));
	}

	public static void onGroupNameUpdated(Bot bot, OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupNameUpdated(bot, ob11GroupNameNoticeEvent));
	}

	public static void onGroupMemberCardUpdated(Bot bot, OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberCardUpdated(bot, ob11GroupCardNoticeEvent));
	}

	public static void onGroupMemberQuit(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberQuit(bot, ob11GroupDecreaseNoticeEvent));
	}

	public static void onGroupMemberKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberKicked(bot, ob11GroupDecreaseNoticeEvent));
	}

	public static void onGroupBotKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupBotKicked(bot, ob11GroupDecreaseNoticeEvent));
	}

	public static void onGroupMemberApproved(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberApproved(bot, ob11GroupIncreaseNoticeEvent));
	}

	public static void onGroupMemberInvited(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberInvited(bot, ob11GroupIncreaseNoticeEvent));
	}

	public static void onGroupMessageRecalled(Bot bot, OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMessageRecalled(bot, ob11GroupRecallNoticeEvent));
	}

	public static void onGroupFileUploaded(Bot bot, OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupFileUploaded(bot, ob11GroupUploadNoticeEvent));
	}

	public static void onGroupMessageEmojiLiked(Bot bot, OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMessageEmojiLiked(bot, ob11GroupMsgEmojiLikeNoticeEvent));
	}

	public static void onGroupEssenceMessageAdded(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupEssenceMessageAdded(bot, ob11GroupEssenceNoticeEvent));
	}

	public static void onGroupEssenceMessageRemoved(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupEssenceMessageRemoved(bot, ob11GroupEssenceNoticeEvent));
	}

	public static void onGroupPoke(Bot bot, OB11GroupPokeNoticeEvent ob11GroupPokeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupPoke(bot, ob11GroupPokeNoticeEvent));
	}

	public static void onFriendPoke(Bot bot, OB11FriendPokeNoticeEvent ob11GroupPokeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendPoke(bot, ob11GroupPokeNoticeEvent));
	}

	public static void onFriendInputStatusUpdated(Bot bot, OB11InputStatusNoticeEvent ob11InputStatusNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onFriendInputStatusUpdated(bot, ob11InputStatusNoticeEvent));
	}

	public static void onGroupMemberTitleUpdated(Bot bot, OB11GroupTitleNoticeEvent ob11GroupTitleNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onGroupMemberTitleUpdated(bot, ob11GroupTitleNoticeEvent));
	}

	public static void onProfileLikeNotify(Bot bot, OB11ProfileLikeNoticeEvent ob11ProfileLikeNoticeEvent)
	{
		List<OB11NoticeEventListener> ob11NoticeEventListenerList = OB11EventListenerManage.getNoticeEventListenerList();
		ob11NoticeEventListenerList.forEach(ob11NoticeEventListener -> ob11NoticeEventListener.onBotProfileLiked(bot, ob11ProfileLikeNoticeEvent));
	}
}
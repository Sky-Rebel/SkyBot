package com.skybot;

import com.skybot.api.OB11MessageApiService;
import com.skybot.event.handling.listener.OB11NoticeEventListener;
import com.skybot.event.notice.*;
import com.skybot.event.notice.notify.OB11GroupTitleNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11InputStatusNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11ProfileLikeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11FriendPokeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11GroupPokeNotifyNoticeEvent;

public class OB11NoticeEventListenerImpl implements OB11NoticeEventListener
{
	@Override
	public void onBotOffline(OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent)
	{

	}

	@Override
	public void onBotProfileLiked(OB11ProfileLikeNotifyNoticeEvent ob11ProfileLikeNotifyNoticeEvent)
	{

	}

	@Override
	public void onFriendAdded(OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent)
	{

	}

	@Override
	public void onFriendMessageRecalled(OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent)
	{

	}

	@Override
	public void onFriendPoke(OB11FriendPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{

	}

	@Override
	public void onFriendInputStatusUpdated(OB11InputStatusNotifyNoticeEvent ob11InputStatusNotifyNoticeEvent)
	{

	}

	@Override
	public void onGroupAdminAssigned(OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{

	}

	@Override
	public void onGroupAdminRevoked(OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent)
	{

	}

	@Override
	public void onGroupMemberMuted(OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{

	}

	@Override
	public void onGroupMemberUnmuted(OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent)
	{

	}

	// OK
	@Override
	public void onGroupNameUpdated(OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent)
	{
		OB11MessageApiService.sendGroupTextMessage(ob11GroupNameNoticeEvent.groupId, ob11GroupNameNoticeEvent.userId + "为本群更改了新的名字 -> " + ob11GroupNameNoticeEvent.newName);
	}

	@Override
	public void onGroupMemberCardUpdated(OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent)
	{

	}

	@Override
	public void onGroupMemberTitleUpdated(OB11GroupTitleNotifyNoticeEvent ob11GroupTitleNotifyNoticeEvent)
	{

	}

	@Override
	public void onGroupMemberApproved(OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		String message =
			"""
			群组同意入群事件
			
			机器自身: %d
			所处群组: %d
			处理管理: %d
			入群用户: %d
			
			时间戳: %d
			""".formatted(ob11GroupIncreaseNoticeEvent.selfId, ob11GroupIncreaseNoticeEvent.groupId, ob11GroupIncreaseNoticeEvent.operatorId, ob11GroupIncreaseNoticeEvent.userId, ob11GroupIncreaseNoticeEvent.time);
		OB11MessageApiService.sendGroupTextMessage(ob11GroupIncreaseNoticeEvent.groupId, message);
	}

	@Override
	public void onGroupMemberInvited(OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent)
	{
		String message =
			"""
			群组邀请入群事件
			
			机器自身: %d
			所处群组: %d
			邀请群员: %d
			入群用户: %d
			
			时间戳: %d
			""".formatted(ob11GroupIncreaseNoticeEvent.selfId, ob11GroupIncreaseNoticeEvent.groupId, ob11GroupIncreaseNoticeEvent.operatorId, ob11GroupIncreaseNoticeEvent.userId, ob11GroupIncreaseNoticeEvent.time);
		OB11MessageApiService.sendGroupTextMessage(ob11GroupIncreaseNoticeEvent.groupId, message);
	}

	@Override
	public void onGroupMemberQuit(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		OB11MessageApiService.sendGroupTextMessage(ob11GroupDecreaseNoticeEvent.groupId, ob11GroupDecreaseNoticeEvent.toString());
	}

	@Override
	public void onGroupMemberKicked(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{
		OB11MessageApiService.sendGroupTextMessage(ob11GroupDecreaseNoticeEvent.groupId, ob11GroupDecreaseNoticeEvent.toString());
	}

	@Override
	public void onGroupBotKicked(OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent)
	{

	}

	@Override
	public void onGroupMessageRecalled(OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent)
	{

	}

	@Override
	public void onGroupFileUploaded(OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent)
	{

	}

	@Override
	public void onGroupMessageEmojiLiked(OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent)
	{

	}

	@Override
	public void onGroupEssenceMessageAdded(OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{

	}

	@Override
	public void onGroupEssenceMessageRemoved(OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent)
	{

	}

	// OK
	@Override
	public void onGroupPoke(OB11GroupPokeNotifyNoticeEvent ob11GroupPokeNoticeEvent)
	{
		OB11MessageApiService.sendGroupTextMessage(ob11GroupPokeNoticeEvent.groupId, "请不要戳我！");
	}
}
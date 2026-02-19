package com.github.sky_rebel.skybot.event.handling.listener;

import com.github.sky_rebel.skybot.Bot;
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

public interface OB11NoticeEventListener
{
	void onBotOffline(Bot bot, OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent);

	void onBotProfileLiked(Bot bot, OB11ProfileLikeNoticeEvent ob11ProfileLikeNoticeEvent);

	void onFriendAdded(Bot bot, OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent);

	void onFriendMessageRecalled(Bot bot, OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent);

	void onFriendPoke(Bot bot, OB11FriendPokeNoticeEvent ob11GroupPokeNoticeEvent);

	void onFriendInputStatusUpdated(Bot bot, OB11InputStatusNoticeEvent ob11InputStatusNoticeEvent);

	void onGroupAdminAssigned(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent);

	void onGroupAdminRevoked(Bot bot, OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent);

	void onGroupMemberMuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent);

	void onGroupMemberUnmuted(Bot bot, OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent);

	void onGroupNameUpdated(Bot bot, OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent);

	void onGroupMemberCardUpdated(Bot bot, OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent);

	void onGroupMemberTitleUpdated(Bot bot, OB11GroupTitleNoticeEvent ob11GroupTitleNoticeEvent);

	void onGroupMemberApproved(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent);

	void onGroupMemberInvited(Bot bot, OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent);

	void onGroupMemberQuit(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent);

	void onGroupMemberKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent);

	void onGroupBotKicked(Bot bot, OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent);

	void onGroupMessageRecalled(Bot bot, OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent);

	void onGroupFileUploaded(Bot bot, OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent);

	void onGroupMessageEmojiLiked(Bot bot, OB11GroupMsgEmojiLikeNoticeEvent ob11GroupMsgEmojiLikeNoticeEvent);

	void onGroupEssenceMessageAdded(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent);

	void onGroupEssenceMessageRemoved(Bot bot, OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent);

	void onGroupPoke(Bot bot, OB11GroupPokeNoticeEvent ob11GroupPokeNoticeEvent);
}
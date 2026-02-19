package com.github.sky_rebel.skybot.event.handling.dispatcher;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.OB11BaseNoticeEvent;
import com.github.sky_rebel.skybot.event.handling.handler.OB11NoticeEventHandler;
import com.github.sky_rebel.skybot.event.notice.OB11BotOfflineNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11FriendAddNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11FriendRecallNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupAdminNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupBanNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupCardNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupDecreaseNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupEssenceNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupIncreaseNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupNameNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupRecallNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupUploadNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupTitleNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11InputStatusNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11NotifyNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11ProfileLikeNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11FriendPokeNoticeEvent;
import com.github.sky_rebel.skybot.event.notice.OB11GroupPokeNoticeEvent;
import org.json.JSONObject;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

public class OB11NoticeEventDispatcher
{
	public static void dispatch(Bot bot, JSONObject ob11EventPostData)
	{
		final OB11BaseNoticeEvent.Type noticeType = OB11BaseNoticeEvent.Type.valueOf(ob11EventPostData.getString("notice_type").toUpperCase());
		switch (noticeType)
		{
			case FRIEND_ADD ->
			{
				OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent = new OB11FriendAddNoticeEvent();
				ob11FriendAddNoticeEvent.bot = bot;
				ob11FriendAddNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11FriendAddNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11FriendAddNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				OB11NoticeEventHandler.onFriendAdded(bot, ob11FriendAddNoticeEvent);
			}
			case FRIEND_RECALL ->
			{
				OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent = new OB11FriendRecallNoticeEvent();
				ob11FriendRecallNoticeEvent.bot = bot;
				ob11FriendRecallNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11FriendRecallNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11FriendRecallNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11FriendRecallNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				OB11NoticeEventHandler.onFriendMessageRecalled(bot, ob11FriendRecallNoticeEvent);
			}
			case GROUP_BAN ->
			{
				OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent = new OB11GroupBanNoticeEvent();
				ob11GroupBanNoticeEvent.bot = bot;
				ob11GroupBanNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupBanNoticeEvent.duration = ob11EventPostData.getInt("duration");
				ob11GroupBanNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
				ob11GroupBanNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
				String subType = ob11EventPostData.getString("sub_type");
				if (subType.equals("ban"))
					OB11NoticeEventHandler.onGroupMemberMuted(bot, ob11GroupBanNoticeEvent);
				else if (subType.equals("lift_ban"))
					OB11NoticeEventHandler.onGroupMemberUnmuted(bot, ob11GroupBanNoticeEvent);
				else LOGGER.warn("未知群组禁言事件子类型");
			}
			case GROUP_CARD ->
			{
				OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent = new OB11GroupCardNoticeEvent();
				ob11GroupCardNoticeEvent.bot = bot;
				ob11GroupCardNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupCardNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupCardNoticeEvent.newCard = ob11EventPostData.getString("card_new");
				ob11GroupCardNoticeEvent.oldCrd = ob11EventPostData.getString("card_old");
				OB11NoticeEventHandler.onGroupMemberCardUpdated(bot, ob11GroupCardNoticeEvent);
			}
			case GROUP_ADMIN ->
			{
				OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent = new OB11GroupAdminNoticeEvent();
				ob11GroupAdminNoticeEvent.bot = bot;
				ob11GroupAdminNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupAdminNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupAdminNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11GroupAdminNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
				String subType = ob11EventPostData.getString("sub_type");
				if (subType.equals("set"))
					OB11NoticeEventHandler.onGroupAdminAssigned(bot, ob11GroupAdminNoticeEvent);
				else if (subType.equals("unset"))
					OB11NoticeEventHandler.onGroupAdminRevoked(bot, ob11GroupAdminNoticeEvent);
			}
			case GROUP_RECALL ->
			{
				OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent = new OB11GroupRecallNoticeEvent();
				ob11GroupRecallNoticeEvent.bot = bot;
				ob11GroupRecallNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupRecallNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupRecallNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11GroupRecallNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
				OB11NoticeEventHandler.onGroupMessageRecalled(bot, ob11GroupRecallNoticeEvent);
			}
			case GROUP_UPLOAD ->
			{
				OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent = new OB11GroupUploadNoticeEvent();
				ob11GroupUploadNoticeEvent.bot = bot;
				ob11GroupUploadNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupUploadNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				OB11GroupUploadNoticeEvent.GroupUploadFile groupUploadFile = new OB11GroupUploadNoticeEvent.GroupUploadFile();
				JSONObject fileInfo = ob11EventPostData.getJSONObject("file");
				groupUploadFile.id = fileInfo.getString("id");
				groupUploadFile.name = fileInfo.getString("name");
				groupUploadFile.size = fileInfo.getLong("size");
				groupUploadFile.busid = fileInfo.getLong("busid");
				ob11GroupUploadNoticeEvent.groupUploadFile = groupUploadFile;
				OB11NoticeEventHandler.onGroupFileUploaded(bot, ob11GroupUploadNoticeEvent);
			}
			case GROUP_DECREASE ->
			{
				OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent = new OB11GroupDecreaseNoticeEvent();
				ob11GroupDecreaseNoticeEvent.bot = bot;
				ob11GroupDecreaseNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupDecreaseNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupDecreaseNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11GroupDecreaseNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
				ob11GroupDecreaseNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
				String subType = ob11EventPostData.getString("sub_type");
				OB11GroupDecreaseNoticeEvent.DecreaseSubType decreaseSubType = OB11GroupDecreaseNoticeEvent.DecreaseSubType.valueOf(subType.toUpperCase());
				switch (decreaseSubType)
				{
					case LEAVE -> OB11NoticeEventHandler.onGroupMemberKicked(bot, ob11GroupDecreaseNoticeEvent);
					case KICK -> OB11NoticeEventHandler.onGroupMemberKicked(bot, ob11GroupDecreaseNoticeEvent);
					case KICK_ME -> OB11NoticeEventHandler.onGroupBotKicked(bot, ob11GroupDecreaseNoticeEvent);
					default -> LOGGER.warn("未知群组减少成员事件子类型！");
				}
			}
			case GROUP_INCREASE ->
			{
				OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent = new OB11GroupIncreaseNoticeEvent();
				ob11GroupIncreaseNoticeEvent.bot = bot;
				ob11GroupIncreaseNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupIncreaseNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupIncreaseNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11GroupIncreaseNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
				ob11GroupIncreaseNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
				String subType = ob11EventPostData.getString("sub_type");
				if (subType.equals("approve"))
					OB11NoticeEventHandler.onGroupMemberApproved(bot, ob11GroupIncreaseNoticeEvent);
				else if (subType.equals("invite"))
					OB11NoticeEventHandler.onGroupMemberInvited(bot, ob11GroupIncreaseNoticeEvent);
				else LOGGER.warn("未知群组增加成员事件子类型！");
			}
			case ESSENCE ->
			{
				OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent = new OB11GroupEssenceNoticeEvent();
				ob11GroupEssenceNoticeEvent.bot = bot;
				ob11GroupEssenceNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11GroupEssenceNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupEssenceNoticeEvent.senderId = ob11EventPostData.getLong("sender_id");
				ob11GroupEssenceNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11GroupEssenceNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
				String subType = ob11EventPostData.getString("sub_type");
				if (subType.equals("add"))
					OB11NoticeEventHandler.onGroupEssenceMessageAdded(bot, ob11GroupEssenceNoticeEvent);
				else if (subType.equals("delete"))
					OB11NoticeEventHandler.onGroupEssenceMessageRemoved(bot, ob11GroupEssenceNoticeEvent);
				else LOGGER.warn("未知精华消息变动事件子类型");
			}
			case BOT_OFFLINE ->
			{
				OB11BotOfflineNoticeEvent ob11BotOfflineNoticeEvent = new OB11BotOfflineNoticeEvent();
				ob11BotOfflineNoticeEvent.bot = bot;
				ob11BotOfflineNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11BotOfflineNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11BotOfflineNoticeEvent.time = ob11EventPostData.getLong("time");
				ob11BotOfflineNoticeEvent.tag = ob11EventPostData.getString("tag");
				ob11BotOfflineNoticeEvent.message = ob11EventPostData.getString("message");
				OB11NoticeEventHandler.onBotOffline(bot, ob11BotOfflineNoticeEvent);
			}
			case NOTIFY ->
			{
				String subType = ob11EventPostData.getString("sub_type");
				OB11NotifyNoticeEvent.Type notifyNoticeType = OB11NotifyNoticeEvent.Type.valueOf(subType.toUpperCase());
				switch (notifyNoticeType)
				{
					case POKE ->
					{
						if (ob11EventPostData.has("group_id"))
						{
							OB11GroupPokeNoticeEvent ob11GroupPokeNotifyNoticeEvent = new OB11GroupPokeNoticeEvent();
							ob11GroupPokeNotifyNoticeEvent.bot = bot;
							ob11GroupPokeNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
							ob11GroupPokeNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
							ob11GroupPokeNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
							ob11GroupPokeNotifyNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
							ob11GroupPokeNotifyNoticeEvent.targetId = ob11EventPostData.getLong("target_id");
							OB11NoticeEventHandler.onGroupPoke(bot, ob11GroupPokeNotifyNoticeEvent);
						}
						else
						{
							OB11FriendPokeNoticeEvent ob11FriendPokeNotifyNoticeEvent = new OB11FriendPokeNoticeEvent();
							ob11FriendPokeNotifyNoticeEvent.bot = bot;
							ob11FriendPokeNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
							ob11FriendPokeNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
							ob11FriendPokeNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
							ob11FriendPokeNotifyNoticeEvent.targetId = ob11EventPostData.getLong("target_id");
							OB11NoticeEventHandler.onFriendPoke(bot, ob11FriendPokeNotifyNoticeEvent);
						}
					}
					case TITLE ->
					{
						OB11GroupTitleNoticeEvent ob11GroupTitleNoticeEvent = new OB11GroupTitleNoticeEvent();
						ob11GroupTitleNoticeEvent.bot = bot;
						ob11GroupTitleNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupTitleNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupTitleNoticeEvent.title = ob11EventPostData.getString("title");;
						OB11NoticeEventHandler.onGroupMemberTitleUpdated(bot, ob11GroupTitleNoticeEvent);
					}
					case INPUT_STATUS ->
					{
						OB11InputStatusNoticeEvent ob11InputStatusNoticeEvent = new OB11InputStatusNoticeEvent();
						ob11InputStatusNoticeEvent.bot = bot;
						ob11InputStatusNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11InputStatusNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11InputStatusNoticeEvent.event_type = ob11EventPostData.getInt("event_type");
						ob11InputStatusNoticeEvent.status_text = ob11EventPostData.getString("status_text");
						ob11InputStatusNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11InputStatusNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						OB11NoticeEventHandler.onFriendInputStatusUpdated(bot, ob11InputStatusNoticeEvent);
					}
					case PROFILE_LIKE ->
					{
						OB11ProfileLikeNoticeEvent ob11ProfileLikeNoticeEvent = new OB11ProfileLikeNoticeEvent();
						ob11ProfileLikeNoticeEvent.bot = bot;
						ob11ProfileLikeNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11ProfileLikeNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11ProfileLikeNoticeEvent.times = ob11EventPostData.getInt("times");
						ob11ProfileLikeNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						ob11ProfileLikeNoticeEvent.operatorNick = ob11EventPostData.getString("operator_nick");
						OB11NoticeEventHandler.onProfileLikeNotify(bot, ob11ProfileLikeNoticeEvent);
					}
					case GROUP_NAME ->
					{
						OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent = new OB11GroupNameNoticeEvent();
						ob11GroupNameNoticeEvent.bot = bot;
						ob11GroupNameNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupNameNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupNameNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11GroupNameNoticeEvent.newName = ob11EventPostData.getString("name_new");
						ob11GroupNameNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						OB11NoticeEventHandler.onGroupNameUpdated(bot, ob11GroupNameNoticeEvent);
					}
				}
			}
		}
	}
}
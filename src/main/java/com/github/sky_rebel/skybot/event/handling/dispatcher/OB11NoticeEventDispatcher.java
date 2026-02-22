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
				ob11FriendAddNoticeEvent.setBot(bot);
				ob11FriendAddNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11FriendAddNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11FriendAddNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
				OB11NoticeEventHandler.onFriendAdded(bot, ob11FriendAddNoticeEvent);
			}
			case FRIEND_RECALL ->
			{
				OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent = new OB11FriendRecallNoticeEvent();
				ob11FriendRecallNoticeEvent.setBot(bot);
				ob11FriendRecallNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11FriendRecallNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11FriendRecallNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11FriendRecallNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
				OB11NoticeEventHandler.onFriendMessageRecalled(bot, ob11FriendRecallNoticeEvent);
			}
			case GROUP_BAN ->
			{
				OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent = new OB11GroupBanNoticeEvent();
				ob11GroupBanNoticeEvent.setBot(bot);
				ob11GroupBanNoticeEvent.setTime(ob11EventPostData.getLong("time"));
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
				ob11GroupCardNoticeEvent.setBot(bot);
				ob11GroupCardNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupCardNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
				ob11GroupCardNoticeEvent.newCard = ob11EventPostData.getString("card_new");
				ob11GroupCardNoticeEvent.oldCrd = ob11EventPostData.getString("card_old");
				OB11NoticeEventHandler.onGroupMemberCardUpdated(bot, ob11GroupCardNoticeEvent);
			}
			case GROUP_ADMIN ->
			{
				OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent = new OB11GroupAdminNoticeEvent();
				ob11GroupAdminNoticeEvent.setBot(bot);
				ob11GroupAdminNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupAdminNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
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
				ob11GroupRecallNoticeEvent.setBot(bot);
				ob11GroupRecallNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupRecallNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
				ob11GroupRecallNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11GroupRecallNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
				OB11NoticeEventHandler.onGroupMessageRecalled(bot, ob11GroupRecallNoticeEvent);
			}
			case GROUP_UPLOAD ->
			{
				OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent = new OB11GroupUploadNoticeEvent();
				ob11GroupUploadNoticeEvent.setBot(bot);
				ob11GroupUploadNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupUploadNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
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
				ob11GroupDecreaseNoticeEvent.setBot(bot);
				ob11GroupDecreaseNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupDecreaseNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
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
				ob11GroupIncreaseNoticeEvent.setBot(bot);
				ob11GroupIncreaseNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupIncreaseNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
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
				ob11GroupEssenceNoticeEvent.setBot(bot);
				ob11GroupEssenceNoticeEvent.setTime(ob11EventPostData.getLong("time"));
				ob11GroupEssenceNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
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
				ob11BotOfflineNoticeEvent.setBot(bot);
				ob11BotOfflineNoticeEvent.userId = ob11EventPostData.getLong("user_id");
				ob11BotOfflineNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
				ob11BotOfflineNoticeEvent.setTime(ob11EventPostData.getLong("time"));
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
							ob11GroupPokeNotifyNoticeEvent.setBot(bot);
							ob11GroupPokeNotifyNoticeEvent.setTime(ob11EventPostData.getLong("time"));
							ob11GroupPokeNotifyNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
							ob11GroupPokeNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
							ob11GroupPokeNotifyNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
							ob11GroupPokeNotifyNoticeEvent.targetId = ob11EventPostData.getLong("target_id");
							OB11NoticeEventHandler.onGroupPoke(bot, ob11GroupPokeNotifyNoticeEvent);
						}
						else
						{
							OB11FriendPokeNoticeEvent ob11FriendPokeNotifyNoticeEvent = new OB11FriendPokeNoticeEvent();
							ob11FriendPokeNotifyNoticeEvent.setBot(bot);
							ob11FriendPokeNotifyNoticeEvent.setTime(ob11EventPostData.getLong("time"));
							ob11FriendPokeNotifyNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
							ob11FriendPokeNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
							ob11FriendPokeNotifyNoticeEvent.targetId = ob11EventPostData.getLong("target_id");
							OB11NoticeEventHandler.onFriendPoke(bot, ob11FriendPokeNotifyNoticeEvent);
						}
					}
					case TITLE ->
					{
						OB11GroupTitleNoticeEvent ob11GroupTitleNoticeEvent = new OB11GroupTitleNoticeEvent();
						ob11GroupTitleNoticeEvent.setBot(bot);
						ob11GroupTitleNoticeEvent.setTime(ob11EventPostData.getLong("time"));
						ob11GroupTitleNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
						ob11GroupTitleNoticeEvent.title = ob11EventPostData.getString("title");;
						OB11NoticeEventHandler.onGroupMemberTitleUpdated(bot, ob11GroupTitleNoticeEvent);
					}
					case INPUT_STATUS ->
					{
						OB11InputStatusNoticeEvent ob11InputStatusNoticeEvent = new OB11InputStatusNoticeEvent();
						ob11InputStatusNoticeEvent.setBot(bot);
						ob11InputStatusNoticeEvent.setTime(ob11EventPostData.getLong("time"));
						ob11InputStatusNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
						ob11InputStatusNoticeEvent.event_type = ob11EventPostData.getInt("event_type");
						ob11InputStatusNoticeEvent.status_text = ob11EventPostData.getString("status_text");
						ob11InputStatusNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11InputStatusNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						OB11NoticeEventHandler.onFriendInputStatusUpdated(bot, ob11InputStatusNoticeEvent);
					}
					case PROFILE_LIKE ->
					{
						OB11ProfileLikeNoticeEvent ob11ProfileLikeNoticeEvent = new OB11ProfileLikeNoticeEvent();
						ob11ProfileLikeNoticeEvent.setBot(bot);
						ob11ProfileLikeNoticeEvent.setTime(ob11EventPostData.getLong("time"));
						ob11ProfileLikeNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
						ob11ProfileLikeNoticeEvent.times = ob11EventPostData.getInt("times");
						ob11ProfileLikeNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						ob11ProfileLikeNoticeEvent.operatorNick = ob11EventPostData.getString("operator_nick");
						OB11NoticeEventHandler.onProfileLikeNotify(bot, ob11ProfileLikeNoticeEvent);
					}
					case GROUP_NAME ->
					{
						OB11GroupNameNoticeEvent ob11GroupNameNoticeEvent = new OB11GroupNameNoticeEvent();
						ob11GroupNameNoticeEvent.setBot(bot);
						ob11GroupNameNoticeEvent.setTime(ob11EventPostData.getLong("time"));
						ob11GroupNameNoticeEvent.setSelfId(ob11EventPostData.getLong("self_id"));
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
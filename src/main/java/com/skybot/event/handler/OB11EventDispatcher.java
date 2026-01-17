package com.skybot.event.handler;

import com.skybot.event.OB11BaseEvent;
import com.skybot.event.OB11BaseNoticeEvent;
import com.skybot.event.message.OB11GroupMessageEvent;
import com.skybot.event.message.OB11PrivateMessageEvent;
import com.skybot.event.meta.OB11HeartbeatEvent;
import com.skybot.event.meta.OB11LifeCycleEvent;
import com.skybot.event.notice.*;
import com.skybot.event.notice.notify.OB11GroupTitleNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11InputStatusNotifyNoticeEvent;
import com.skybot.event.notice.notify.OB11ProfileLikeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11FriendPokeNotifyNoticeEvent;
import com.skybot.event.notice.notify.poke.OB11GroupPokeNotifyNoticeEvent;
import com.skybot.event.request.OB11FriendRequestEvent;
import com.skybot.event.request.OB11GroupRequestEvent;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OB11EventDispatcher
{
	public static final Logger LOGGER = LoggerFactory.getLogger(OB11EventDispatcher.class);

	/**
	 * 分发OB11事件
	 * @param ob11EventPostData Napcat客户端上报的原始数据
	 */
	public static void dispatch(JSONObject ob11EventPostData)
	{
		final String postType = ob11EventPostData.getString("post_type");
		OB11BaseEvent.EventType eventType;
		if (postType.equals("meta_event"))
			eventType = OB11BaseEvent.EventType.META;
		else
			eventType = OB11BaseEvent.EventType.valueOf(postType.toUpperCase());
		switch (eventType)
		{
			case META ->
			{
				final String metaEventType = ob11EventPostData.getString("meta_event_type");
				if (metaEventType.equals(OB11LifeCycleEvent.META_EVENT_TYPE))
				{
					OB11LifeCycleEvent ob11LifeCycleEvent = new OB11LifeCycleEvent();
					ob11LifeCycleEvent.time = ob11EventPostData.getLong("time");
					ob11LifeCycleEvent.selfId = ob11EventPostData.getLong("self_id");
					String subType = ob11EventPostData.getString("sub_type");
					ob11LifeCycleEvent.lifeCycleSubType = OB11LifeCycleEvent.LifeCycleSubType.valueOf(subType.toUpperCase());
					switch (ob11LifeCycleEvent.lifeCycleSubType)
					{
						case ENABLE -> OB11MetaEventHandler.onEnable(ob11LifeCycleEvent);
						case DISABLE -> OB11MetaEventHandler.onDisable(ob11LifeCycleEvent);
						case CONNECT -> OB11MetaEventHandler.onConnect(ob11LifeCycleEvent);
						default -> LOGGER.warn("未知生命周期事件类型！");
					}
				}
				else if (metaEventType.equals(OB11HeartbeatEvent.META_EVENT_TYPE))
				{
					OB11HeartbeatEvent ob11HeartbeatEvent = new OB11HeartbeatEvent();
					ob11HeartbeatEvent.selfId = ob11EventPostData.getLong("self_id");
					ob11HeartbeatEvent.interval = ob11EventPostData.getInt("interval");
					ob11HeartbeatEvent.time = ob11EventPostData.getLong("time");
					JSONObject status = ob11EventPostData.getJSONObject("status");
					OB11HeartbeatEvent.HeartbeatStatus heartbeatStatus = new OB11HeartbeatEvent.HeartbeatStatus();
					heartbeatStatus.good = status.getBoolean("good");
					heartbeatStatus.undefined = status.getBoolean("online");
					ob11HeartbeatEvent.heartbeatStatus = heartbeatStatus;
					OB11MetaEventHandler.onHeartbeat(ob11HeartbeatEvent);
				}
				else LOGGER.warn("未知元事件类型！");
			}
			case MESSAGE ->
			{
				final String messageEventType = ob11EventPostData.getString("message_type");
				if (messageEventType.equals(OB11GroupMessageEvent.MESSAGE_EVENT_TYPE))
				{
					OB11GroupMessageEvent ob11GroupMessageEvent = new OB11GroupMessageEvent();
					ob11GroupMessageEvent.groupId = ob11EventPostData.getLong("group_id");
					ob11GroupMessageEvent.messageId = ob11EventPostData.getLong("message_id");
					ob11GroupMessageEvent.rawMessage = ob11EventPostData.getString("raw_message");
					ob11GroupMessageEvent.selfId = ob11EventPostData.getLong("self_id");
					ob11GroupMessageEvent.time = ob11EventPostData.getLong("time");
					ob11GroupMessageEvent.userId = ob11EventPostData.getLong("user_id");
					ob11GroupMessageEvent.message = ob11EventPostData.getJSONArray("message");
					OB11GroupMessageEvent.Sender sender = new OB11GroupMessageEvent.Sender();
					JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
					sender.card = senderJson.getString("card");
					sender.userId = senderJson.getLong("user_id");
					sender.nickname = senderJson.getString("nickname");
					sender.role = senderJson.getString("role");
					ob11GroupMessageEvent.sender = sender;
					OB11MessageEventHandler.onGroupMessage(ob11GroupMessageEvent);
				}
				else if (messageEventType.equals(OB11PrivateMessageEvent.MESSAGE_EVENT_TYPE))
				{
					OB11PrivateMessageEvent ob11PrivateMessageEvent = new OB11PrivateMessageEvent();
					ob11PrivateMessageEvent.messageId = ob11EventPostData.getLong("message_id");
					ob11PrivateMessageEvent.rawMessage = ob11EventPostData.getString("raw_message");
					ob11PrivateMessageEvent.selfId = ob11EventPostData.getLong("self_id");
					ob11PrivateMessageEvent.time = ob11EventPostData.getLong("time");
					ob11PrivateMessageEvent.userId = ob11EventPostData.getLong("user_id");
					ob11PrivateMessageEvent.message = ob11EventPostData.getJSONArray("message");
					OB11PrivateMessageEvent.Sender sender = new OB11PrivateMessageEvent.Sender();
					JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
					sender.age = senderJson.getInt("age");
					sender.userId = senderJson.getLong("user_id");
					sender.nickname = senderJson.getString("nickname");
					sender.sex = senderJson.getString("sex");
					ob11PrivateMessageEvent.sender = sender;
					OB11MessageEventHandler.onPrivateMessage(ob11PrivateMessageEvent);
				}
				else LOGGER.warn("未知消息事件类型！");
			}
			case NOTICE ->
			{
				final OB11BaseNoticeEvent.EventType noticeEventType = OB11BaseNoticeEvent.EventType.valueOf(ob11EventPostData.getString("notice_type").toUpperCase());
				switch (noticeEventType)
				{
					case FRIEND_ADD ->
					{
						OB11FriendAddNoticeEvent ob11FriendAddNoticeEvent = new OB11FriendAddNoticeEvent();
						ob11FriendAddNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11FriendAddNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11FriendAddNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						OB11NoticeEventHandler.onFriendAdd(ob11FriendAddNoticeEvent);
					}
					case FRIEND_RECALL ->
					{
						OB11FriendRecallNoticeEvent ob11FriendRecallNoticeEvent = new OB11FriendRecallNoticeEvent();
						ob11FriendRecallNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11FriendRecallNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
						ob11FriendRecallNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11FriendRecallNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						OB11NoticeEventHandler.onFriendRecall(ob11FriendRecallNoticeEvent);
					}
					case GROUP_BAN ->
					{
						OB11GroupBanNoticeEvent ob11GroupBanNoticeEvent = new OB11GroupBanNoticeEvent();
						ob11GroupBanNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupBanNoticeEvent.duration = ob11EventPostData.getInt("duration");
						ob11GroupBanNoticeEvent.subType = ob11EventPostData.getString("sub_type");
						ob11GroupBanNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						ob11GroupBanNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						String subType = ob11EventPostData.getString("sub_type");
						if (subType.equals("ban"))
							OB11NoticeEventHandler.onGroupBan(ob11GroupBanNoticeEvent);
						else if (subType.equals("lift_ban"))
							OB11NoticeEventHandler.onGroupLiftBan(ob11GroupBanNoticeEvent);
						else LOGGER.warn("未知群组禁言事件子类型");
					}
					case GROUP_CARD ->
					{
						OB11GroupCardNoticeEvent ob11GroupCardNoticeEvent = new OB11GroupCardNoticeEvent();
						ob11GroupCardNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupCardNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupCardNoticeEvent.newCard = ob11EventPostData.getString("new_card");
						ob11GroupCardNoticeEvent.oldCrd = ob11EventPostData.getString("old_card");
						OB11NoticeEventHandler.onGroupCard(ob11GroupCardNoticeEvent);
					}
					case GROUP_ADMIN ->
					{
						OB11GroupAdminNoticeEvent ob11GroupAdminNoticeEvent = new OB11GroupAdminNoticeEvent();
						ob11GroupAdminNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupAdminNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupAdminNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11GroupAdminNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						String subType = ob11EventPostData.getString("sub_type");
						if (subType.equals("set"))
							OB11NoticeEventHandler.onGroupAdminSet(ob11GroupAdminNoticeEvent);
						else if (subType.equals("unset"))
							OB11NoticeEventHandler.onGroupAdminUnset(ob11GroupAdminNoticeEvent);
					}
					case GROUP_RECALL ->
					{
						OB11GroupRecallNoticeEvent ob11GroupRecallNoticeEvent = new OB11GroupRecallNoticeEvent();
						ob11GroupRecallNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupRecallNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupRecallNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
						ob11GroupRecallNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						OB11NoticeEventHandler.onGroupRecall(ob11GroupRecallNoticeEvent);
					}
					case GROUP_UPLOAD ->
					{
						OB11GroupUploadNoticeEvent ob11GroupUploadNoticeEvent = new OB11GroupUploadNoticeEvent();
						ob11GroupUploadNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupUploadNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						OB11GroupUploadNoticeEvent.GroupUploadFile groupUploadFile = new OB11GroupUploadNoticeEvent.GroupUploadFile();
						JSONObject fileInfo = ob11EventPostData.getJSONObject("file");
						groupUploadFile.id = fileInfo.getString("id");
						groupUploadFile.name = fileInfo.getString("name");
						groupUploadFile.size = fileInfo.getLong("size");
						groupUploadFile.busid = fileInfo.getLong("busid");
						OB11NoticeEventHandler.onGroupUpload(ob11GroupUploadNoticeEvent);
					}
					case GROUP_DECREASE ->
					{
						OB11GroupDecreaseNoticeEvent ob11GroupDecreaseNoticeEvent = new OB11GroupDecreaseNoticeEvent();
						ob11GroupDecreaseNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupDecreaseNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupDecreaseNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11GroupDecreaseNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						ob11GroupDecreaseNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						String subType = ob11EventPostData.getString("sub_type");
						OB11GroupDecreaseNoticeEvent.DecreaseSubType decreaseSubType = OB11GroupDecreaseNoticeEvent.DecreaseSubType.valueOf(subType.toUpperCase());
						switch (decreaseSubType)
						{
							case LEAVE -> OB11NoticeEventHandler.onGroupLeaveDecrease(ob11GroupDecreaseNoticeEvent);
							case KICK -> OB11NoticeEventHandler.onGroupKickDecrease(ob11GroupDecreaseNoticeEvent);
							case KICK_ME -> OB11NoticeEventHandler.onGroupKickMeDecrease(ob11GroupDecreaseNoticeEvent);
							default -> LOGGER.warn("未知群组减少成员事件子类型！");
						}
					}
					case GROUP_INCREASE ->
					{
						OB11GroupIncreaseNoticeEvent ob11GroupIncreaseNoticeEvent = new OB11GroupIncreaseNoticeEvent();
						ob11GroupIncreaseNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupIncreaseNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupIncreaseNoticeEvent.userId = ob11EventPostData.getLong("user_id");
						ob11GroupIncreaseNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
						ob11GroupIncreaseNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						String subType = ob11EventPostData.getString("sub_type");
						if (subType.equals("approve"))
							OB11NoticeEventHandler.onGroupApproveIncrease(ob11GroupIncreaseNoticeEvent);
						else if (subType.equals("invite"))
							OB11NoticeEventHandler.onGroupInviteIncrease(ob11GroupIncreaseNoticeEvent);
						else LOGGER.warn("未知群组增加成员事件子类型！");
					}
					case ESSENCE ->
					{
						OB11GroupEssenceNoticeEvent ob11GroupEssenceNoticeEvent = new OB11GroupEssenceNoticeEvent();
						ob11GroupEssenceNoticeEvent.time = ob11EventPostData.getLong("time");
						ob11GroupEssenceNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
						ob11GroupEssenceNoticeEvent.senderId = ob11EventPostData.getLong("sender_id");
						ob11GroupEssenceNoticeEvent.messageId = ob11EventPostData.getLong("message_id");
						ob11GroupEssenceNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
						String subType = ob11EventPostData.getString("sub_type");
						if (subType.equals("add"))
							OB11NoticeEventHandler.onGroupAddEssence(ob11GroupEssenceNoticeEvent);
						else if (subType.equals("delete"))
							OB11NoticeEventHandler.onGroupDeleteEssence(ob11GroupEssenceNoticeEvent);
						else LOGGER.warn("未知精华消息变动事件子类型");
					}
					case NOTIFY ->
					{
						String subType = ob11EventPostData.getString("sub_type");
						OB11NotifyNoticeEvent.EventType notifyNoticeEventType = OB11NotifyNoticeEvent.EventType.valueOf(subType.toUpperCase());
						switch (notifyNoticeEventType)
						{
							case POKE ->
							{
								if (ob11EventPostData.has("group_id"))
								{
									OB11GroupPokeNotifyNoticeEvent ob11GroupPokeNotifyNoticeEvent = new OB11GroupPokeNotifyNoticeEvent();
									ob11GroupPokeNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
									ob11GroupPokeNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
									ob11GroupPokeNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
									ob11GroupPokeNotifyNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
									ob11GroupPokeNotifyNoticeEvent.targetId = ob11EventPostData.getLong("target_id");
									OB11NoticeEventHandler.onGroupPokeNotify(ob11GroupPokeNotifyNoticeEvent);
								}
								else
								{
									OB11FriendPokeNotifyNoticeEvent ob11FriendPokeNotifyNoticeEvent = new OB11FriendPokeNotifyNoticeEvent();
									ob11FriendPokeNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
									ob11FriendPokeNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
									ob11FriendPokeNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
									ob11FriendPokeNotifyNoticeEvent.targetId = ob11EventPostData.getLong("target_id");
									OB11NoticeEventHandler.onFriendPokeNotify(ob11FriendPokeNotifyNoticeEvent);
								}
							}
							case TITLE ->
							{
								OB11GroupTitleNotifyNoticeEvent ob11GroupTitleNotifyNoticeEvent = new OB11GroupTitleNotifyNoticeEvent();
								ob11GroupTitleNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
								ob11GroupTitleNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
								ob11GroupTitleNotifyNoticeEvent.title = ob11EventPostData.getString("title");;
								OB11NoticeEventHandler.onTitle(ob11GroupTitleNotifyNoticeEvent);
							}
							case INPUT_STATUS ->
							{
								OB11InputStatusNotifyNoticeEvent ob11InputStatusNotifyNoticeEvent = new OB11InputStatusNotifyNoticeEvent();
								ob11InputStatusNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
								ob11InputStatusNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
								ob11InputStatusNotifyNoticeEvent.event_type = ob11EventPostData.getInt("event_type");
								ob11InputStatusNotifyNoticeEvent.status_text = ob11EventPostData.getString("event_type");
								ob11InputStatusNotifyNoticeEvent.userId = ob11EventPostData.getLong("user_id");
								ob11InputStatusNotifyNoticeEvent.groupId = ob11EventPostData.getLong("group_id");
								OB11NoticeEventHandler.onInputStatusNotify(ob11InputStatusNotifyNoticeEvent);
							}
							case PROFILE_LIKE ->
							{
								OB11ProfileLikeNotifyNoticeEvent ob11ProfileLikeNotifyNoticeEvent = new OB11ProfileLikeNotifyNoticeEvent();
								ob11ProfileLikeNotifyNoticeEvent.time = ob11EventPostData.getLong("time");
								ob11ProfileLikeNotifyNoticeEvent.selfId = ob11EventPostData.getLong("self_id");
								ob11ProfileLikeNotifyNoticeEvent.times = ob11EventPostData.getInt("times");
								ob11ProfileLikeNotifyNoticeEvent.operatorId = ob11EventPostData.getLong("operator_id");
								ob11ProfileLikeNotifyNoticeEvent.operatorNick = ob11EventPostData.getString("operator_nick");
								OB11NoticeEventHandler.onProfileLikeNotify(ob11ProfileLikeNotifyNoticeEvent);
							}
						}
					}
				}
			}
			case REQUEST ->
			{
				final String requestEventType = ob11EventPostData.getString("request_type");
				if (requestEventType.equals("friend"))
				{
					OB11FriendRequestEvent ob11FriendRequestEvent = new OB11FriendRequestEvent();
					ob11FriendRequestEvent.comment = ob11EventPostData.getString("comment");
					ob11FriendRequestEvent.userId = ob11EventPostData.getLong("user_id");
					ob11FriendRequestEvent.flag = ob11EventPostData.getString("flag");
					ob11FriendRequestEvent.time = ob11EventPostData.getLong("time");
					ob11FriendRequestEvent.selfId = ob11EventPostData.getLong("self_id");
					OB11RequestEventHandler.onFriendRequest(ob11FriendRequestEvent);
				}
				else if (requestEventType.equals("group"))
				{
					OB11GroupRequestEvent ob11GroupRequestEvent = new OB11GroupRequestEvent();
					ob11GroupRequestEvent.time = ob11EventPostData.getLong("time");
					ob11GroupRequestEvent.comment = ob11EventPostData.getString("comment");
					ob11GroupRequestEvent.groupId = ob11EventPostData.getLong("group_id");
					ob11GroupRequestEvent.flag = ob11EventPostData.getString("flag");
					ob11GroupRequestEvent.selfId = ob11EventPostData.getLong("self_id");
					ob11GroupRequestEvent.userId = ob11EventPostData.getLong("user_id");
					final String subType = ob11EventPostData.getString("sub_type");
					if (subType.equals("add"))
						OB11RequestEventHandler.onGroupAddRequest(ob11GroupRequestEvent);
					else if (subType.equals("invite"))
						OB11RequestEventHandler.onGroupInviteRequest(ob11GroupRequestEvent);
					else LOGGER.warn("未知群组请求事件类型！");
				}
				else LOGGER.warn("未知请求事件类型！");
			}
			case MESSAGE_SENT ->
			{
				// pass
			}
			default -> LOGGER.warn("未知基础事件类型！");
		}
	}
}
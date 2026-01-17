package com.skybot;

import com.skybot.event.OB11BaseMessageEvent;
import com.skybot.event.OB11BaseMessageSentEvent;
import com.skybot.event.OB11BaseMetaEvent;
import com.skybot.event.handler.OB11MessageEventHandler;
import com.skybot.event.handler.OB11MetaEventHandler;
import com.skybot.event.message.OB11GroupMessageEvent;
import com.skybot.event.message.OB11PrivateMessageEvent;
import com.skybot.event.meta.OB11LifeCycleEvent;
import org.json.JSONObject;

public class EventDispatcher
{
	/**
	 * 分发OB11事件
	 * @param ob11EventPostData Napcat客户端上报的原始数据
	 */
	public static void dispatch(JSONObject ob11EventPostData)
	{
		final String postType = ob11EventPostData.getString("post_type");
		if (postType.equals(OB11BaseMetaEvent.POST_TYPE))
		{
			final String metaEventType = ob11EventPostData.getString("meta_event_type");
			if (metaEventType.equals(OB11LifeCycleEvent.META_EVENT_TYPE))
			{
				OB11LifeCycleEvent ob11LifeCycleEvent = new OB11LifeCycleEvent();
				ob11LifeCycleEvent.time = ob11EventPostData.getLong("time");
				ob11LifeCycleEvent.self_id = ob11EventPostData.getLong("self_id");
				ob11LifeCycleEvent.post_type = ob11EventPostData.getString("post_type");
				ob11LifeCycleEvent.lifeCycleSubType = OB11LifeCycleEvent.LifeCycleSubType.valueOf(ob11EventPostData.getString("sub_type").toUpperCase());
				switch (ob11LifeCycleEvent.lifeCycleSubType)
				{
					case ENABLE -> OB11MetaEventHandler.onEnable(ob11LifeCycleEvent);
					case DISABLE -> OB11MetaEventHandler.onDisable(ob11LifeCycleEvent);
					case CONNECT -> OB11MetaEventHandler.onConnect(ob11LifeCycleEvent);
				}
			}
		}
		else if (postType.equals(OB11BaseMessageEvent.POST_TYPE))
		{
			final String messageType = ob11EventPostData.getString("message_type");
			if (messageType.equals(OB11GroupMessageEvent.MESSAGE_TYPE))
			{
				OB11GroupMessageEvent ob11GroupMessageEvent = new OB11GroupMessageEvent();
				ob11GroupMessageEvent.groupId = ob11EventPostData.getLong("group_id");
				ob11GroupMessageEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11GroupMessageEvent.rawMessage = ob11EventPostData.getString("raw_message");
				ob11GroupMessageEvent.self_id = ob11EventPostData.getLong("self_id");
				ob11GroupMessageEvent.time = ob11EventPostData.getLong("time");
				ob11GroupMessageEvent.userId = ob11EventPostData.getLong("user_id");
				ob11GroupMessageEvent.message = ob11EventPostData.getJSONArray("message");
				ob11GroupMessageEvent.post_type = OB11GroupMessageEvent.POST_TYPE;
				OB11GroupMessageEvent.Sender sender = new OB11GroupMessageEvent.Sender();
				JSONObject senderJson = ob11EventPostData.getJSONObject("sender");
				sender.card = senderJson.getString("card");
				sender.userId = senderJson.getLong("user_id");
				sender.nickname = senderJson.getString("nickname");
				sender.role = senderJson.getString("role");
				ob11GroupMessageEvent.sender = sender;
				OB11MessageEventHandler.onGroupMessage(ob11GroupMessageEvent);
			}
			else if (messageType.equals(OB11PrivateMessageEvent.MESSAGE_TYPE))
			{
				OB11PrivateMessageEvent ob11PrivateMessageEvent = new OB11PrivateMessageEvent();
				ob11PrivateMessageEvent.messageId = ob11EventPostData.getLong("message_id");
				ob11PrivateMessageEvent.rawMessage = ob11EventPostData.getString("raw_message");
				ob11PrivateMessageEvent.self_id = ob11EventPostData.getLong("self_id");
				ob11PrivateMessageEvent.time = ob11EventPostData.getLong("time");
				ob11PrivateMessageEvent.userId = ob11EventPostData.getLong("user_id");
				ob11PrivateMessageEvent.post_type = OB11PrivateMessageEvent.POST_TYPE;
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
		}
		else if (postType.equals(OB11BaseMessageSentEvent.POST_TYPE))
		{

		}
	}
}
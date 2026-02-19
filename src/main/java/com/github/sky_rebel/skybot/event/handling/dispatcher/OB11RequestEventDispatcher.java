package com.github.sky_rebel.skybot.event.handling.dispatcher;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.handling.handler.OB11RequestHandler;
import com.github.sky_rebel.skybot.event.request.OB11FriendAddRequestEvent;
import com.github.sky_rebel.skybot.event.request.OB11GroupAddRequestEvent;
import com.github.sky_rebel.skybot.event.request.OB11GroupInviteRequestEvent;
import org.json.JSONObject;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

public class OB11RequestEventDispatcher
{
	public static void dispatch(Bot bot, JSONObject ob11EventPostData)
	{
		final String requestEventType = ob11EventPostData.getString("request_type");
		if (requestEventType.equals("friend"))
		{
			OB11FriendAddRequestEvent ob11FriendAddRequestEvent = new OB11FriendAddRequestEvent();
			ob11FriendAddRequestEvent.bot = bot;
			ob11FriendAddRequestEvent.comment = ob11EventPostData.getString("comment");
			ob11FriendAddRequestEvent.userId = ob11EventPostData.getLong("user_id");
			ob11FriendAddRequestEvent.flag = ob11EventPostData.getString("flag");
			ob11FriendAddRequestEvent.time = ob11EventPostData.getLong("time");
			ob11FriendAddRequestEvent.selfId = ob11EventPostData.getLong("self_id");
			OB11RequestHandler.onFriendRequest(bot, ob11FriendAddRequestEvent);
		}
		else if (requestEventType.equals("group"))
		{
			final String subType = ob11EventPostData.getString("sub_type");
			if (subType.equals("add"))
			{
				OB11GroupAddRequestEvent ob11GroupAddRequestEvent = new OB11GroupAddRequestEvent();
				ob11GroupAddRequestEvent.bot = bot;
				ob11GroupAddRequestEvent.time = ob11EventPostData.getLong("time");
				ob11GroupAddRequestEvent.comment = ob11EventPostData.getString("comment");
				ob11GroupAddRequestEvent.groupId = ob11EventPostData.getLong("group_id");
				ob11GroupAddRequestEvent.flag = ob11EventPostData.getString("flag");
				ob11GroupAddRequestEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupAddRequestEvent.userId = ob11EventPostData.getLong("user_id");
				OB11RequestHandler.onGroupAddRequest(bot, ob11GroupAddRequestEvent);
			}
			else if (subType.equals("invite"))
			{
				OB11GroupInviteRequestEvent ob11GroupInviteRequestEvent = new OB11GroupInviteRequestEvent();
				ob11GroupInviteRequestEvent.bot = bot;
				ob11GroupInviteRequestEvent.time = ob11EventPostData.getLong("time");
				ob11GroupInviteRequestEvent.selfId = ob11EventPostData.getLong("self_id");
				ob11GroupInviteRequestEvent.groupId = ob11EventPostData.getLong("group_id");
				ob11GroupInviteRequestEvent.userId = ob11EventPostData.getLong("user_id");
				ob11GroupInviteRequestEvent.flag = ob11EventPostData.getString("flag");
				ob11GroupInviteRequestEvent.comment = ob11EventPostData.getString("comment");
				OB11RequestHandler.onGroupInviteRequest(bot, ob11GroupInviteRequestEvent);
			}
			else LOGGER.warn("未知群组请求事件类型！");
		}
		else LOGGER.warn("未知请求事件类型！");
	}
}
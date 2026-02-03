package com.skybot.event.handling.listener;

import com.skybot.bot.Bot;
import com.skybot.event.request.OB11FriendAddRequestEvent;
import com.skybot.event.request.OB11GroupAddRequestEvent;
import com.skybot.event.request.OB11GroupInviteRequestEvent;

public interface OB11RequestEventListener
{
	/**
	 * 好友申请请求事件
	 * @param ob11FriendAddRequestEvent 好友申请请求事件数据类
	 */
	void onFriendRequest(Bot bot, OB11FriendAddRequestEvent ob11FriendAddRequestEvent);

	/**
	 * 入群申请请求事件
	 * @param ob11GroupAddRequestEvent 入群申请请求事件数据类
	 */
	void onGroupAddRequest(Bot bot, OB11GroupAddRequestEvent ob11GroupAddRequestEvent);

	/**
	 * 入群邀请请求事件
	 * @param ob11GroupInviteRequestEvent 入群邀请请求事件数据类
	 */
	void onGroupInviteRequest(Bot bot, OB11GroupInviteRequestEvent ob11GroupInviteRequestEvent);
}
package com.github.sky_rebel.skybot.event.handling.listener;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.request.OB11FriendAddRequestEvent;
import com.github.sky_rebel.skybot.event.request.OB11GroupAddRequestEvent;
import com.github.sky_rebel.skybot.event.request.OB11GroupInviteRequestEvent;

public interface OB11RequestEventListener
{
	void onFriendRequest(Bot bot, OB11FriendAddRequestEvent ob11FriendAddRequestEvent);

	void onGroupAddRequest(Bot bot, OB11GroupAddRequestEvent ob11GroupAddRequestEvent);

	void onGroupInviteRequest(Bot bot, OB11GroupInviteRequestEvent ob11GroupInviteRequestEvent);
}
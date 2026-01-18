package com.skybot.event.handling.listener.request;

import com.skybot.event.notice.OB11FriendAddNoticeEvent;
import com.skybot.event.request.OB11FriendAddRequestEvent;

public interface OB11FriendAddRequestEventListener
{
	/**
	 * 好友申请请求事件
	 * @param ob11FriendAddRequestEvent 好友申请请求事件数据类
	 */
	void onFriendAddedRequest(OB11FriendAddRequestEvent ob11FriendAddRequestEvent);
}
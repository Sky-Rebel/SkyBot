package com.skybot.event.handling.listener.request;

import com.skybot.event.notice.OB11FriendAddNoticeEvent;
import com.skybot.event.request.OB11GroupInviteRequestEvent;

public interface OB11GroupInviteRequestEventListener
{
	/**
	 * 入群邀请请求事件
	 * @param ob11GroupInviteRequestEvent 入群邀请请求事件数据类
	 */
	void onGroupInvitedRequest(OB11GroupInviteRequestEvent ob11GroupInviteRequestEvent);
}

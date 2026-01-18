package com.skybot.event.handling.listener.request;

import com.skybot.event.notice.OB11FriendAddNoticeEvent;
import com.skybot.event.request.OB11GroupAddRequestEvent;

public interface OB11GroupAddRequestEventListener
{
	/**
	 * 入群申请请求事件
	 * @param ob11GroupAddRequestEvent 入群申请请求事件数据类
	 */
	void onGroupAddedRequest(OB11GroupAddRequestEvent ob11GroupAddRequestEvent);
}
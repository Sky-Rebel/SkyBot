package com.skybot.event.handling.listener.message;

import com.skybot.event.message.OB11GroupMessageEvent;

public interface OB11GroupMessageEventListener
{
	/**
	 * 群组消息事件
	 * @param ob11GroupMessageEvent 群组消息事件数据类
	 */
	void onGroupMessage(OB11GroupMessageEvent ob11GroupMessageEvent);
}

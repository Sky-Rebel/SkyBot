package com.skybot.event.handling.listener.message;

import com.skybot.event.message.OB11PrivateMessageEvent;

public interface OB11PrivateMessageEventListener
{
	/**
	 * 私聊消息事件
	 * @param ob11PrivateMessageEvent 私聊消息事件数据类
	 */
	void onPrivateMessage(OB11PrivateMessageEvent ob11PrivateMessageEvent);
}
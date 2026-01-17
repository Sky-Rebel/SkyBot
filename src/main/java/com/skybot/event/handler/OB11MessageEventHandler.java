package com.skybot.event.handler;

import com.skybot.api.MessageManage;
import com.skybot.event.message.OB11GroupMessageEvent;
import com.skybot.event.message.OB11PrivateMessageEvent;

public class OB11MessageEventHandler
{
	/**
	 * 群组消息处理器
	 * @param ob11GroupMessageEvent 群组消息事件数据类
	 */
	public static void onGroupMessage(OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		if (ob11GroupMessageEvent.rawMessage.equals("test"))
			MessageManage.sendGroupTextMessage(ob11GroupMessageEvent.groupId, "SkyBot Test Success！\n");
	}

	/**
	 * 私聊消息处理器
	 * @param ob11PrivateMessageEvent 私聊消息事件数据类
	 */
	public static void onPrivateMessage(OB11PrivateMessageEvent ob11PrivateMessageEvent) {}
}

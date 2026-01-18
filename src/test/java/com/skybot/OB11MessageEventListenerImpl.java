package com.skybot;

import com.skybot.api.MessageManage;
import com.skybot.event.handling.listener.message.OB11MessageEventListener;
import com.skybot.event.message.OB11GroupMessageEvent;
import com.skybot.event.message.OB11PrivateMessageEvent;

public class OB11MessageEventListenerImpl implements OB11MessageEventListener
{
	@Override
	public void onGroupMessage(OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		if (ob11GroupMessageEvent.rawMessage.equals("test"))
			MessageManage.sendGroupTextMessage(ob11GroupMessageEvent.groupId, "Skybot Test Success");
	}

	@Override
	public void onPrivateMessage(OB11PrivateMessageEvent ob11PrivateMessageEvent)
	{

	}
}
package com.skybot;

import com.skybot.bot.api.OB11MessageService;
import com.skybot.bot.event.handling.listener.message.OB11MessageEventListener;
import com.skybot.bot.event.message.OB11GroupMessageEvent;
import com.skybot.bot.event.message.OB11PrivateMessageEvent;

public class OB11MessageEventListenerImpl implements OB11MessageEventListener
{
	@Override
	public void onGroupMessage(OB11GroupMessageEvent ob11GroupMessageEvent)
	{
		if (ob11GroupMessageEvent.rawMessage.equals("test"))
			OB11MessageService.sendGroupTextMessage(ob11GroupMessageEvent.groupId, "Skybot Test Success");
	}

	@Override
	public void onPrivateMessage(OB11PrivateMessageEvent ob11PrivateMessageEvent)
	{
		if (ob11PrivateMessageEvent.rawMessage.equals("test"))
			OB11MessageService.sendPrivateTextMessage(ob11PrivateMessageEvent.userId, "Skybot Test Success");
	}
}
package com.github.sky_rebel.skybot.event.handling.dispatcher;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;
import com.github.sky_rebel.skybot.event.handling.handler.OB11MessageEventHandler;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;
import org.json.JSONObject;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

public class OB11MessageEventDispatcher
{
	public static void dispatch(Bot bot, JSONObject json)
	{
		try
		{
			OB11BaseMessageEvent ob11BaseMessageEvent = OB11BaseMessageEvent.getInstance(bot, json);
			if (ob11BaseMessageEvent != null)
			{
				if (ob11BaseMessageEvent instanceof OB11GroupMessageEvent ob11GroupMessageEvent)
				{
					OB11MessageEventHandler.onGroupMessage(bot, ob11GroupMessageEvent);
				}
				else
				{
					OB11MessageEventHandler.onPrivateMessage(bot, (OB11PrivateMessageEvent) ob11BaseMessageEvent);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error("消息事件分发异常", e);
		}
	}
}
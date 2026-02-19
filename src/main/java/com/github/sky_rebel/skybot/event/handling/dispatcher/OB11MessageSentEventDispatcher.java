package com.github.sky_rebel.skybot.event.handling.dispatcher;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.OB11BaseMessageSentEvent;
import com.github.sky_rebel.skybot.event.handling.handler.OB11MessageSentEventHandler;
import com.github.sky_rebel.skybot.event.message_sent.OB11GroupMessageSentEvent;
import com.github.sky_rebel.skybot.event.message_sent.OB11PrivateMessageSentEvent;
import org.json.JSONObject;

import static com.github.sky_rebel.skybot.event.handling.dispatcher.OB11EventDispatcher.LOGGER;

public class OB11MessageSentEventDispatcher
{
	public static void dispatch(Bot bot, JSONObject json)
	{
		try
		{
			OB11BaseMessageSentEvent ob11BaseMessageSentEvent = OB11BaseMessageSentEvent.getInstance(bot, json);
			if (ob11BaseMessageSentEvent != null)
			{
				if (ob11BaseMessageSentEvent instanceof OB11GroupMessageSentEvent ob11GroupMessageSentEvent)
				{
					OB11MessageSentEventHandler.onGroupSentMessage(bot, ob11GroupMessageSentEvent);
				}
				else
				{
					OB11MessageSentEventHandler.onPrivateSentMessage(bot, (OB11PrivateMessageSentEvent) ob11BaseMessageSentEvent);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error("消息自发事件分发异常", e);
		}
	}
}
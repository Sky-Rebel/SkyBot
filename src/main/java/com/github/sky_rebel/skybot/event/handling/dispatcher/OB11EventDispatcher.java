package com.github.sky_rebel.skybot.event.handling.dispatcher;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.OB11BaseEvent;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import com.github.sky_rebel.skybot.util.logger.Logger;
import org.json.JSONObject;

public class OB11EventDispatcher
{
	public static final Logger LOGGER = SkybotLogger.getLogger("OB11EventDispatcher");

	public static void dispatch(Bot bot, JSONObject json)
	{
		final String postType = json.optString("post_type");

		try
		{
			OB11BaseEvent.Type type = OB11BaseEvent.Type.valueOf(postType.toUpperCase());
			switch (type)
			{
				case NOTICE -> OB11NoticeEventDispatcher.dispatch(bot, json);

				case MESSAGE -> OB11MessageEventDispatcher.dispatch(bot, json);

				case REQUEST -> OB11RequestEventDispatcher.dispatch(bot, json);

				case META_EVENT -> OB11MetaEventDispatcher.dispatch(bot, json);

				case MESSAGE_SENT -> OB11MessageSentEventDispatcher.dispatch(bot, json);
			}
		}
		catch (IllegalArgumentException e)
		{
			LOGGER.warn("未知基础事件类型 -> {}", postType);
		}
		catch (Exception e)
		{
			LOGGER.error("基础事件分发异常", e);
		}
	}
}
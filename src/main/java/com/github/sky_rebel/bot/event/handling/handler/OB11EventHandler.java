package com.github.sky_rebel.bot.event.handling.handler;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.event.OB11BaseEvent;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OB11EventHandler
{
	public static final Logger LOGGER = LoggerFactory.getLogger(OB11EventHandler.class);

	/**
	 * 分发底层事件
	 * @param ob11EventPostData Napcat客户端上报的原始数据
	 */
	public static void dispatch(Bot bot, JSONObject ob11EventPostData)
	{
		final String postType = ob11EventPostData.getString("post_type");
		OB11BaseEvent.EventType eventType;
		if (postType.equals("meta_event"))
			eventType = OB11BaseEvent.EventType.META;
		else
			eventType = OB11BaseEvent.EventType.valueOf(postType.toUpperCase());
		switch (eventType)
		{
			case META -> OB11MetaEventHandler.dispatch(bot, ob11EventPostData);
			case NOTICE -> OB11NoticeEventHandler.dispatch(bot, ob11EventPostData);
			case MESSAGE -> OB11MessageEventHandler.dispatch(bot, ob11EventPostData);
			case REQUEST -> OB11RequestEventHandler.dispatch(bot, ob11EventPostData);
			case MESSAGE_SENT -> OB11MessageSentEventHandler.dispatch(bot, ob11EventPostData);
			default -> LOGGER.warn("未知基础事件类型！");
		}
	}
}
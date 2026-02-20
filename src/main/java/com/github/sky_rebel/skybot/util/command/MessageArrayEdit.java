package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;
import com.github.sky_rebel.skybot.msg.element.OB11AtMsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11TextMsgElement;
import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MessageArrayEdit
{
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private static final Logger LOGGER = SkybotLogger.getLogger(MessageArrayEdit.class);

	private static final List<OB11MsgElement> MESSAGE_ELEMENT_LIST = new LinkedList<>();

	private final OB11BaseMessageEvent ob11BaseMessageEvent;

	public MessageArrayEdit(OB11BaseMessageEvent event)
	{
		ob11BaseMessageEvent = event;
	}

	public MessageArrayEdit addAtAll()
	{
		return addAt("all");
	}

	private MessageArrayEdit addAt(long id)
	{
		return addAt(String.valueOf(id));
	}

	private MessageArrayEdit addAt(String id)
	{
		OB11AtMsgElement ob11AtMsgElement = new OB11AtMsgElement();
		ob11AtMsgElement.setQQ(id);
		MESSAGE_ELEMENT_LIST.add(ob11AtMsgElement);
		return this;
	}

	public MessageArrayEdit addTextAndLine(String text)
	{
		return addTextAndLine(text, 1);
	}

	public MessageArrayEdit addTextAndLine(String text, int count)
	{
		return addText(text).addLine(count);
	}

	public MessageArrayEdit addText(String text)
	{
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		MESSAGE_ELEMENT_LIST.add(ob11TextMsgElement);
		return this;
	}

	public MessageArrayEdit addTab()
	{
		return addTab(1);
	}

	public MessageArrayEdit addTab(int count)
	{
		addText("-".repeat(count));
		return this;
	}

	public MessageArrayEdit addLine()
	{
		return addLine(1);
	}

	public MessageArrayEdit addLine(int count)
	{
		addText("\n".repeat(count));
		return this;
	}

	public MessageArrayEdit addTime()
	{
		return addText(SIMPLE_DATE_FORMAT.format(new Date()));
	}


	public long sendMessage()
	{
		return sendMessage(ob11BaseMessageEvent);
	}

	private long sendMessage(OB11BaseMessageEvent event)
	{
		if (event == null)
		{
			LOGGER.error("Event实例为空");
			return -1;
		}
		if (event.bot == null)
		{
			LOGGER.error("Bot实例为空");
			return -1;
		}
		boolean isGroupMsgEvent = event instanceof OB11GroupMessageEvent;
		if (isGroupMsgEvent)
		{
			return event.bot.getOB11MessageApiService().sendGroupMessage(((OB11GroupMessageEvent) event).groupId, MESSAGE_ELEMENT_LIST);
		}
		else
		{
			return event.bot.getOB11MessageApiService().sendGroupMessage(((OB11PrivateMessageEvent) event).userId, MESSAGE_ELEMENT_LIST);
		}
	}
}
package com.github.sky_rebel.skybot.util.command;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.event.OB11BaseMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11GroupMessageEvent;
import com.github.sky_rebel.skybot.event.message.OB11PrivateMessageEvent;
import com.github.sky_rebel.skybot.msg.element.OB11AtMsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11TextMsgElement;
import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MessageArrayEdit
{
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private static final Logger LOGGER = SkybotLogger.getLogger(MessageArrayEdit.class);

	private static final List<OB11MsgElement> MESSAGE_ELEMENT_LIST = new LinkedList<>();

	private OB11BaseMessageEvent ob11BaseMessageEvent;

	private Bot bot;

	public MessageArrayEdit(Bot bot)
	{
		this.bot = bot;
	}

	public MessageArrayEdit(OB11BaseMessageEvent event)
	{
		this.ob11BaseMessageEvent = event;
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
		addText("-".repeat(count)).addLine();
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

	public long sendGroupMessage(long groupId)
	{
		return sendMessage(bot, true, groupId, 0);
	}

	public long sendPrivateMessage(long userId)
	{
		return sendMessage(bot, false, 0, userId);
	}

	public long sendMessageToMaster()
	{
		if (bot == null)
		{
			LOGGER.error("Bot实例为空，无法获取主人ID");
			return -1;
		}
		long masterId = bot.getBotConfig().skybotConfig.master;
		if (masterId == -1)
		{
			LOGGER.error("主人ID未配置，无法发送消息");
			return -1;
		}
		return sendMessage(bot, false, 0, masterId);
	}

	public long sendMessageToMainGroup()
	{
		if (bot == null)
		{
			LOGGER.error("Bot实例为空，无法获取主群ID");
			return -1;
		}
		long mainGroupId = bot.getBotConfig().skybotConfig.mainGroup;
		if (mainGroupId == -1)
		{
			LOGGER.error("主群ID未配置，无法发送消息");
			return -1;
		}
		return sendMessage(bot, true, mainGroupId, 0);
	}

	private long sendMessage(Bot bot, boolean isGroup, long groupId, long userId)
	{
		if (bot == null)
		{
			LOGGER.error("Bot实例为空，无法发送消息");
			return -1;
		}
		if (isGroup)
		{
			return bot.getMessageApiService().sendGroupMessage(groupId, MESSAGE_ELEMENT_LIST);
		}
		else
		{
			return bot.getMessageApiService().sendPrivateMessage(userId, MESSAGE_ELEMENT_LIST);

		}
	}

	private long sendMessage(OB11BaseMessageEvent event)
	{
		if (event == null)
		{
			LOGGER.error("Event实例为空，无法发送消息");
			return -1;
		}
		if (event.bot == null)
		{
			LOGGER.error("Bot实例为空，无法发送消息");
			return -1;
		}
		boolean isGroupMsgEvent = event instanceof OB11GroupMessageEvent;
		List<OB11MsgElement> msgElementList = new ArrayList<>(MESSAGE_ELEMENT_LIST);
		MESSAGE_ELEMENT_LIST.clear();
		if (isGroupMsgEvent)
		{
			return event.bot.getMessageApiService().sendGroupMessage(((OB11GroupMessageEvent) event).groupId, msgElementList);
		}
		else
		{
			return event.bot.getMessageApiService().sendPrivateMessage(((OB11PrivateMessageEvent) event).userId, msgElementList);
		}
	}

	public List<OB11MsgElement> getMessageElementList()
	{
		return MESSAGE_ELEMENT_LIST;
	}
}
package com.skybot;

import com.skybot.bot.Bot;
import com.skybot.bot.BotConfig;
import com.skybot.event.handling.manage.OB11MessageEventListenerManage;
import com.skybot.event.handling.manage.OB11MessageSentEventListenerManage;
import com.skybot.event.handling.manage.OB11MetaEventListenerManage;
import com.skybot.event.handling.manage.OB11NoticeEventListenerManage;

public class BotTest
{
	public static void main()
	{
		OB11NoticeEventListenerManage.registerListener(new OB11NoticeEventListenerImpl());
		OB11MessageEventListenerManage.registerListener(new OB11MessageEventListenerImpl());
		OB11MetaEventListenerManage.registerListener(new OB11MetaEventListenerImpl());
		OB11MessageSentEventListenerManage.registerListener(new OB11MessageSentEventListenerImpl());
		new Bot().start();
	}
}
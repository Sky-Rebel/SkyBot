import com.skybot.OB11NoticeEventListenerImpl;
import com.skybot.api.MessageManage;
import com.skybot.bot.Bot;
import com.skybot.bot.BotConfig;
import com.skybot.event.handling.listener.message.OB11GroupMessageEventListener;
import com.skybot.event.handling.manage.OB11MessageEventListenerManage;
import com.skybot.event.handling.manage.OB11NoticeEventListenerManage;
import com.skybot.event.message.OB11GroupMessageEvent;

public static void main()
{
	OB11GroupMessageEventListener ob11GroupMessageEventListener = new OB11GroupMessageEventListener()
	{
		@Override
		public void onGroupMessage(OB11GroupMessageEvent ob11GroupMessageEvent)
		{
			if (ob11GroupMessageEvent.rawMessage.equals("test"))
			{
				MessageManage.sendGroupTextMessage(ob11GroupMessageEvent.groupId, "Skybot Test Success");
			}
		}
	};
	OB11NoticeEventListenerManage.registerListener(new OB11NoticeEventListenerImpl());
	OB11MessageEventListenerManage.registerListener(ob11GroupMessageEventListener);
	BotConfig botConfig = new BotConfig();
	botConfig.botId = 1234567;
	Bot bot = new Bot(botConfig);
	bot.start();
}
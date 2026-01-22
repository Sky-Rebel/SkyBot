import com.skybot.OB11MessageEventListenerImpl;
import com.skybot.OB11NoticeEventListenerImpl;
import com.skybot.bot.Bot;
import com.skybot.bot.event.handling.manage.OB11MessageEventListenerManage;
import com.skybot.bot.event.handling.manage.OB11NoticeEventListenerManage;

public static void main() throws InterruptedException
{
	OB11NoticeEventListenerManage.registerListener(new OB11NoticeEventListenerImpl());
	OB11MessageEventListenerManage.registerListener(new OB11MessageEventListenerImpl());
	Bot bot = new Bot();
	bot.start();
}
import com.skybot.Bot;
import com.skybot.BotConfig;

public static void main()
{
	BotConfig botConfig = new BotConfig();
	botConfig.botId = 2141650971;
	Bot bot = new Bot(botConfig);
	bot.start();
}

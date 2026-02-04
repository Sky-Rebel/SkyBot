import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.util.logger.Logger;

public static void main()
{
	Logger logger = new Logger(Bot.class);
	logger.debug("debug test");
	logger.info("info test");
	logger.warn("warn test");
	logger.error("error test");
}
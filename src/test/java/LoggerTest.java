import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import com.github.sky_rebel.skybot.util.logger.Logger;

public static void main()
{
	Logger logger = SkybotLogger.getLogger(Bot.class);
	logger.debug("debug test");
	logger.info("info test");
	logger.warn("warn test");
	logger.error("error test");
}
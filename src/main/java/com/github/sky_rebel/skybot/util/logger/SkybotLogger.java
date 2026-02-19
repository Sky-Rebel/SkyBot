package com.github.sky_rebel.skybot.util.logger;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SkybotLogger implements Logger
{
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("[yyyy-MM-dd HH-mm-ss.SSS]");

	private static final String LOGGING_PATTERN = "%s%s%s %s%5s%s%s:%s %s%s%s%s ->%s %s%n";

	private static final PrintStream ERROR_STREAM = System.err;

	private static final PrintStream OUTPUT_STREAM = System.out;

	private static final String COLOR_RESET = "\033[0m";

	private static final String COLOR_DATETIME = "\033[38;2;117;124;130m";

	private static final String COLOR_TAG = "\033[38;2;165;153;240m";

	private static final String COLOR_SEPARATOR = "\033[38;2;189;189;189m";

	private String loggerTag;

	public enum LoggerLevel
	{
		/**
		 * DEBUG-青色
		 */
		DEBUG("\033[38;2;86;182;194m"),

		/**
		 * INFO-绿色
		 */
		INFO("\033[38;2;129;199;132m"),

		/**
		 * WARN-黄色
		 */
		WARN("\033[38;2;255;198;79m"),

		/**
		 * ERROR-红色
		 */
		ERROR("\033[38;2;239;83;80m");

		private final String color;

		LoggerLevel(String color)
		{
			this.color = color;
		}

		public String getColor()
		{
			return color;
		}
	}

	public static SkybotLogger getLogger(Class<?> clazz)
	{
		return getLogger(clazz.getName());
	}

	public static SkybotLogger getLogger(String name)
	{
		SkybotLogger skybotLogger = new SkybotLogger();
		skybotLogger.loggerTag = name;
		return skybotLogger;
	}

	@Override
	public void debug(String text)
	{
		log(LoggerLevel.DEBUG, text, null);
	}

	@Override
	public void debug(String text, Object... args)
	{
		log(LoggerLevel.DEBUG, formatMessage(text, args), null);
	}

	@Override
	public void info(String text)
	{
		log(LoggerLevel.INFO, text, null);
	}

	@Override
	public void info(String text, Object... args)
	{
		log(LoggerLevel.INFO, formatMessage(text, args), null);
	}

	@Override
	public void warn(String text)
	{
		log(LoggerLevel.WARN, text, null);
	}

	@Override
	public void warn(String text, Object... args)
	{
		log(LoggerLevel.WARN, formatMessage(text, args), null);
	}

	@Override
	public void error(String text)
	{
		log(LoggerLevel.ERROR, text, null);
	}

	public void error(Throwable throwable)
	{
		log(LoggerLevel.ERROR, null, throwable);
	}

	@Override
	public void error(String text, Throwable throwable)
	{
		log(LoggerLevel.ERROR, text, throwable);
	}

	@Override
	public void error(String text, Object... args)
	{
		log(LoggerLevel.ERROR, formatMessage(text, args), null);
	}

	@Override
	public void error(String text, Throwable throwable, Object... args)
	{
		log(LoggerLevel.ERROR, formatMessage(text, args), throwable);
	}

	private String formatMessage(String template, Object... args)
	{
		if (template == null || template.isEmpty())
		{
			return "";
		}
		if (args == null || args.length == 0)
		{
			return template;
		}
		StringBuilder stringBuilder = new StringBuilder(template);
		int argIndex = 0;
		int placeholderIndex;
		while ((placeholderIndex = stringBuilder.indexOf("{}")) != -1 && argIndex < args.length)
		{
			String argStr = args[argIndex] == null ? "null" : args[argIndex].toString();
			stringBuilder.replace(placeholderIndex, placeholderIndex + 2, argStr);
			argIndex++;
		}
		return stringBuilder.toString();
	}

	private void log(LoggerLevel level, String text, Throwable throwable)
	{
		String datetime = SIMPLE_DATE_FORMAT.format(new Date());
		String logContent = (text == null) ? "" : text;
		String colorCode = level.getColor();
		String levelName = level.name();
		String logMessage = String.format
		(
			LOGGING_PATTERN,
			COLOR_DATETIME,
			datetime,
			COLOR_RESET,
			level.getColor(),
			levelName,
			COLOR_RESET,
			COLOR_SEPARATOR,
			COLOR_RESET,
			COLOR_TAG,
			loggerTag,
			COLOR_RESET,
			COLOR_SEPARATOR,
			COLOR_RESET,
			logContent
		);
		OUTPUT_STREAM.println(logMessage);
		if (throwable != null)
		{
			ERROR_STREAM.println(colorCode);
			throwable.printStackTrace(ERROR_STREAM);
			ERROR_STREAM.print(COLOR_RESET);
		}
	}
}
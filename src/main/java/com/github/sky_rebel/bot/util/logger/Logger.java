package com.github.sky_rebel.bot.util.logger;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements ILogger
{
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("[yyyy-MM-dd HH-mm-ss.SSS]");

	private static final String LOGGING_PATTERN = "%s%s%s %s%5s%s%s:%s %s%s%s%s ->%s %s%n";

	private static final PrintStream ERROR_STREAM = System.err;

	private static final PrintStream OUTPUT_STREAM = System.out;

	private static final String COLOR_RESET = "\033[0m";

	private static final String COLOR_DATETIME = "\033[38;2;117;124;130m";

	private static final String COLOR_TAG = "\033[38;2;165;153;240m";

	private static final String COLOR_SEPARATOR = "\033[38;2;189;189;189m";

	private final String loggerTag;

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

	public Logger(String name)
	{
		loggerTag = name;
	}

	public Logger(Class<?> clazz)
	{
		loggerTag= clazz.getName();
	}

	@Override
	public void debug(String text)
	{
		log(LoggerLevel.DEBUG, text, null);
	}

	@Override
	public void info(String text)
	{
		log(LoggerLevel.INFO, text, null);
	}

	@Override
	public void warn(String text)
	{
		log(LoggerLevel.WARN, text, null);
	}

	@Override
	public void error(String text)
	{
		log(LoggerLevel.ERROR, text, null);
	}

	@Override
	public void error(String text, Throwable throwable)
	{
		log(LoggerLevel.ERROR, text, throwable);
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
package com.github.sky_rebel.bot.util.logger;

public interface ILogger
{
	void debug(String text);

	void info(String text);

	void warn(String text);

	void error(String text);

	void error(String text, Throwable throwable);
}
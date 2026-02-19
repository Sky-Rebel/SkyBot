package com.github.sky_rebel.skybot.util.logger;

public interface Logger
{
	void debug(String text);

	void debug(String text, Object... args);

	void info(String text);

	void info(String text, Object... args);

	void warn(String text);

	void warn(String text, Object... args);

	void error(String text);

	void error(Throwable throwable);

	void error(String text, Throwable throwable);

	void error(String text, Object... args);

	void error(String text, Throwable throwable, Object... args);
}
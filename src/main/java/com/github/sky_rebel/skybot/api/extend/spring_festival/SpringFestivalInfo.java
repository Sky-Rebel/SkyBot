package com.github.sky_rebel.skybot.api.extend.spring_festival;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class SpringFestivalInfo
{
	private static final int currentYear;

	static
	{
		currentYear = LocalDate.now().getYear();
	}

	public static LocalDateTime getSpringFestivalLocalDateTime()
	{
		ChineseDate springFestival = new ChineseDate(currentYear, 1, 1);
		return springFestival.getGregorianDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static LocalDateTime getSpringFestivalLocalDateTime(int year)
	{
		ChineseDate springFestival = new ChineseDate(year, 1, 1);
		return springFestival.getGregorianDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static long getCountdownToSpringFestival()
	{
		return getCountdownToSpringFestival(ChronoUnit.MILLIS);
	}

	public static long getCountdownToSpringFestival(ChronoUnit chronoUnit)
	{
		LocalDateTime springFestivalDate = getSpringFestivalLocalDateTime();
		LocalDateTime now = LocalDateTime.now();
		return chronoUnit.between(now, springFestivalDate);
	}

	public static long getCountdownToSpringFestival(int year, ChronoUnit chronoUnit)
	{
		LocalDateTime springFestivalDate = getSpringFestivalLocalDateTime(year);
		LocalDateTime now = LocalDateTime.now();
		return chronoUnit.between(now, springFestivalDate);
	}

	public static boolean isSpringFestivalToday()
	{
		ChineseDate today = new ChineseDate(DateUtil.date());
		return today.getMonth() == 1 && today.getDay() == 1;
	}

	public static String getCountdownToSpringFestivalString()
	{
		var countdown = getCountdownToSpringFestival();
		var formatDateTime = formatMillisToDateTime(countdown);
		if (countdown < 0)
			return currentYear + "春节已过" + formatDateTime;
		else
			return currentYear + "春节尚有" + formatDateTime;
	}

	public static String formatMillisToDateTime(long millis)
	{
		if (millis < 0)
		{
			millis = Math.abs(millis);
		}
		final long MILLIS_PER_SECOND = 1000;
		final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
		final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
		final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
		final long MILLIS_PER_MONTH = 30 * MILLIS_PER_DAY;
		long months = millis / MILLIS_PER_MONTH;
		millis %= MILLIS_PER_MONTH;
		long days = millis / MILLIS_PER_DAY;
		millis %= MILLIS_PER_DAY;
		long hours = millis / MILLIS_PER_HOUR;
		millis %= MILLIS_PER_HOUR;
		long minutes = millis / MILLIS_PER_MINUTE;
		millis %= MILLIS_PER_MINUTE;
		long seconds = millis / MILLIS_PER_SECOND;
		long remainMillis = millis % MILLIS_PER_SECOND;
		return String.format("%d月%d天%d时%d分%d秒%d毫秒", months, days, hours, minutes, seconds, remainMillis);
	}
}
package com.github.sky_rebel.bot.api.extend.spring_festival;

import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.util.logger.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SpringFestivalCountdown
{
	private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);

	private static final Logger LOGGER = new Logger(SpringFestivalCountdown.class);

	public static void startCountdown(int seconds)
	{
		if (seconds > 60)
		{
			LOGGER.error("倒计时秒数不得超于分制秒数");
			return;
		}
		long springFestivalCountdown = SpringFestivalInfo.getCountdownToSpringFestival() - (seconds * 1000L);
		if (springFestivalCountdown < 0)
		{
			LOGGER.error("倒计时时机已过，无法启动任务");
			return;
		}
		AtomicInteger atomicInteger = new AtomicInteger(seconds);
		AtomicReference<ScheduledFuture<?>> atomicReference = new AtomicReference<>();
		Runnable countdownTask = () ->
		{
			int currentInteger = atomicInteger.getAndDecrement();
			if (currentInteger > 0)
			{
				sendMessageToFriendList(currentInteger);
			}
			if (atomicInteger.get() < 0)
			{
				ScheduledFuture<?> scheduledFuture = atomicReference.get();
				if (scheduledFuture != null && !scheduledFuture.isCancelled())
				{
					scheduledFuture.cancel(true);
				}
			}
		};
		ScheduledFuture<?> scheduledFuture = null;
		scheduledFuture = SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(countdownTask, springFestivalCountdown, 1000, TimeUnit.MILLISECONDS);
		atomicReference.set(scheduledFuture);
	}

	private static void sendMessageToFriendList(int currentInteger)
	{
		Bot bot = Bot.getFirstBot();
		if (bot != null)
		{
			String msg = switch (currentInteger)
			{
				case 9 -> "9";
				case 8 -> "8";
				case 7 -> "7";
				case 6 -> "6";
				case 5 -> "5";
				case 4 -> "4";
				case 3 -> "3";
				case 2 -> "2";
				case 1 -> "1";
				case 0 -> "本次为春节倒计时测试，感谢参与！";
				default -> "";
			};
			bot.getOB11MessageApiService().sendTextMessageToFriendList(msg);
		}
	}
}
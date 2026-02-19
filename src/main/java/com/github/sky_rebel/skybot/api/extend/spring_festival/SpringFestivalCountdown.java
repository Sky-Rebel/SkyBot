package com.github.sky_rebel.skybot.api.extend.spring_festival;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import com.github.sky_rebel.skybot.util.logger.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SpringFestivalCountdown
{
	private static final Logger LOGGER = SkybotLogger.getLogger(SpringFestivalCountdown.class);

	private static final Map<Integer, String> COUNTDOWN_MSG_MAP = new HashMap<>();

	private static final ScheduledExecutorService SCHEDULED_THREAD = Executors.newScheduledThreadPool(1);

	private static final ExecutorService MESSAGE_POOL = new ThreadPoolExecutor(258, 516, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());

	static
	{
		COUNTDOWN_MSG_MAP.put(9, "九衢灯火照新年，锦绣山河共此天\u2605");
		COUNTDOWN_MSG_MAP.put(8, "八荒同庆春光好，瑞霭祥云护岁安\u2601");
		COUNTDOWN_MSG_MAP.put(7, "七襄云锦呈佳瑞，一夕东风换旧年\uD83C\uDF19");
		COUNTDOWN_MSG_MAP.put(6, "六龙回日开新景，吉语声声到耳边\u2728");
		COUNTDOWN_MSG_MAP.put(5, "五云深处春光近，福自天来喜自绵\uD83C\uDF3F");
		COUNTDOWN_MSG_MAP.put(4, "四时清晏人常乐，家国安宁岁月延\uD83C\uDF31");
		COUNTDOWN_MSG_MAP.put(3, "三春早报平安信，万户欢声接瑞烟\uD83C\uDF0C");
		COUNTDOWN_MSG_MAP.put(2, "二分明月迎新岁，一盏清茶庆团圆\uD83C\uDF19");
		COUNTDOWN_MSG_MAP.put(1, "一帘春色随风至，万事顺遂福无边\uD83C\uDF38");
		COUNTDOWN_MSG_MAP.put(0, "华灯初上新春至，共祝人间好岁年\uD83C\uDF86");
	}

	public static void startCountdown(long countdown, int seconds)
	{
		AtomicInteger atomicInteger = new AtomicInteger(seconds);
		AtomicReference<ScheduledFuture<?>> atomicReference = new AtomicReference<>();
		long springFestivalCountdown = countdown - (seconds * 1000L);
		Runnable countdownTask = () ->
		{
			int currentInteger = atomicInteger.getAndDecrement();
			if (currentInteger >= 0 && currentInteger <= seconds && currentInteger <= 9)
			{
				String msg = COUNTDOWN_MSG_MAP.getOrDefault(currentInteger, "");
				if (!msg.isEmpty())
				{
					MESSAGE_POOL.submit(() ->
					{
						try
						{
							Bot bot = Bot.getFirstBot();
							if (bot != null)
							{
								bot.getOB11MessageApiService().sendTextMessageToFriendList(msg);
								bot.getOB11MessageApiService().sendTextMessageToGroupList(msg);
							}
						}
						catch (Exception e)
						{
							LOGGER.error("消息发送任务执行出现异常！", e);
						}
					});
				}
			}
			if (currentInteger < 0)
			{
				if (atomicReference.get() != null && !atomicReference.get().isCancelled())
				{
					atomicReference.get().cancel(false);
				}
			}
		};
		atomicReference.set(SCHEDULED_THREAD.scheduleAtFixedRate(countdownTask, springFestivalCountdown, 1000, TimeUnit.MILLISECONDS));
	}

	private static void shutdownThreadPool()
	{
		MESSAGE_POOL.shutdown();
		try
		{
			if (!MESSAGE_POOL.awaitTermination(5, TimeUnit.SECONDS))
			{
				MESSAGE_POOL.shutdownNow();
			}
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			MESSAGE_POOL.shutdownNow();
		}
		SCHEDULED_THREAD.shutdown();
		try
		{
			if (!SCHEDULED_THREAD.awaitTermination(5, TimeUnit.SECONDS))
			{
				SCHEDULED_THREAD.shutdownNow();
			}
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			SCHEDULED_THREAD.shutdownNow();
		}
	}
}
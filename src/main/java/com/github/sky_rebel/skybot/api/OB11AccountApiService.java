package com.github.sky_rebel.skybot.api;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.BotApiService;
import com.github.sky_rebel.skybot.api.data.account.OB11FriendInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OB11AccountApiService
{
	private final Bot bot;

	public OB11AccountApiService(Bot bot)
	{
		this.bot = bot;
	}

	private enum OB11AccountApiPath
	{

		GET_FRIEND_LIST("/get_friend_list"),

		GET_MINI_APP_ARK("/get_mini_app_ark"),

		SEND_LIKE("/send_like");

		private final String value;

		OB11AccountApiPath(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return this.value;
		}
	}

	/**
	 * 点赞所有好友并且发送消息（如果点赞成功）
	 * @return API响应结果列表
	 */
	public List<BotApiService.APIRequestResult> sendLikeAndMessageToFriendListIfSuccess(String text)
	{
		return sendLikeAndMessageToFriendList(20, true, text);
	}

	/**
	 * 点赞所有好友并且发送消息（如果点赞成功）
	 * @param times 点赞次数
	 * @return API响应结果列表
	 */
	public List<BotApiService.APIRequestResult> sendLikeAndMessageToFriendListIfSuccess(int times, String text)
	{
		return sendLikeAndMessageToFriendList(times, true, text);
	}

	/**
	 * 点赞所有好友
	 * @return API响应结果列表
	 */
	public List<BotApiService.APIRequestResult> sendLikeToFriendList()
	{
		return sendLikeAndToFriendList(20);
	}

	/**
	 * 点赞所有好友
	 * @param times 点赞次数
	 * @return API响应结果列表
	 */
	public List<BotApiService.APIRequestResult> sendLikeAndToFriendList(int times)
	{
		return sendLikeAndMessageToFriendList(times, false, null);
	}

	/**
	 * 点赞并且发送消息向所有好友
	 * @param times 点赞次数
	 * @return API响应结果列表
	 */
	public List<BotApiService.APIRequestResult> sendLikeAndMessageToFriendList(int times, boolean isSuccessSend, String text)
	{
		List<Long> friendIdList = getFriendIdList();
		List<BotApiService.APIRequestResult> apiRequestResultList = new ArrayList<>();
		AtomicReference<OB11MessageApiService> atomicReference = new AtomicReference<>();
		if (isSuccessSend)
		{
			atomicReference.set(bot.getMessageApiService());
		}
		if (!friendIdList.isEmpty())
		{
			friendIdList.forEach(friendId ->
			{
				BotApiService.APIRequestResult apiRequestResult = sendLike(friendId, times);
				if (apiRequestResult.isSuccess)
				{
					atomicReference.get().sendPrivateTextMessage(friendId, text);
				}
				apiRequestResultList.add(apiRequestResult);
			});
		}
		return apiRequestResultList;
	}

	/**
	 * 点赞好友并且发送消息（如果点赞成功）
	 * @param userId 用户ID
	 * @return API响应结果
	 */
	public BotApiService.APIRequestResult sendLikeAndMessageIfSuccess(long userId, String text)
	{
		return sendLikeAndMessageIfSuccess(userId, 20, text);
	}

	/**
	 * 点赞好友并且发送消息（如果点赞成功）
	 * @param userId 用户ID
	 * @param times 点赞次数
	 * @return API响应结果
	 */
	public BotApiService.APIRequestResult sendLikeAndMessageIfSuccess(long userId, int times, String text)
	{
		OB11MessageApiService ob11MessageApiService = bot.getMessageApiService();
		BotApiService.APIRequestResult apiRequestResult = sendLike(userId, times);
		if (apiRequestResult.isSuccess)
		{
			if (text != null)
			{
				ob11MessageApiService.sendPrivateTextMessage(userId, text);
			}
		}
		return apiRequestResult;
	}

	/**
	 * 点赞好友
	 * @param userId 用户ID
	 * @return API响应结果
	 */
	public BotApiService.APIRequestResult sendLike(long userId)
	{
		return sendLike(userId, 20);
	}

	/**
	 * 点赞好友
	 * @param userId 用户ID
	 * @param times 点赞次数
	 * @return API响应结果
	 */
	public BotApiService.APIRequestResult sendLike(long userId, int times)
	{
		BotApiService botApiService = new BotApiService(bot, OB11AccountApiPath.SEND_LIKE.getValue());
		JSONObject rootObject = new JSONObject();
		rootObject.put("user_id", userId);
		rootObject.put("times", times);
		return botApiService.sendRequest(rootObject.toString());
	}

	/**
	 * 获取好友ID列表
	 * @return 好友ID列表
	 */
	public List<Long> getFriendIdList()
	{
		return getFriendIdList(true);
	}

	/**
	 * 获取好友ID列表
	 * @param noCache 是否不使用缓存
	 * @return 好友ID列表
	 */
	public List<Long> getFriendIdList(boolean noCache)
	{
		List<Long> friendIdList = new ArrayList<>();
		List<OB11FriendInfo> ob11FriendInfoList = getFriendInfoList(noCache);
		if (!ob11FriendInfoList.isEmpty())
		{
			ob11FriendInfoList.forEach(ob11FriendInfo ->
			{
				friendIdList.add(ob11FriendInfo.userId);
			});
			return friendIdList;
		}
		return new ArrayList<>();
	}

	/**
	 * 获取好友信息列表
	 * @return 好友信息数据类列表
	 */
	public List<OB11FriendInfo> getFriendInfoList()
	{
		return getFriendInfoList(true);
	}

	/**
	 * 获取好友信息列表
	 * @param noCache 是否不使用缓存
	 * @return 好友信息数据类列表
	 */
	public List<OB11FriendInfo> getFriendInfoList(boolean noCache)
	{
		BotApiService botApiService = new BotApiService(bot, OB11AccountApiPath.GET_FRIEND_LIST.getValue());
		JSONObject data = new JSONObject().put("no_cache", noCache);
		BotApiService.APIRequestResult apiRequestResult = botApiService.sendRequest(data.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				List<OB11FriendInfo> ob11FriendInfoList = new ArrayList<>();
				if (apiRequestResult.data instanceof JSONArray friendInfoList)
				{
					friendInfoList.forEach(obj ->
					{
						if (obj instanceof JSONObject friendInfo)
						{
							ob11FriendInfoList.add(OB11FriendInfo.getInstance(friendInfo));
						}
					});
					return ob11FriendInfoList;
				}
			}
		}
		return new ArrayList<>();
	}

	/**
	 * 获取小程序卡片
	 * @param type 类型
	 * @param title 标题
	 * @param desc 描述
	 * @param picUrl 图片URL
	 * @param jumpUrl 跳转URL
	 * @param webUrl 网页URL
	 * @param rawArkData 是否返回原始ARK数据
	 * @return JSON卡片代码
	 */
	public JSONObject getMiniAppArk(String type, String title, String desc, String picUrl, String jumpUrl, String webUrl, boolean rawArkData)
	{
		BotApiService botApiService = new BotApiService(bot, OB11AccountApiPath.GET_MINI_APP_ARK.getValue());
		JSONObject data = new JSONObject()
		.put("type", type)
		.put("title", title)
		.put("desc", desc)
		.put("picUrl", picUrl)
		.put("jumpUrl", jumpUrl);
		if (rawArkData)
		{
			data.put("rawArkData", rawArkData);
		}
		if (webUrl != null)
		{
			data.put("webUrl", webUrl);
		}
		BotApiService.APIRequestResult apiRequestResult = botApiService.sendRequest(data.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONObject miniAppArk)
				{
					return miniAppArk;
				}
			}
		}
		return new JSONObject();
	}
}
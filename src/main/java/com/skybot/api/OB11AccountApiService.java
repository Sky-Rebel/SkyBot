package com.skybot.api;

import com.skybot.api.data.account.OB11FriendInfo;
import com.skybot.bot.BotServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OB11AccountApiService
{
	private enum OB11UserApiPath
	{

		GET_FRIEND_LIST("/get_friend_list"),

		GET_MINI_APP_ARK("/get_mini_app_ark");

		private final String value;

		OB11UserApiPath(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return this.value;
		}
	}

	/**
	 * 获取好友列表
	 * @return 好友信息数据类列表
	 */
	public static List<OB11FriendInfo> getFriendList()
	{
		return getFriendList(true);
	}

	/**
	 * 获取好友列表
	 * @param noCache 是否不使用缓存
	 * @return 好友信息数据类列表
	 */
	public static List<OB11FriendInfo> getFriendList(boolean noCache)
	{
		BotServer botServer = new BotServer(OB11UserApiPath.GET_FRIEND_LIST.getValue());
		JSONObject data = new JSONObject().put("no_cache", noCache);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(data.toString());
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
	public static JSONObject getMiniAppArk(String type, String title, String desc, String picUrl, String jumpUrl, String webUrl, boolean rawArkData)
	{
		BotServer botServer = new BotServer(OB11UserApiPath.GET_MINI_APP_ARK.getValue());
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
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(data.toString());
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
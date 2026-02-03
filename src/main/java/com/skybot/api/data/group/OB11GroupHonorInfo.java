package com.skybot.api.data.group;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class OB11GroupHonorInfo
{
	public long groupId;

	public OB11UserHonorInfo currentTalkative;

	public List<OB11UserHonorInfo> talkativeList;

	public List<OB11UserHonorInfo> performerList;

	public List<OB11UserHonorInfo> legendList;

	public List<OB11UserHonorInfo> emotionList;

	public List<OB11UserHonorInfo> strongNewbieList;

	public static class OB11UserHonorInfo
	{
		public long userId;

		public String nickname;

		public String avatar;

		public String description;

		public static OB11UserHonorInfo getInstance(JSONObject data)
		{
			OB11UserHonorInfo info = new OB11UserHonorInfo();
			if (data != null)
			{
				info.userId = data.getLong("user_id");
				info.nickname = data.getString("nickname");
				info.avatar = data.getString("avatar");
				info.description = data.getString("description");
			}
			return info;
		}

		@Override
		public String toString()
		{
			return new StringJoiner(", ", OB11UserHonorInfo.class.getSimpleName() + "[", "]")
				.add("userId=" + userId)
				.add("nickname='" + nickname + "'")
				.add("avatar=" + avatar)
				.add("description='" + description + "'")
				.toString();
		}
	}

	private static List<OB11UserHonorInfo> parseHonorUserList(JSONArray jsonArray)
	{
		List<OB11UserHonorInfo> userList = new ArrayList<>();
		if (jsonArray != null && !jsonArray.isEmpty())
		{
			jsonArray.forEach(obj ->
			{
				if (obj instanceof JSONObject userObj)
				{
					userList.add(OB11UserHonorInfo.getInstance(userObj));
				}
			});
		}
		return userList;
	}

	public static OB11GroupHonorInfo getInstance(JSONObject data)
	{
		OB11GroupHonorInfo info = new OB11GroupHonorInfo();
		if (data != null)
		{
			info.groupId = data.getLong("group_id");
			JSONObject currentTalkativeObj = data.getJSONObject("current_talkative");
			if (currentTalkativeObj != null)
			{
				info.currentTalkative = OB11UserHonorInfo.getInstance(currentTalkativeObj);
			}
			info.talkativeList = parseHonorUserList(data.getJSONArray("talkative_list"));
			info.performerList = parseHonorUserList(data.getJSONArray("performer_list"));
			info.legendList = parseHonorUserList(data.getJSONArray("legend_list"));
			info.emotionList = parseHonorUserList(data.getJSONArray("emotion_list"));
			info.strongNewbieList = parseHonorUserList(data.getJSONArray("strong_newbie_list"));
		}
		return info;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupHonorInfo.class.getSimpleName() + "[", "]")
			.add("groupId='" + groupId + "'")
			.add("currentTalkative=" + currentTalkative)
			.add("talkativeList=" + talkativeList)
			.add("performerList=" + performerList)
			.add("legendList=" + legendList)
			.add("emotionList=" + emotionList)
			.add("strongNewbieList=" + strongNewbieList)
			.toString();
	}
}
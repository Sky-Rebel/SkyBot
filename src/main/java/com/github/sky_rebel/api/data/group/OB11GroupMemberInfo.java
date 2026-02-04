package com.github.sky_rebel.api.data.group;

import org.json.JSONObject;
import java.util.StringJoiner;

public class OB11GroupMemberInfo
{
	public long groupId;

	public long userId;

	public String nickname;

	public String card;

	public String sex;

	public int age;

	public long joinTime;

	public long lastSentTime;

	public int level;

	public int qqLevel;

	public String role;

	public String title;

	public String area;

	public boolean unfriendly;

	public long titleExpireTime;

	public boolean cardChangeable;

	public long shutUpTimestamp;

	public boolean isRobot;

	public static OB11GroupMemberInfo getInstance(JSONObject data)
	{
		OB11GroupMemberInfo ob11GroupMemberInfo = new OB11GroupMemberInfo();
		ob11GroupMemberInfo.groupId = data.getLong("group_id");
		ob11GroupMemberInfo.userId = data.getLong("user_id");
		ob11GroupMemberInfo.nickname = data.getString("nickname");
		ob11GroupMemberInfo.card = data.getString("card");
		ob11GroupMemberInfo.sex = data.getString("sex");
		ob11GroupMemberInfo.age = data.getInt("age");
		ob11GroupMemberInfo.joinTime = data.getLong("join_time");
		ob11GroupMemberInfo.lastSentTime = data.getLong("last_sent_time");
		ob11GroupMemberInfo.level = data.getInt("level");
		ob11GroupMemberInfo.qqLevel = data.getInt("qq_level");
		ob11GroupMemberInfo.role = data.getString("role");
		ob11GroupMemberInfo.title = data.getString("title");
		ob11GroupMemberInfo.area = data.getString("area");
		ob11GroupMemberInfo.unfriendly = data.getBoolean("unfriendly");
		ob11GroupMemberInfo.titleExpireTime = data.getLong("title_expire_time");
		ob11GroupMemberInfo.cardChangeable = data.getBoolean("card_changeable");
		ob11GroupMemberInfo.shutUpTimestamp = data.getLong("shut_up_timestamp");
		ob11GroupMemberInfo.isRobot = data.getBoolean("is_robot");
		return ob11GroupMemberInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupMemberInfo.class.getSimpleName() + "[", "]")
			.add("groupId=" + groupId)
			.add("userId=" + userId)
			.add("nickname='" + nickname + "'")
			.add("card='" + card + "'")
			.add("sex='" + sex + "'")
			.add("age=" + age)
			.add("joinTime=" + joinTime)
			.add("lastSentTime=" + lastSentTime)
			.add("level=" + level)
			.add("qqLevel=" + qqLevel)
			.add("role='" + role + "'")
			.add("title='" + title + "'")
			.add("area='" + area + "'")
			.add("unfriendly=" + unfriendly)
			.add("titleExpireTime=" + titleExpireTime)
			.add("cardChangeable=" + cardChangeable)
			.add("shutUpTimestamp=" + shutUpTimestamp)
			.add("isRobot=" + isRobot)
			.toString();
	}
}
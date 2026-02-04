package com.github.sky_rebel.bot.api.data.account;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11FriendInfo
{
	public int birthdayYear;

	public int birthdayMonth;

	public int birthdayDay;

	public String phoneNum;

	public String email;

	public int categoryId;

	public long userId;

	public String nickname;

	public String remark;

	public String sex;

	public int level;

	public int age;

	public String qid;

	public int loginDays;

	public String categoryName;

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11FriendInfo.class.getSimpleName() + "[", "]").add("birthday_year=" + birthdayYear)
		.add("birthday_month=" + birthdayMonth)
		.add("birthday_day=" + birthdayDay)
		.add("phone_num='" + phoneNum + "'")
		.add("email='" + email + "'")
		.add("category_id=" + categoryId)
		.add("user_id=" + userId)
		.add("nickname='" + nickname + "'")
		.add("remark='" + remark + "'")
		.add("sex='" + sex + "'")
		.add("level=" + level)
		.add("age=" + age)
		.add("qid='" + qid + "'")
		.add("login_days=" + loginDays)
		.add("categoryName='" + categoryName + "'")
		.toString();
	}

	public static OB11FriendInfo getInstance(JSONObject data)
	{
		OB11FriendInfo friendInfo = new OB11FriendInfo();
		friendInfo.birthdayYear = data.optInt("birthday_year", 0);
		friendInfo.birthdayMonth = data.optInt("birthday_month", 0);
		friendInfo.birthdayDay = data.optInt("birthday_day", 0);
		friendInfo.phoneNum = data.optString("phone_num");
		friendInfo.email = data.optString("email");
		friendInfo.categoryId = data.optInt("category_id", 0);
		friendInfo.userId = data.optLong("user_id", 0L); // long 类型注意默认值用 0L
		friendInfo.nickname = data.optString("nickname");
		friendInfo.remark = data.optString("remark");
		friendInfo.sex = data.optString("sex");
		friendInfo.level = data.optInt("level", 0);
		friendInfo.age = data.optInt("age", 0);
		friendInfo.qid = data.optString("qid");
		friendInfo.loginDays = data.optInt("login_days", 0);
		friendInfo.categoryName = data.optString("categoryName");
		return friendInfo;
	}
}
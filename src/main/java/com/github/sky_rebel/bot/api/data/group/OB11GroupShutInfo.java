package com.github.sky_rebel.bot.api.data.group;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11GroupShutInfo
{
	public String uid;

	public String qid;

	public String uin;

	public String nick;

	public String remark;

	public int cardType;

	public String cardName;

	public int role;

	public String avatarPath;

	public long shutUpTime;

	public boolean isDelete;

	public boolean isSpecialConcerned;

	public boolean isSpecialShield;

	public boolean isRobot;

	public JSONObject groupHonor;

	public int memberRealLevel;

	public int memberLevel;

	public int globalGroupLevel;

	public int globalGroupPoint;

	public int memberTitleId;

	public String memberSpecialTitle;

	public String specialTitleExpireTime;

	public int userShowFlag;

	public int userShowFlagNew;

	public int richFlag;

	public int mssVipType;

	public int bigClubLevel;

	public int bigClubFlag;

	public String autoRemark;

	public int creditLevel;

	public long joinTime;

	public long lastSpeakTime;

	public int memberFlag;

	public int memberFlagExt;

	public int memberMobileFlag;

	public int memberFlagExt2;

	public boolean isSpecialShielded;

	public int cardNameId;

	public static OB11GroupShutInfo getInstance(JSONObject data)
	{
		OB11GroupShutInfo ob11GroupShutInfo = new OB11GroupShutInfo();
		ob11GroupShutInfo.uid = data.getString("uid");
		ob11GroupShutInfo.qid = data.getString("qid");
		ob11GroupShutInfo.uin = data.getString("uin");
		ob11GroupShutInfo.nick = data.getString("nick");
		ob11GroupShutInfo.remark = data.getString("remark");
		ob11GroupShutInfo.cardType = data.getInt("cardType");
		ob11GroupShutInfo.cardName = data.getString("cardName");
		ob11GroupShutInfo.role = data.getInt("role");
		ob11GroupShutInfo.avatarPath = data.getString("avatarPath");
		ob11GroupShutInfo.shutUpTime = data.getLong("shutUpTime");
		ob11GroupShutInfo.isDelete = data.getBoolean("isDelete");
		ob11GroupShutInfo.isSpecialConcerned = data.getBoolean("isSpecialConcerned");
		ob11GroupShutInfo.isSpecialShield = data.getBoolean("isSpecialShield");
		ob11GroupShutInfo.isRobot = data.getBoolean("isRobot");
		ob11GroupShutInfo.groupHonor = data.getJSONObject("groupHonor");
		ob11GroupShutInfo.memberRealLevel = data.getInt("memberRealLevel");
		ob11GroupShutInfo.memberLevel = data.getInt("memberLevel");
		ob11GroupShutInfo.globalGroupLevel = data.getInt("globalGroupLevel");
		ob11GroupShutInfo.globalGroupPoint = data.getInt("globalGroupPoint");
		ob11GroupShutInfo.memberTitleId = data.getInt("memberTitleId");
		ob11GroupShutInfo.memberSpecialTitle = data.getString("memberSpecialTitle");
		ob11GroupShutInfo.specialTitleExpireTime = data.getString("specialTitleExpireTime");
		ob11GroupShutInfo.userShowFlag = data.getInt("userShowFlag");
		ob11GroupShutInfo.userShowFlagNew = data.getInt("userShowFlagNew");
		ob11GroupShutInfo.richFlag = data.getInt("richFlag");
		ob11GroupShutInfo.mssVipType = data.getInt("mssVipType");
		ob11GroupShutInfo.bigClubLevel = data.getInt("bigClubLevel");
		ob11GroupShutInfo.bigClubFlag = data.getInt("bigClubFlag");
		ob11GroupShutInfo.autoRemark = data.getString("autoRemark");
		ob11GroupShutInfo.creditLevel = data.getInt("creditLevel");
		ob11GroupShutInfo.joinTime = data.getLong("joinTime");
		ob11GroupShutInfo.lastSpeakTime = data.getLong("lastSpeakTime");
		ob11GroupShutInfo.memberFlag = data.getInt("memberFlag");
		ob11GroupShutInfo.memberFlagExt = data.getInt("memberFlagExt");
		ob11GroupShutInfo.memberMobileFlag = data.getInt("memberMobileFlag");
		ob11GroupShutInfo.memberFlagExt2 = data.getInt("memberFlagExt2");
		ob11GroupShutInfo.isSpecialShielded = data.getBoolean("isSpecialShielded");
		ob11GroupShutInfo.cardNameId = data.getInt("cardNameId");
		return ob11GroupShutInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupShutInfo.class.getSimpleName() + "[", "]")
			.add("uid='" + uid + "'")
			.add("qid='" + qid + "'")
			.add("uin='" + uin + "'")
			.add("nick='" + nick + "'")
			.add("remark='" + remark + "'")
			.add("cardType=" + cardType)
			.add("cardName='" + cardName + "'")
			.add("role=" + role)
			.add("avatarPath='" + avatarPath + "'")
			.add("shutUpTime=" + shutUpTime)
			.add("isDelete=" + isDelete)
			.add("isSpecialConcerned=" + isSpecialConcerned)
			.add("isSpecialShield=" + isSpecialShield)
			.add("isRobot=" + isRobot)
			.add("groupHonor=" + groupHonor)
			.add("memberRealLevel=" + memberRealLevel)
			.add("memberLevel=" + memberRealLevel)
			.add("globalGroupLevel=" + globalGroupLevel)
			.add("globalGroupPoint=" + globalGroupPoint)
			.add("memberTitleId=" + memberTitleId)
			.add("memberSpecialTitle='" + memberSpecialTitle + "'")
			.add("specialTitleExpireTime='" + specialTitleExpireTime + "'")
			.add("userShowFlag=" + userShowFlag)
			.add("userShowFlagNew=" + userShowFlagNew)
			.add("richFlag=" + richFlag)
			.add("mssVipType=" + mssVipType)
			.add("bigClubLevel=" + bigClubLevel)
			.add("bigClubFlag=" + bigClubFlag)
			.add("autoRemark='" + autoRemark + "'")
			.add("creditLevel=" + creditLevel)
			.add("joinTime=" + joinTime)
			.add("lastSpeakTime=" + lastSpeakTime)
			.add("memberFlag=" + memberFlag)
			.add("memberFlagExt=" + memberFlagExt)
			.add("memberMobileFlag=" + memberMobileFlag)
			.add("memberFlagExt2=" + memberFlagExt2)
			.add("isSpecialShielded=" + isSpecialShielded)
			.add("cardNameId=" + cardNameId)
			.toString();
	}
}
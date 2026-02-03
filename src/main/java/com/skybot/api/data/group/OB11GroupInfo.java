package com.skybot.api.data.group;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11GroupInfo
{
	public long groupId;

	public String groupName;

	public String groupRemark;

	public int memberCount;

	public int maxMemberCount;

	public int groupAllShut;

	public static OB11GroupInfo getInstance(JSONObject data)
	{
		OB11GroupInfo ob11GroupInfo = new OB11GroupInfo();
		ob11GroupInfo.groupId = data.getLong("group_id");
		ob11GroupInfo.groupName = data.getString("group_name");
		ob11GroupInfo.groupRemark = data.getString("group_remark");
		ob11GroupInfo.groupAllShut = data.getInt("group_all_shut");
		ob11GroupInfo.memberCount = data.getInt("member_count");
		ob11GroupInfo.maxMemberCount = data.getInt("max_member_count");
		return ob11GroupInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupInfo.class.getSimpleName() + "[", "]")
			.add("groupId=" + groupId)
			.add("groupName='" + groupName + "'")
			.add("groupRemark='" + groupRemark + "'")
			.add("memberCount=" + memberCount)
			.add("maxMemberCount=" + maxMemberCount)
			.add("groupAllShut=" + groupAllShut)
			.toString();
	}
}
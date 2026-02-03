package com.skybot.api.data.group;

import org.json.JSONObject;

import java.util.StringJoiner;

public class OB11GroupDetailInfo
{
	public long groupId;

	public String groupUin;

	public String groupName;

	public String groupRemark;

	public String groupMemo;

	public String remarkName;

	public long groupCreateTime;

	public int memberCount;

	public int maxMemberCount;

	public int activeMemberNum;

	public String ownerUin;

	public String ownerUid;

	public int groupAdminMaxNum;

	public String role;

	public int groupAllShut;

	public long shutUpAllTimestamp;

	public long shutUpMeTimestamp;

	public boolean isTop;

	public boolean isConfGroup;

	public boolean isGroupFreeze;

	public int isAllowRecallMsg;

	public int isAllowConfGroupMemberAtAll;

	public int isAllowConfGroupMemberModifyGroupName;

	public int groupGrade;

	public int groupSecLevel;

	public int privilegeFlag;

	public static OB11GroupDetailInfo getInstance(JSONObject data)
	{
		OB11GroupDetailInfo ob11GroupDetailInfo = new OB11GroupDetailInfo();
		ob11GroupDetailInfo.groupId = data.optLong("group_id", 0);
		ob11GroupDetailInfo.groupUin = data.optString("groupUin", "");
		ob11GroupDetailInfo.groupName = data.optString("groupName", "");
		ob11GroupDetailInfo.groupRemark = data.optString("group_remark", "");
		ob11GroupDetailInfo.groupMemo = data.optString("groupMemo", "");
		ob11GroupDetailInfo.remarkName = data.optString("remarkName", "");
		ob11GroupDetailInfo.groupCreateTime = data.optLong("groupCreateTime", 0);
		ob11GroupDetailInfo.memberCount = data.optInt("memberCount", data.optInt("member_count", 0)); // 兼容两个字段名
		ob11GroupDetailInfo.maxMemberCount = data.optInt("maxMemberCount", data.optInt("max_member_count", 0)); // 兼容两个字段名
		ob11GroupDetailInfo.activeMemberNum = data.optInt("activeMemberNum", 0);
		ob11GroupDetailInfo.ownerUin = data.optString("ownerUin", "");
		ob11GroupDetailInfo.ownerUid = data.optString("ownerUid", "");
		ob11GroupDetailInfo.groupAdminMaxNum = data.optInt("groupAdminMaxNum", 0);
		ob11GroupDetailInfo.groupAllShut = data.optInt("group_all_shut", 0);
		ob11GroupDetailInfo.shutUpAllTimestamp = data.optLong("shutUpAllTimestamp", 0);
		ob11GroupDetailInfo.shutUpMeTimestamp = data.optLong("shutUpMeTimestamp", 0);
		ob11GroupDetailInfo.isTop = data.optBoolean("isTop", false);
		ob11GroupDetailInfo.isConfGroup = data.optInt("isConfGroup", 0) == 1;
		ob11GroupDetailInfo.isGroupFreeze = data.optInt("isGroupFreeze", 0) == 1;
		ob11GroupDetailInfo.isAllowRecallMsg = data.optInt("isAllowRecallMsg", 0);
		ob11GroupDetailInfo.isAllowConfGroupMemberAtAll = data.optInt("isAllowConfGroupMemberAtAll", 0);
		ob11GroupDetailInfo.isAllowConfGroupMemberModifyGroupName = data.optInt("isAllowConfGroupMemberModifyGroupName", 0);
		ob11GroupDetailInfo.groupGrade = data.optInt("groupGrade", 0);
		ob11GroupDetailInfo.groupSecLevel = data.optInt("groupSecLevel", 0);
		ob11GroupDetailInfo.privilegeFlag = data.optInt("privilegeFlag", 0);
		return ob11GroupDetailInfo;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupDetailInfo.class.getSimpleName() + "[", "]")
			.add("groupId=" + groupId)
			.add("groupUin='" + groupUin + "'")
			.add("groupName='" + groupName + "'")
			.add("groupRemark='" + groupRemark + "'")
			.add("groupMemo='" + groupMemo + "'")
			.add("remarkName='" + remarkName + "'")
			.add("groupCreateTime=" + groupCreateTime)
			.add("memberCount=" + memberCount)
			.add("maxMemberCount=" + maxMemberCount)
			.add("activeMemberNum=" + activeMemberNum)
			.add("ownerUin='" + ownerUin + "'")
			.add("ownerUid='" + ownerUid + "'")
			.add("groupAdminMaxNum=" + groupAdminMaxNum)
			.add("groupAllShut=" + groupAllShut)
			.add("shutUpAllTimestamp=" + shutUpAllTimestamp)
			.add("shutUpMeTimestamp=" + shutUpMeTimestamp)
			.add("isTop=" + isTop)
			.add("isConfGroup=" + isConfGroup)
			.add("isGroupFreeze=" + isGroupFreeze)
			.add("isAllowRecallMsg=" + isAllowRecallMsg)
			.add("isAllowConfGroupMemberAtAll=" + isAllowConfGroupMemberAtAll)
			.add("isAllowConfGroupMemberModifyGroupName=" + isAllowConfGroupMemberModifyGroupName)
			.add("groupGrade=" + groupGrade)
			.add("groupSecLevel=" + groupSecLevel)
			.add("privilegeFlag=" + privilegeFlag)
			.toString();
	}
}
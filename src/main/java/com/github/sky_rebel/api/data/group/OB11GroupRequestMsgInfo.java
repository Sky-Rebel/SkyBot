package com.github.sky_rebel.api.data.group;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class OB11GroupRequestMsgInfo
{
	public List<OB11GroupRequestInfo> groupJoinRequestList;

	public List<OB11GroupRequestInfo> groupInvitedRequestList;

	public static class OB11GroupRequestInfo
	{
		public long requestId;

		public long invitorUin;

		public String invitorNick;

		public long groupId;

		public String message;

		public String groupName;

		public boolean checked;

		public long actor;

		public String requesterNick;

		public static OB11GroupRequestInfo getInstance(JSONObject data)
		{
			OB11GroupRequestInfo ob11GroupRequestInfo = new OB11GroupRequestInfo();
			ob11GroupRequestInfo.requestId = data.getLong("request_id");
			ob11GroupRequestInfo.invitorUin = data.getLong("invitor_uin");
			ob11GroupRequestInfo.invitorNick = data.getString("invitor_nick");
			ob11GroupRequestInfo.groupId = data.getLong("group_id");
			ob11GroupRequestInfo.message = data.getString("message");
			ob11GroupRequestInfo.groupName = data.getString("group_name");
			ob11GroupRequestInfo.checked = data.getBoolean("checked");
			ob11GroupRequestInfo.actor = data.getLong("actor");
			ob11GroupRequestInfo.requesterNick = data.getString("requester_nick");
			return ob11GroupRequestInfo;
		}

		@Override
		public String toString()
		{
			return new StringJoiner(", ", OB11GroupRequestMsgInfo.class.getSimpleName() + "[", "]")
				.add("requestId=" + requestId)
				.add("invitorUin=" + invitorUin)
				.add("invitorNick='" + invitorNick + "'")
				.add("groupId=" + groupId)
				.add("message='" + message + "'")
				.add("groupName='" + groupName + "'")
				.add("checked=" + checked)
				.add("actor=" + actor)
				.add("requesterNick='" + requesterNick + "'")
				.toString();
		}
	}

	public static OB11GroupRequestMsgInfo getInstance(JSONObject data)
	{
		if (data != null)
		{
			OB11GroupRequestMsgInfo ob11GroupRequestMsgInfo = new OB11GroupRequestMsgInfo();
			JSONArray joinRequests = data.getJSONArray("join_requests");
			if (joinRequests != null && !joinRequests.isEmpty())
			{
				List<OB11GroupRequestInfo> groupJoinRequestInfoList = new ArrayList<>();
				joinRequests.forEach(obj ->
				{
					if (obj instanceof JSONObject joinRequestInfo)
					{
						groupJoinRequestInfoList.add(OB11GroupRequestInfo.getInstance(joinRequestInfo));
					}
				});
				ob11GroupRequestMsgInfo.groupJoinRequestList = groupJoinRequestInfoList;
			}
			JSONArray invitedRequest = data.getJSONArray("InvitedRequest");
			if (invitedRequest != null && !invitedRequest.isEmpty())
			{
				List<OB11GroupRequestInfo> groupInvitedRequestInfoList = new ArrayList<>();
				invitedRequest.forEach(obj ->
				{
					if (obj instanceof JSONObject invitedRequestInfo)
					{
						groupInvitedRequestInfoList.add(OB11GroupRequestInfo.getInstance(invitedRequestInfo));
					}
				});
				ob11GroupRequestMsgInfo.groupInvitedRequestList = groupInvitedRequestInfoList;
			}
			return ob11GroupRequestMsgInfo;
		}
		return new OB11GroupRequestMsgInfo();
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", OB11GroupRequestMsgInfo.class.getSimpleName() + "[", "]")
			.add("groupJoinRequestList=" + groupJoinRequestList)
			.add("groupInvitedRequestList=" + groupInvitedRequestList)
			.toString();
	}
}
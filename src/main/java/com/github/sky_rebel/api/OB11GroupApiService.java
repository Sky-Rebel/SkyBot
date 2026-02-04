package com.github.sky_rebel.api;

import com.github.sky_rebel.api.data.group.*;
import com.github.sky_rebel.bot.Bot;
import com.github.sky_rebel.bot.BotServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OB11GroupApiService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(OB11GroupApiService.class);

	private final Bot bot;

	public OB11GroupApiService(Bot bot)
	{
		this.bot = bot;
	}

	public enum OB11GroupApiPath
	{
		SET_GROUP_BAN("/set_group_ban"),
		SET_GROUP_SIGN("/set_group_sign"),
		SET_GROUP_TODO("/set_group_todo"),
		SET_GROUP_KICK("/set_group_kick"),
		GET_GROUP_INFO("/get_group_info"),
		GET_GROUP_LIST("/get_group_list"),
		SET_GROUP_CARD("/set_group_card"),
		SET_GROUP_NAME("/set_group_name"),
		SET_GROUP_LEAVE("/set_group_leave"),
		SEND_GROUP_SIGN("/send_group_sign"),
		SET_GROUP_ADMIN("/set_group_admin"),
		SET_ESSENCE_MSG("/set_essence_msg"),
		SET_GROUP_REMARK("/set_group_remark"),
		SET_GROUP_SEARCH("/set_group_search"),
		DEL_GROUP_NOTICE("/_del_group_notice"),
		GET_GROUP_NOTICE("/_get_group_notice"),
		GET_GROUP_INFO_EX("/get_group_info_ex"),
		SEND_GROUP_NOTICE("/_send_group_notice"),
		SET_GROUP_PORTRAIT("/set_group_portrait"),
		DELETE_ESSENCE_MSG("/delete_essence_msg"),
		GET_GROUP_SHUT_LIST("/get_group_shut_list"),
		SET_GROUP_WHOLE_BAN("/set_group_whole_ban"),
		GET_ESSENCE_MSG_LIST("/get_essence_msg_list"),
		GET_GROUP_SYSTEM_MSG("/get_group_system_msg"),
		GET_GROUP_HONOR_INFO("/get_group_honor_info"),
		SET_GROUP_ADD_OPTION("/set_group_add_option"),
		SET_GROUP_ADD_REQUEST("/set_group_add_request"),
		GET_GROUP_MEMBER_INFO("/get_group_member_info"),
		GET_GROUP_MEMBER_LIST("/get_group_member_list"),
		GET_GROUP_DETAIL_INFO("/get_group_detail_info"),
		SET_GROUP_KICK_MEMBERS("/set_group_kick_members"),
		SET_GROUP_SPECIAL_TITLE("/set_group_special_title"),
		GET_GROUP_AT_ALL_REMAIN("/get_group_at_all_remain"),
		GET_GROUP_IGNORED_NOTIFIES("/get_group_ignored_notifies"),
		SET_GROUP_ROBOT_ADD_OPTION("/set_group_robot_add_option");

		private final String value;

		OB11GroupApiPath(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return this.value;
		}
	}

	/**
	 * 设置群禁言
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @param duration 禁言时间（秒）
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult setGroupBan(long groupId, long userId, long duration)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_BAN.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userId)
			.put("duration", duration);
		return botServer.sendRequest(rootObject.toString());
	}


	/**
	 * 设置群打卡
	 * @param groupId 群聊ID
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult setGroupSign(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_SIGN.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 发送群打卡
	 * @param groupId 群聊ID
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult sendGroupSign(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SEND_GROUP_SIGN.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群待办
	 * @param groupId 群聊ID
	 * @param messageId 消息ID
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult setGroupTodo(long groupId, long messageId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_TODO.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("message_id", messageId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群待办
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult setGroupKick(long groupId, long userId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_KICK.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群信息
	 * @param groupId 群聊ID
	 * @return 群聊信息数据类
	 */
	public OB11GroupInfo getGroupInfo(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_INFO.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data instanceof JSONObject data)
			{
				return OB11GroupInfo.getInstance(data);
			}
		}
		LOGGER.error("getGroupInfo -> {}", apiRequestResult);
		return new OB11GroupInfo();
	}

	/**
	 * 设置群名片
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @param card 名片
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult setGroupCard(long groupId, long userId, String card)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_CARD.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userId)
			.put("card", card);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群列表
	 * @return 群聊信息数据类列表
	 */
	public List<OB11GroupInfo> getGroupList()
	{
		return getGroupList(true);
	}

	/**
	 * 获取群列表
	 * @param noCache 不缓存
	 * @return 群聊信息数据类列表
	 */
	public List<OB11GroupInfo> getGroupList(boolean noCache)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_LIST.getValue());
		JSONObject rootObject = new JSONObject().put("no_cache", noCache);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				List<OB11GroupInfo> ob11GroupInfoList = new ArrayList<>();
				if (apiRequestResult.data instanceof JSONArray groupInfoListArray)
				{
					if (!groupInfoListArray.isEmpty())
					{
						groupInfoListArray.forEach(obj ->
						{
							if (obj instanceof JSONObject groupInfo)
							{
								ob11GroupInfoList.add(OB11GroupInfo.getInstance(groupInfo));
							}
						});
						return ob11GroupInfoList;
					}
				}
			}
		}
		LOGGER.error("getGroupList -> {}", apiRequestResult);
		return new ArrayList<>();
	}

	/**
	 * 设置群名字
	 * @param groupId 群聊ID
	 * @param groupName 群聊名字
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupName(long groupId, String groupName)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_NAME.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("group_name", groupName);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 退出群聊（非群主） 或 解散群聊（是群主）
	 * @param groupId 群聊ID
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupLeave(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_LEAVE.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群精华
	 * @param message_id 消息ID
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setEssenceMsg(long message_id)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_ESSENCE_MSG.getValue());
		JSONObject rootObject = new JSONObject().put("message_id", message_id);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群管理
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @param enable 设置/取消
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupAdmin(long groupId, long userId, boolean enable)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_ADMIN.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userId)
			.put("enable", enable);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群备注
	 * @param groupId 群聊ID
	 * @param remark 备注
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupRemark(long groupId, String remark)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_REMARK.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("remark", remark);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * TODO: 发送图片尚未测试
	 * 发布群公告
	 * @param groupId 群聊ID
	 * @param content 公告内容
	 * @param image 图片路径（无请提交NULL）
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult sendGroupNotice(long groupId, String content, String image)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SEND_GROUP_NOTICE.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("content", content);
		if (image != null)
			rootObject.put("image", image);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群公告
	 * @param groupId 群聊ID
	 * @return 群聊公告数据类列表
	 */
	public List<OB11GroupNoticeInfo> getGroupNotice(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_NOTICE.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONArray groupNoticeInfoListArray)
				{
					if (!groupNoticeInfoListArray.isEmpty())
					{
						List<OB11GroupNoticeInfo> ob11GroupNoticeInfoList = new ArrayList<>();
						groupNoticeInfoListArray.forEach(obj ->
						{
							if (obj instanceof JSONObject groupNoticeInfo)
							{
								ob11GroupNoticeInfoList.add(OB11GroupNoticeInfo.getInstance(groupNoticeInfo));
							}
						});
						return ob11GroupNoticeInfoList;
					}
				}
			}
		}
		LOGGER.error("getGroupNotice -> {}", apiRequestResult);
		return new ArrayList<>();
	}

	/**
	 * 删除群公告
	 * @param groupId 群聊ID
	 * @param noticeId 公告ID
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult delGroupNotice(long groupId, String noticeId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.DEL_GROUP_NOTICE.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("notice_id", noticeId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群搜索
	 * @param groupId 群聊ID
	 * @param noCodeFingerOpen 禁止通过群号搜索
	 * @param noFingerOpen 禁止搜索
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupSearch(long groupId, int noCodeFingerOpen, int noFingerOpen)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_SEARCH.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("no_finger_open", noFingerOpen)
			.put("no_code_finger_open", noCodeFingerOpen);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群扩展信息
	 * @param groupId 群聊ID
	 * @return 群聊扩展信息数据类
	 */
	public OB11GroupExInfo getGroupInfoEx(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_INFO_EX.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONObject groupExInfo)
				{
					return OB11GroupExInfo.getInstance(groupExInfo);
				}
			}
		}
		LOGGER.error("getGroupInfoEx -> {}", apiRequestResult);
		return new OB11GroupExInfo();
	}

	/**
	 * 设置群头像
	 * @param groupId 群聊ID
	 * @param file 图片地址
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupPortrait(long groupId, String file)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_PORTRAIT.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("file", file);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 删除群精华
	 * @param messageId 消息ID
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult deleteEssenceMsg(long messageId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.DELETE_ESSENCE_MSG.getValue());
		JSONObject rootObject = new JSONObject().put("message_id", messageId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群禁言列表
	 * @param groupId 群聊ID
	 * @return 群禁言信息数据类列表
	 */
	public List<OB11GroupShutInfo> getGroupShutList(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_SHUT_LIST.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONArray groupShutInfoList)
				{
					List<OB11GroupShutInfo> ob11GroupShutInfoList = new ArrayList<>();
					groupShutInfoList.forEach(obj ->
					{
						if (obj instanceof JSONObject groupShouInfo)
						{
							ob11GroupShutInfoList.add(OB11GroupShutInfo.getInstance(groupShouInfo));
						}
					});
					return ob11GroupShutInfoList;
				}
			}
		}
		LOGGER.error("getGroupShutList -> {}", apiRequestResult);
		return new ArrayList<>();
	}

	/**
	 * 设置群全体禁言
	 * @param groupId 群聊ID
	 * @param enable 全禁 或 全解
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupWholeBan(long groupId, boolean enable)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_WHOLE_BAN.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("enable", enable);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群精华列表
	 * @param groupId 群聊ID
	 * @return 群精华信息数据类列表
	 */
	public List<OB11EssenceMsgInfo> getEssenceMsgList(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_ESSENCE_MSG_LIST.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONArray essenceMsgInfoList)
				{
					List<OB11EssenceMsgInfo> ob11EssenceMsgInfoList = new ArrayList<>();
					essenceMsgInfoList.forEach(obj ->
					{
						if (obj instanceof JSONObject essenceMsgInfo)
						{
							ob11EssenceMsgInfoList.add(OB11EssenceMsgInfo.getInstance(essenceMsgInfo));
						}
					});
					return ob11EssenceMsgInfoList;
				}
			}
		}
		LOGGER.error("getEssenceMsgList -> {}", apiRequestResult);
		return new ArrayList<>();
	}

	/**
	 * 获取群系统消息
	 * @param count 获取数量
	 * @return 群系统消息数据类
	 */
	public OB11GroupRequestMsgInfo getGroupSystemMsg(int count)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_SYSTEM_MSG.getValue());
		JSONObject rootObject = new JSONObject().put("count", count);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			return OB11GroupRequestMsgInfo.getInstance((JSONObject) apiRequestResult.data);
		}
		LOGGER.error("getGroupSystemMsg -> {}", apiRequestResult);
		return new OB11GroupRequestMsgInfo();
	}

	/**
	 * 获取群荣誉
	 * @param groupId 群聊ID
	 * @return 群荣誉信息数据类
	 */
	public OB11GroupHonorInfo getGroupHonorInfo(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_HONOR_INFO.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("type", "all");
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			return OB11GroupHonorInfo.getInstance((JSONObject) apiRequestResult.data);
		}
		LOGGER.error("getGroupHonorInfo -> {}", apiRequestResult);
		return new OB11GroupHonorInfo();
	}

	/**
	 * 设置群新成员加入选项
	 * @param groupId 群聊ID
	 * @param addType 加群类型
	 * <ul>
	 * <li>1 - 直接加群</li>
	 * <li>2 - 验证消息加群（管理审核）</li>
	 * <li>3 - 禁止加群</li>
	 * <li>4 - 正确回答问题加群</li>
	 * <li>5 - 回答问题加群（管理审核）</li>
	 * </ul>
	 * @param groupQuestion 问题
	 * @param groupAnswer 答案
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupAddOption(long groupId, String addType, String groupQuestion, String groupAnswer)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_ADD_OPTION.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("add_type", addType)
			.put("group_question", groupQuestion)
			.put("group_answer", groupAnswer);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 处理群请求
	 * @param flag 加群ID
	 * @param approve 同意 或 拒绝
	 * @param reason 拒绝理由
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupAddRequest(String flag, boolean approve, String reason)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_ADD_REQUEST.getValue());
		JSONObject rootObject = new JSONObject()
			.put("flag", flag)
			.put("approve", approve)
			.put("reason", reason);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群成员信息
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @param noCache 不缓存
	 * @return 群成员信息数据类
	 */
	public OB11GroupMemberInfo getGroupMemberInfo(long groupId, long userId, boolean noCache)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_MEMBER_INFO.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userId)
			.put("no_cache", noCache);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONObject groupMemberInfo)
				{
					return OB11GroupMemberInfo.getInstance(groupMemberInfo);
				}
			}
		}
		LOGGER.error("getGroupMemberInfo -> {}", apiRequestResult);
		return new OB11GroupMemberInfo();
	}

	/**
	 * 获取群成员列表
	 * @param groupId 群聊ID
	 * @param noCache 不缓存
	 * @return 群成员信息数据类列表
	 */
	public List<OB11GroupMemberInfo> getGroupMemberList(long groupId, boolean noCache)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_MEMBER_LIST.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("no_cache", noCache);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONArray groupMemberInfoList)
				{
					List<OB11GroupMemberInfo> ob11GroupMemberInfoList = new ArrayList<>();
					groupMemberInfoList.forEach(obj ->
					{
						if (obj instanceof JSONObject groupMemberInfo)
						{
							ob11GroupMemberInfoList.add(OB11GroupMemberInfo.getInstance(groupMemberInfo));
						}
					});
					return ob11GroupMemberInfoList;
				}
			}
		}
		LOGGER.error("getGroupMemberList -> {}", apiRequestResult);
		return new ArrayList<>();
	}

	/**
	 * 获取群详细信息
	 * @param groupId 群聊ID
	 * @return 群详细信息数据类
	 */
	public OB11GroupDetailInfo getGroupDetailInfo(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_DETAIL_INFO.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONObject groupDetailInfo)
				{
					return OB11GroupDetailInfo.getInstance(groupDetailInfo);
				}
			}
		}
		return new OB11GroupDetailInfo();
	}

	/**
	 * 设置群踢出（批量）
	 * @param groupId 群聊ID
	 * @param userIdList 用户ID列表
	 * @param rejectAddRequest 是否拒绝再次申请
	 * @return API请求结果
	 */
	public BotServer.APIRequestResult setGroupKickMembers(long groupId, List<Long> userIdList, boolean rejectAddRequest)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_KICK_MEMBERS.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userIdList)
			.put("reject_add_request", rejectAddRequest);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 设置群头衔
	 * @param groupId 用户ID
	 * @param userId 用户ID
	 * @param specialTitle 头衔
	 * @return APi响应结果
	 */
	public BotServer.APIRequestResult setGroupSpecialTitle(long groupId, long userId, String specialTitle)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_SPECIAL_TITLE.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("user_id", userId)
			.put("special_title", specialTitle);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 获取群艾特信息
	 * @param groupId 群聊ID
	 * @return 群艾特剩余信息数据类
	 */
	public OB11GroupAtAllRemainInfo getGroupAtAllRemain(long groupId)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_AT_ALL_REMAIN.getValue());
		JSONObject rootObject = new JSONObject().put("group_id", groupId);
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest(rootObject.toString());
		if (apiRequestResult.isSuccess)
		{
			if (apiRequestResult.data != null)
			{
				if (apiRequestResult.data instanceof JSONObject groupAtAllRemainInfo)
				{
					return OB11GroupAtAllRemainInfo.getInstance(groupAtAllRemainInfo);
				}
			}
		}
		return new OB11GroupAtAllRemainInfo();
	}

	/**
	 * 获取群过滤消息
	 * @return 群过滤消息数据列
	 */
	public OB11GroupRequestMsgInfo getGroupIgnoredNotifies()
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.GET_GROUP_IGNORED_NOTIFIES.getValue());
		BotServer.APIRequestResult apiRequestResult = botServer.sendRequest("{}");
		if (apiRequestResult.isSuccess)
		{
			return OB11GroupRequestMsgInfo.getInstance((JSONObject) apiRequestResult.data);
		}
		LOGGER.error("getGroupIgnoredNotifies -> {}", apiRequestResult);
		return new OB11GroupRequestMsgInfo();
	}

	/**
	 * 设置群机器人加入选项
	 * @param groupId 群聊ID
	 * @param robotMemberSwitch 加群开关
	 * <ul>
	 * <li>0 - 禁止加入</li>
	 * <li>1 - 允许加入</li>
	 * </ul>
	 * @param robotMemberExamine 加群方式
	 * <ul>
	 * <li>0 - 无需审核</li>
	 * <li>1 - 需要审核</li>
	 * </ul>
	 * @return API响应结果
	 */
	public BotServer.APIRequestResult setGroupRobotAddOption(long groupId, int robotMemberSwitch, int robotMemberExamine)
	{
		BotServer botServer = new BotServer(bot, OB11GroupApiPath.SET_GROUP_ROBOT_ADD_OPTION.getValue());
		JSONObject rootObject = new JSONObject()
			.put("group_id", groupId)
			.put("robot_member_switch", robotMemberSwitch)
			.put("robot_member_examine", robotMemberExamine);
		return botServer.sendRequest(rootObject.toString());
	}
}
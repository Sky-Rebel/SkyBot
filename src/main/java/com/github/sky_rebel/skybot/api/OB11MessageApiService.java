package com.github.sky_rebel.skybot.api;

import com.github.sky_rebel.skybot.Bot;
import com.github.sky_rebel.skybot.BotServer;
import com.github.sky_rebel.skybot.msg.element.OB11JsonMsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11MsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11ReplyMsgElement;
import com.github.sky_rebel.skybot.msg.element.OB11TextMsgElement;
import com.github.sky_rebel.skybot.util.command.MessageArrayEdit;
import com.github.sky_rebel.skybot.util.logger.Logger;
import com.github.sky_rebel.skybot.util.logger.SkybotLogger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OB11MessageApiService
{
	private static final Logger LOGGER = SkybotLogger.getLogger(OB11MessageApiService.class);

	private Bot bot;

	public OB11MessageApiService(Bot bot)
	{
		this.bot = bot;
	}

	private enum OB11MessageApiPath
	{
		SEND_POKE("/send_poke"),
		DELETE_MSG("/delete_msg"),
		SEND_GROUP_MSG("/send_group_msg"),
		SEND_PRIVATE_MSG("/send_private_msg"),
		FORWARD_GROUP_SINGLE_MSG("/forward_group_single_msg"),
		FORWARD_FRIEND_SINGLE_MSG("/forward_friend_single_msg"),
		MARK_GROUP_MSG_AS_READ("/mark_group_msg_as_read"),
		MARK_PRIVATE_MSG_AS_READ("/mark_private_msg_as_read");

		private final String value;

		OB11MessageApiPath(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return this.value;
		}
	}

	/**
	 * 发送群聊JSON消息
	 * @param groupId 群聊ID
	 * @param json json内容
	 * @return 消息ID
	 */
	public long sendGroupJsonMessage(long groupId, JSONObject json)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11JsonMsgElement ob11JsonMsgElement = new OB11JsonMsgElement();
		ob11JsonMsgElement.setJson(json);
		msgElementArray.add(ob11JsonMsgElement);
		return sendGroupMessage(groupId, msgElementArray);
	}

	/**
	 * 发送群聊回复文本消息
	 * @param groupId 群聊ID
	 * @param text 文本内容
	 * @return 消息ID
	 */
	public long sendGroupReplyTextMessage(long groupId, long messageId, String text)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11ReplyMsgElement ob11ReplyMsgElement = new OB11ReplyMsgElement();
		ob11ReplyMsgElement.setId(messageId);
		msgElementArray.add(ob11ReplyMsgElement);
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendGroupMessage(groupId, msgElementArray);
	}

	/**
	 * 发送群聊文本消息
	 * @param groupId 群聊ID
	 * @param text 文本内容
	 * @return 消息ID
	 */
	public long sendGroupTextMessage(long groupId, String text)
	{
		System.out.print("sendGroupTextMessage");
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendGroupMessage(groupId, msgElementArray);
	}

	/**
	 * 发送群聊消息
	 * @param groupId 群聊ID
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @return 消息ID
	 */
	public long sendGroupMessage(long groupId, MessageArrayEdit messageArrayEdit)
	{
		return sendMessage("group", groupId, 0, messageArrayEdit);
	}

	/**
	 * 发送群聊消息
	 * @param groupId 群聊ID
	 * @param msgElementArray 消息元素数组
	 * @return 消息ID
	 */
	public long sendGroupMessage(long groupId, List<? extends OB11MsgElement> msgElementArray)
	{
		return sendMessage("group", groupId, 0, msgElementArray);
	}

	/**
	 * 发送私聊JSON消息
	 * @param userId 私聊ID
	 * @param json json内容
	 * @return 消息ID
	 */
	public long sendPrivateJsonMessage(long userId, JSONObject json)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11JsonMsgElement ob11JsonMsgElement = new OB11JsonMsgElement();
		ob11JsonMsgElement.setJson(json);
		msgElementArray.add(ob11JsonMsgElement);
		return sendPrivateMessage(userId, msgElementArray);
	}

	/**
	 * 发送私聊文本消息
	 * @param userId 私聊ID
	 * @param text 文本内容
	 * @return 消息ID
	 */
	public long sendPrivateTextMessage(long userId, String text)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendPrivateMessage(userId, msgElementArray);
	}

	/**
	 * 发送私聊消息
	 * @param userId 私聊ID
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @return 消息ID
	 */
	public long sendPrivateMessage(long userId, MessageArrayEdit messageArrayEdit)
	{
		return sendMessage("private", 0, userId, messageArrayEdit);
	}

	/**
	 * 发送私聊消息
	 * @param userId 私聊ID
	 * @param msgElementArray 消息元素数组
	 * @return 消息ID
	 */
	public long sendPrivateMessage(long userId, List<? extends OB11MsgElement> msgElementArray)
	{
		return sendMessage("private", 0, userId, msgElementArray);
	}

	/**
	 * 发送文本消息到好友列表
	 * @param text 文本内容
	 * @return 消息ID列表
	 */
	public List<Long> sendFriendListTextMessage(String text)
	{
		return sendFriendListTextMessage(text, true);
	}

	/**
	 * 发送文本消息到好友列表
	 * @param text 文本内容
	 * @param noCache 获取好友ID列表是否不使用缓存
	 * @return 消息ID列表
	 */
	public List<Long> sendFriendListTextMessage(String text, boolean noCache)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendFriendListMessage(msgElementArray, noCache);
	}

	/**
	 * 发送消息到好友列表
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @return 消息ID列表
	 */
	public List<Long> sendFriendListMessage(MessageArrayEdit messageArrayEdit)
	{
		return sendFriendListMessage(messageArrayEdit, true);
	}

	/**
	 * 发送消息到好友列表
	 * @param msgElementArray 消息元素数组
	 * @return 消息ID列表
	 */
	public List<Long> sendFriendListMessage(List<? extends OB11MsgElement> msgElementArray)
	{
		return sendFriendListMessage(msgElementArray, true);
	}

	/**
	 * 发送消息到好友列表
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @param noCache 获取好友ID列表是否不使用缓存
	 * @return 消息ID列表
	 */
	public List<Long> sendFriendListMessage(MessageArrayEdit messageArrayEdit, boolean noCache)
	{
		return sendFriendListMessage(messageArrayEdit.getMessageElementList(), noCache);
	}

	/**
	 * 发送消息到好友列表
	 * @param msgElementArray 消息元素数组
	 * @param noCache 获取好友ID列表是否不使用缓存
	 * @return 消息ID列表
	 */
	public List<Long> sendFriendListMessage(List<? extends OB11MsgElement> msgElementArray, boolean noCache)
	{
		List<Long> messageIdList = new ArrayList<>();
		List<Long> friendIdList = bot.getAccountApiService().getFriendIdList(noCache);
		if (!friendIdList.isEmpty())
		{
			friendIdList.forEach(friendId ->
			{
				messageIdList.add(sendPrivateMessage(friendId, msgElementArray));
			});
		}
		return messageIdList;
	}

	/**
	 * 发送文本消息到群组列表
	 * @param text 文本内容
	 * @return 消息ID列表
	 */
	public List<Long> sendGroupListTextMessage(String text)
	{
		return sendGroupListTextMessage(text, true);
	}

	/**
	 * 发送文本消息到从群组列表
	 * @param text 文本内容
	 * @param noCache 获取群组ID列表是否不使用缓存
	 * @return 消息ID列表
	 */
	public List<Long> sendGroupListTextMessage(String text, boolean noCache)
	{
		List<OB11MsgElement> msgElementArray = new ArrayList<>();
		OB11TextMsgElement ob11TextMsgElement = new OB11TextMsgElement();
		ob11TextMsgElement.setText(text);
		msgElementArray.add(ob11TextMsgElement);
		return sendGroupListMessage(msgElementArray, noCache);
	}

	/**
	 * 发送消息到群组列表
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @return 消息ID列表
	 */
	public List<Long> sendGroupListMessage(MessageArrayEdit messageArrayEdit)
	{
		return sendGroupListMessage(messageArrayEdit, true);
	}

	/**
	 * 发送消息到群组列表
	 * @param msgElementArray 消息元素数组
	 * @return 消息ID列表
	 */
	public List<Long> sendGroupListMessage(List<? extends OB11MsgElement> msgElementArray)
	{
		return sendGroupListMessage(msgElementArray, true);
	}

	/**
	 * 发送消息到群组列表
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @param noCache 获取群组ID列表是否不使用缓存
	 * @return 消息ID列表
	 */
	public List<Long> sendGroupListMessage(MessageArrayEdit messageArrayEdit, boolean noCache)
	{
		return sendGroupListMessage(messageArrayEdit.getMessageElementList(), noCache);
	}

	/**
	 * 发送消息到群组列表
	 * @param msgElementArray 消息元素数组
	 * @param noCache 获取群组ID列表是否不使用缓存
	 * @return 消息ID列表
	 */
	public List<Long> sendGroupListMessage(List<? extends OB11MsgElement> msgElementArray, boolean noCache)
	{
		List<Long> messageIdList = new ArrayList<>();
		List<Long> groupIdList = bot.getGroupApiService().getGroupIdList(noCache);
		if (!groupIdList.isEmpty())
		{
			groupIdList.forEach(groupId ->
			{
				messageIdList.add(sendGroupMessage(groupId, msgElementArray));
			});
		}
		return messageIdList;
	}

	/**
	 * 发送消息
	 * @param messageType 消息类型
	 * @param groupId 群聊ID
	 * @param userId 私聊ID
	 * @param messageArrayEdit 消息元素数组编辑实例
	 * @return 消息ID
	 */
	public long sendMessage(String messageType, long groupId, long userId, MessageArrayEdit messageArrayEdit)
	{
		return sendMessage(messageType, groupId, userId, messageArrayEdit.getMessageElementList());
	}

	/**
	 * 发送消息
	 * @param messageType 消息类型
	 * @param groupId 群聊ID
	 * @param userId 私聊ID
	 * @param msgElementArray 消息元素数组
	 * @return 消息ID
	 */
	public long sendMessage(String messageType, long groupId, long userId, List<? extends OB11MsgElement> msgElementArray)
	{
		BotServer botServer = null;
		JSONObject rootObject = new JSONObject();
		if (messageType.equals("group"))
		{
			botServer = new BotServer(bot, OB11MessageApiService.OB11MessageApiPath.SEND_GROUP_MSG.getValue());
			rootObject.put("group_id", groupId);
		}
		else if (messageType.equals("private"))
		{
			botServer = new BotServer(bot, OB11MessageApiService.OB11MessageApiPath.SEND_PRIVATE_MSG.getValue());
			rootObject.put("user_id", userId);
		}
		else
		{
			LOGGER.error("未知消息发送类型");
			return -1;
		}
		JSONArray messageArray = new JSONArray();
		msgElementArray.forEach(ob11MsgElement -> messageArray.put(ob11MsgElement.toJSONObject()));
		rootObject.put("message", messageArray);
		BotServer.APIRequestResult apiRequestResult = null;
		if (botServer != null)
		{
			apiRequestResult = botServer.sendRequest(rootObject.toString());
			if (apiRequestResult != null)
			{
				if (apiRequestResult.data != null)
				{
					if (apiRequestResult.data instanceof JSONObject dataObject)
					{
						if (apiRequestResult.isSuccess) return dataObject.getLong("message_id");
						else
						{
							int retCode = apiRequestResult.retcode;
							String errorMsg = apiRequestResult.message;
							LOGGER.error("sendMessage -> ".concat(String.valueOf(retCode)).concat(":").concat(errorMsg));
						}
					}
				}
			}
		}
		return -1;
	}

	/**
	 * 转发群聊消息到群聊
	 * @param groupId 群聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardGroupMessageToGroup(long groupId, long messageId)
	{
		return forwardGroupMessage("group", groupId, 0, messageId);
	}

	/**
	 * 转发私聊消息到群聊
	 * @param userId 私聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardGroupMessageToPrivate(long userId, long messageId)
	{
		return forwardGroupMessage("group", 0, userId, messageId);
	}

	/**
	 * 转发群聊消息
	 * @param forwardTargetType 转发目标类型
	 * @param groupId 群聊ID
	 * @param userId 私聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardGroupMessage(String forwardTargetType, long groupId, long userId, long messageId)
	{
		return forwardMessage("group", forwardTargetType, groupId, userId, messageId);
	}

	/**
	 * 转发私聊消息到群聊
	 * @param groupId 群聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardPrivateMessageToGroup(long groupId, long messageId)
	{
		return forwardPrivateMessage("group", groupId, 0, messageId);
	}

	/**
	 * 转发私聊消息到私聊
	 * @param userId 私聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardPrivateMessageToPrivate(long userId, long messageId)
	{
		return forwardPrivateMessage("private", 0, userId, messageId);
	}

	/**
	 * 转发私聊消息
	 * @param forwardTargetType 转发目标类型
	 * @param groupId 群聊ID
	 * @param userId 私聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardPrivateMessage(String forwardTargetType, long groupId, long userId, long messageId)
	{
		return forwardMessage("private", forwardTargetType, groupId, userId, messageId);
	}

	/**
	 * 转发消息
	 * @param forwardMessageType 转发消息类型
	 * @param forwardTargetType 转发目标类型
	 * @param groupId 群聊ID
	 * @param userId 私聊ID
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult forwardMessage(String forwardMessageType, String forwardTargetType, long groupId, long userId, long messageId)
	{
		BotServer botServer = null;
		JSONObject rootObject = new JSONObject();
		if (forwardMessageType.equals("group"))
		{
			botServer = new BotServer(bot, OB11MessageApiPath.FORWARD_GROUP_SINGLE_MSG.getValue());
			if (forwardTargetType.equals("group")) rootObject.put("group_id", groupId);
			else if (forwardTargetType.equals("private")) rootObject.put("user_id", userId);
			else LOGGER.error("未知群聊消息转发类型 -> {}", forwardTargetType);
		}
		else if (forwardMessageType.equals("private"))
		{
			botServer = new BotServer(bot, OB11MessageApiPath.FORWARD_FRIEND_SINGLE_MSG.getValue());
			if (forwardTargetType.equals("group")) rootObject.put("group_id", groupId);
			else if (forwardTargetType.equals("private")) rootObject.put("user_id", userId);
			else LOGGER.error("未知私聊消息转发类型 -> {}", forwardTargetType);
		}
		else LOGGER.error("未知转发消息类型 -> {}", forwardMessageType);
		rootObject.put("message_id", messageId);
		if (botServer != null)
		{
			return botServer.sendRequest(rootObject.toString());
		}
		return new BotServer.APIRequestResult();
	}

	/**
	 * 发送群聊戳一戳
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult sendGroupPoke(long groupId, long userId)
	{
		return sendPoke("group", groupId, userId);
	}

	/**
	 * 发送私聊戳一戳
	 * @param userId 私聊ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult sendPrivatePoke(long userId)
	{
		return sendPoke("private", 0, userId);
	}

	/**
	 * 发送戳一戳
	 * @param pokeType 戳戳类型
	 * @param groupId 群聊ID
	 * @param userId 用户ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult sendPoke(String pokeType, long groupId, long userId)
	{
		BotServer botServer = new BotServer(bot, OB11MessageApiPath.SEND_POKE.getValue());
		JSONObject rootObject = new JSONObject();
		if (pokeType.equals("group"))
		{
			rootObject.put("group_id", groupId);
			rootObject.put("user_id", userId);
		}
		else if (pokeType.equals("private"))
		{
			rootObject.put("user_id", userId);
		}
		else
		{
			LOGGER.error("未知戳一戳类型 -> {}", pokeType);
			return new BotServer.APIRequestResult();
		}
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 撤回消息
	 * @param messageId 消息ID
	 * @return API响应结果数据类
	 */
	public BotServer.APIRequestResult deleteMessage(long messageId)
	{
		BotServer botServer = new BotServer(bot, OB11MessageApiPath.DELETE_MSG.getValue());
		JSONObject rootObject = new JSONObject();
		rootObject.put("message_id", messageId);
		return botServer.sendRequest(rootObject.toString());
	}

	/**
	 * 撤回消息（批量）
	 * @param messageIdList 消息ID列表
	 * @return API响应结果数据类
	 */
	public List<BotServer.APIRequestResult> deleteMessage(List<Long> messageIdList)
	{
		List<BotServer.APIRequestResult> apiRequestResultList = new ArrayList<>();
		if (!messageIdList.isEmpty())
		{
			messageIdList.forEach(messageId ->
			{
				apiRequestResultList.add(deleteMessage(messageId));
			});
		}
		return apiRequestResultList;
	}
}
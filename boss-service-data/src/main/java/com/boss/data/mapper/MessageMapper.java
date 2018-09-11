/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.mapper;

import java.util.List;

import com.boss.core.db.MessageInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月8日下午2:33:25
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface MessageMapper {
	/**
	 * 更新消息
	 * @param message
	 * @return
	 */
	int updateMessage(MessageInfo message);
	/**
	 * 插入消息
	 * @param message
	 * @return
	 */
	int insertMessage(MessageInfo message);
	/**
	 * 分页查询消息
	 * @param page
	 * @param messageInfo
	 * @return
	 */
	List<MessageInfo> selectMessageByPage(Page<MessageInfo> page,MessageInfo messageInfo);
	/**
	 * 不分页查询消息
	 * @param messageInfo
	 * @return
	 */
	List<MessageInfo> selectMessageByPage(MessageInfo messageInfo);
}

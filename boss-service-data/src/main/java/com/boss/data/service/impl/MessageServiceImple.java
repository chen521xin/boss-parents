/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.MessageInfo;
import com.boss.data.mapper.MessageMapper;
import com.boss.data.service.MessageService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description 
 * @data 2018年4月8日下午3:00:36
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class MessageServiceImple extends BaseServiceImpl implements MessageService{
	  
	@Autowired
	private MessageMapper messageMapper;
	/**
	 * 更新消息
	 * @param message
	 * @return
	 */
	@Override
	public void updateMessage(MessageInfo message) {
		message.changeStatus(CommonUtils.METHOD_UPDATE,getUserName());
		 messageMapper.updateMessage(message);
	}
	/**
	 * 插入消息
	 * @param message
	 * @return
	 */
	@Override
	public void insertMessage(MessageInfo message) {
		message.changeStatus(CommonUtils.METHOD_ADD,getUserName());
		messageMapper.insertMessage(message);
	    addLog(OperationType.ADD.getOption(), BusinessUtils.MESSAGE);
	}
	/**
	 * 分页查询消息
	 * @param page
	 * @param messageInfo
	 * @return
	 */
	@Override
	public List<MessageInfo> selectMessageByPage(Page<MessageInfo> page, MessageInfo messageInfo) {
		return messageMapper.selectMessageByPage(page, messageInfo);
	}
	/**
	 * 不分页查询消息
	 * @param messageInfo
	 * @return
	 */
	@Override
	public List<MessageInfo> selectMessageByPage(MessageInfo messageInfo) {
		return messageMapper.selectMessageByPage(messageInfo);
	}

}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.db;

import java.io.Serializable;

/**
 * @description
 * @data 2018年4月8日下午2:21:16
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class MessageInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4501899487350439829L;
	/**
	 * 功能名称：如保单提醒
	 */
	private String moduleType;
	/**
	 * 
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 是否已读
	 */
	private String isAlerdyRead;
	/**
	 * 消息类型
	 */
	private String messageType;

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsAlerdyRead() {
		return isAlerdyRead;
	}

	public void setIsAlerdyRead(String isAlerdyRead) {
		this.isAlerdyRead = isAlerdyRead;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}

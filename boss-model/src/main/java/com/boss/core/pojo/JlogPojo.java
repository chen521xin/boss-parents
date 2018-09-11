/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.pojo;

import java.io.Serializable;

import com.boss.core.db.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
/**
 * 日志实体
 * 
 * @author Administrator
 *
 */
@ApiModel("日志信息")
public class JlogPojo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7784498606386377475L;
	/**
	 * 操作用户姓名
	 */
	@ApiModelProperty(value = "操作用户姓名", dataType = "java.lang.String", required = false)
	private String username;
	/**
	 * 操作用户IP
	 */
	@ApiModelProperty(value = "操作用户IP", dataType = "java.lang.String", required = false)
	private String userIp;
	/**
	 * 操作时间
	 */
	@ApiModelProperty(value = "操作时间", dataType = "java.lang.String", required = false)
	private String makeTime;//接受字符串类型日期
	/**
	 * 业务类别
	 */
	@ApiModelProperty(value = "业务类别", dataType = "java.lang.String", required = false)
	private String bizType;
	/**
	 * 操作状态
	 */
	@ApiModelProperty(value = "操作状态", dataType = "java.lang.String", required = false)
	private String status;
	/**
	 * 日志内容
	 */
	@ApiModelProperty(value = "日志内容", dataType = "java.lang.String", required = false)
	private String message;
	/**
	 * 服务名称
	 */
	@ApiModelProperty(value = "服务名称", dataType = "java.lang.String", required = false)
	private String serviceName;
	/**
	 * 服务实例Id
	 */
	@ApiModelProperty(value = "服务实例Id", dataType = "java.lang.String", required = false)
	private String instanceId;
	/**
	 * 服务实例主机
	 */
	@ApiModelProperty(value = "服务实例主机", dataType = "java.lang.String", required = false)
	private String serviceHost;
	/**
	 * 时区
	 */
	@ApiModelProperty(value = "时区", dataType = "java.lang.String", required = false)
	private String timeZone;
	/**
	 * 用户Id
	 */
	@ApiModelProperty(value = "用户Id", dataType = "java.lang.String", required = false)
	private String userId;

	private String fullName;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getServiceHost() {
		return serviceHost;
	}

	public void setServiceHost(String serviceHost) {
		this.serviceHost = serviceHost;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "Jlog [username=" + username + ", userIp=" + userIp + ", makeTime=" + makeTime + ", bizType=" + bizType
				+ ", status=" + status + ", message=" + message + ", serviceName=" + serviceName + ", instanceId="
				+ instanceId + ", serviceHost=" + serviceHost + ", timeZone=" + timeZone + ", userId=" + userId
				+ ", fullName=" + fullName + "]";
	}

}

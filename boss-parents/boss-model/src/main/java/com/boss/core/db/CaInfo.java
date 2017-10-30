/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
*/
package com.boss.core.db;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2017年10月24日下午5:32:19
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("CA白名单信息")
public class CaInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7052638934738389440L;

	/**
	 * CAID
	 */
	@ApiModelProperty(value = "CAID", dataType = "java.lang.String", required = false)
	private String id;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", dataType = "java.lang.String", required = false)
	private String username;
	/**
	 * 生效时间
	 */
	@ApiModelProperty(value = "生效时间", dataType = "java.lang.String", required = false)
	private String validSince;
	/**
	 * 失效时间
	 */
	@ApiModelProperty(value = "失效时间", dataType = "java.lang.String", required = false)
	private String expireSince;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", dataType = "java.lang.String", required = false)
	private String remark;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", dataType = "java.lang.String", required = false)
	private String createTime;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", dataType = "java.lang.String", required = false)
	private String createBy;
	/**
	 * 最后修改人
	 */
	@ApiModelProperty(value = "最后修改人", dataType = "java.lang.String", required = false)
	private String lastUpdateBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getValidSince() {
		return validSince;
	}

	public void setValidSince(String validSince) {
		this.validSince = validSince;
	}

	public String getExpireSince() {
		return expireSince;
	}

	public void setExpireSince(String expireSince) {
		this.expireSince = expireSince;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@Override
	public String toString() {
		return "CaInfo [id=" + id + ", username=" + username + ", validSince=" + validSince + ", expireSince="
				+ expireSince + ", remark=" + remark + ", createTime=" + createTime + ", createBy=" + createBy
				+ ", lastUpdateBy=" + lastUpdateBy + "]";
	}

}

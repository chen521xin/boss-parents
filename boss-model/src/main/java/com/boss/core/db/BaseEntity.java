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
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;

import com.boss.utils.cons.CommonUtils;

/**
 * @description
 * @data 2017年10月30日下午3:58:32
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6183206662169197736L;
	/**
	 * 页码
	 */
	@Min(value = 1, message = "最小值为1")
	private Integer pageNo=1;
	/**
	 * 页码大小
	 */
	@Min(value = 1, message = "最小值为1")
	private Integer pageSize=10;

	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 最后修改时间
	 */
	private Date lastUpdateTime;
	/**
	 * 最后修改人
	 */
	private String lastUpdateBy;
	/**
	 * 数据状态：1正常，其他：不正常
	 */
	private Integer rowstate;
	/**
	 * 数据版本
	 */
	private Integer version;

	public void changeStatus(String method, String username) {
		if (method.equals(CommonUtils.METHOD_ADD)) {
			this.id = uuid();
			this.createBy = username;
			this.createTime = new Date();
			this.lastUpdateBy = username;
			this.lastUpdateTime = new Date();
			this.version = 1;
			this.rowstate = 1;
		} else if (method.equals(CommonUtils.METHOD_UPDATE)) {
			this.lastUpdateBy = username;
			this.lastUpdateTime = new Date();
		}
	}

	public String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

 

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Integer getRowstate() {
		return rowstate;
	}

	public void setRowstate(Integer rowstate) {
		this.rowstate = rowstate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	

}

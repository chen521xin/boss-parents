/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.enums;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


/**
 * 业务模块操作类型
 * @author Administrator
 *
 */
public enum OperationType {

	ADD("新增"),
	UPDATE("更新"),
	DELETE("删除"),
	LOGIN("登入"),
	LOGOUT("登出");
	private String option;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	private OperationType(String option) {
		this.option = option;
	}
}

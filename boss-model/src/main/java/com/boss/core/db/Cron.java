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
 * @data 2018年4月6日下午5:15:26
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class Cron extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2369449359311423130L;

	   private String cron;
	   private String moduleType;

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	} 
	 
	
}

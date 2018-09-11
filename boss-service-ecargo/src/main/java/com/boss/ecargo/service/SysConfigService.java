/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service;

import java.util.Map;

import com.boss.core.db.SysConfig;

/**
 * @description 
 * @data 2018年6月6日上午0:28:25
 * @author long.cai
 * @version v1.0
 * @since v1.0
 *
 **/
public interface SysConfigService {
	
	/**
	 * 根据业务类型获取参数集合
	 * @param busType 业务类型
	 * @return
	 */
	public Map<String,SysConfig> findByBusinessType(String busType);
	
	/**
	 * 更新参数
	 * @param sysConfig
	 * @return
	 */
	public int updateSysConfig(SysConfig sysConfig);
}

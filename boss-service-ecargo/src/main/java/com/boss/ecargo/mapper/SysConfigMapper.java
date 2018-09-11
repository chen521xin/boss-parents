/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.mapper;

import java.util.List;

import com.boss.core.db.SysConfig;

/**
 * @description
 * @data 2018年5月22日下午3:10:14
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface SysConfigMapper {
	int deleteSysConfig(SysConfig config);

	int updateSysConfig(SysConfig config);

	List<SysConfig> selectSysConfig(SysConfig config);

	int insertSysconfig(SysConfig config);
}

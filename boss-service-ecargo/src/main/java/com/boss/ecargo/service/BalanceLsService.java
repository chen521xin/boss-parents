/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boss.core.db.BalanceLsInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月21日上午12:40:45
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface BalanceLsService {
	void insertBalanceLs(BalanceLsInfo balanceLs);
	Page<BalanceLsInfo> selectBalanceLs(Page<BalanceLsInfo> page,BalanceLsInfo ls);
	void export(BalanceLsInfo ls,HttpServletRequest request,HttpServletResponse response);
}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service;

import com.boss.core.db.BalanceInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月20日下午11:54:39
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface BalanceService {
	void updateBalance(BalanceInfo balance);  
	void ecargoUpdateBalance(BalanceInfo balance);  
	BalanceInfo queryBalanceById(BalanceInfo balance); 
	Page<BalanceInfo> queryBalanceByPage(Page<BalanceInfo> page,BalanceInfo balance); 
}

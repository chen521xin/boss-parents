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

import com.boss.core.db.BalanceInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月20日下午11:36:37
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface BalanceMapper {

	void updateBalance(BalanceInfo balance);   
	BalanceInfo queryBalanceByUserId(BalanceInfo balanceInfo); 
	List<BalanceInfo> queryBalanceByPage(Page<BalanceInfo> page,BalanceInfo balance); 
	List<BalanceInfo> queryBalanceByPageAndProxy(Page<BalanceInfo> page,BalanceInfo balance); 
	void insertBalance(BalanceInfo balance); 
	
	
}

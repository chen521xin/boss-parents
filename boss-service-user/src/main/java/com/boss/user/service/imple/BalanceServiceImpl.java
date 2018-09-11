/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.BalanceInfo;
import com.boss.user.mapper.BalanceMapper;
import com.boss.user.service.BalanceService;
import com.boss.utils.cons.CommonUtils;

/**
 * @description 
 * @data 2018年4月20日下午11:55:04
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=RuntimeException.class)
public class BalanceServiceImpl extends BaseServiceImpl implements BalanceService{

	@Autowired
	private BalanceMapper balanceMapper;

	@Override
	public void insertBalance(BalanceInfo balance) {
		balance.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		balanceMapper.insertBalance(balance);
	}
	


}

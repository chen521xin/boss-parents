/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.BalanceInfo;
import com.boss.core.db.BalanceLsInfo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.ecargo.mapper.BalanceMapper;
import com.boss.ecargo.service.BalanceLsService;
import com.boss.ecargo.service.BalanceService;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description 
 * @data 2018年4月20日下午11:55:04
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class BalanceServiceImpl extends BaseServiceImpl implements BalanceService{
	private static Logger logger = LoggerFactory.getLogger(BalanceServiceImpl.class);
	@Autowired
	private BalanceMapper balanceMapper;
	
	@Autowired
	private BalanceLsService balanceLsService;
	
	@Override
	public void updateBalance(BalanceInfo balance) {
		
		ecargoUpdateBalance(balance);
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.BALANCE);
	}

	@Override
	public BalanceInfo queryBalanceById(BalanceInfo balance) {
		return balanceMapper.queryBalanceByUserId(balance);
	}

	@Override	
	public Page<BalanceInfo> queryBalanceByPage(Page<BalanceInfo> page,BalanceInfo balance) {
		String userRole=getUserRole();
		switch(userRole){
		case CommonUtils.ROLE_ADMIN:
			balanceMapper.queryBalanceByPage(page, balance);
			break;
		case CommonUtils.ROLE_PROXY:
			balanceMapper.queryBalanceByPageAndProxy(page, balance);
			break;
		default:
			throw new BizException(BizCode.REQUEST_EXCEPTION);
		}
	
		return page;
	}
	
	public void insertBalance(BalanceInfo balanceInfo){
		BalanceInfo balance = new BalanceInfo();
		balance.setUserId(balanceInfo.getUserId());
		balance.setAccountNum(balanceInfo.getAccountNum()==null?CommonUtils.USER_ACCOUNT:balanceInfo.getAccountNum());
		balance.setTotalBalance("0");
		balance.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		balanceMapper.insertBalance(balance);
		balanceInfo.setId(balance.getId());
	}

	@Override
	public void ecargoUpdateBalance(BalanceInfo balance) {
		//发生后总额
				
				//发生额
				BigDecimal fse=new BigDecimal(balance.getFse());
				BalanceInfo balanceInfo = queryBalanceById(balance);
				BigDecimal totalBalance=new BigDecimal("0");
				if(balanceInfo!=null){		
					totalBalance=new BigDecimal(balanceInfo.getTotalBalance());
					balance.setId(balanceInfo.getId());
				}else{
					logger.info(String.format("--> 用户:%s,系统不存在账户，进行开户操作", getUserName()));
					insertBalance(balance);
				}
				//获取发生后总额
				BigDecimal fshBalance=getFshBalance(balance.getDirectionOf(),totalBalance,fse);	
				balance.setFsqBalance(balanceInfo.getTotalBalance());
				balance.setTotalBalance(String.valueOf(fshBalance));
				//更新余额
				updateBalanceById(balance);
				//插入流水
				insertBalanceLs(balance);
		
	}
	
	public void insertBalanceLs(BalanceInfo balance){
		//记入流水
		BalanceLsInfo ls =new BalanceLsInfo();
		ls.setBalanceId(balance.getId());
		ls.setFse(balance.getFse());
		ls.setBeforeBalance(balance.getFsqBalance());
		ls.setAfterBalance(balance.getTotalBalance());
		ls.setModule(balance.getModule());
		ls.setDirectionOf(balance.getDirectionOf());
		ls.setEcargoPolicyNo(balance.getEcargoPolicyNo());
		balanceLsService.insertBalanceLs(ls);
	}
	public BigDecimal getFshBalance(String fsfx,BigDecimal totalBalance,BigDecimal fse){
        
		//计算发生后基恩
		if(fsfx.equals(CommonUtils.DIRECTION_OF_ADD)){
			return totalBalance.add(fse);
		}else if(fsfx.equals(CommonUtils.DIRECTION_OF_REDUCE)){
		
			if(totalBalance.subtract(fse).compareTo(new BigDecimal("0"))<0){
				
				throw new BizException(BizCode.BALANCE_EXCEPTION);
			}
			return totalBalance.subtract(fse);
		}else{
			throw new BizException(BizCode.FSFX_EXCEPTION);
		}
	}
	
	public void updateBalanceById(BalanceInfo balance){
		//更新谁的余额信息
		balance.setUserId(balance.getUserId());
		balance.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		balanceMapper.updateBalance(balance);
		logger.info(String.format("--> 用户:%s,账户：%s,发生前总额：%s，发生额：%s，发生后总额：%s", getUserName(),balance.getAccountNum(),balance.getFsqBalance(),balance.getFse(),balance.getTotalBalance()));

	}

}

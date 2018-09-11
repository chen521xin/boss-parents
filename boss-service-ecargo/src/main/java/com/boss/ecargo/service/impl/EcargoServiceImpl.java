/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.BalanceInfo;
import com.boss.core.db.EcargoInfo;
import com.boss.core.db.User;
import com.boss.core.db.UserRateInfo;
import com.boss.core.pojo.EcargoPojo;
import com.boss.core.pojo.EcargoProjo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.ecargo.mapper.EcargoMapper;
import com.boss.ecargo.service.EcargoService;
import com.boss.ecargo.util.EcargoUtils;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;
import com.boss.utils.exceptions.IdentCardException;

/**
 * @description
 * @data 2018年5月11日下午3:22:40
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class EcargoServiceImpl extends BaseServiceImpl implements EcargoService {
	private static Logger logger = LoggerFactory.getLogger(EcargoServiceImpl.class);
	@Autowired
	private EcargoMapper ecargoMapper;

	@Autowired
	private EcargoUtils ecargoUtils;
	
	@Autowired
	private BalanceServiceImpl balanceService;

	@Value("${boss.default.premium}")
	private String defaultPremium;

	@Value("${boss.admin.account}")
	private String adminAccount;

	@Override
	public Page<EcargoPojo> selectByPage(Page<EcargoPojo> page, EcargoPojo ecargo) {
		switch (getUserRole()) {
		case CommonUtils.ROLE_USER:
			ecargoMapper.selectByPageByUserId(page, ecargo);	
			break;
		case CommonUtils.ROLE_PROXY:
			
			ecargoMapper.selectByPageProx(page, ecargo);			
			break;
		case CommonUtils.ROLE_ADMIN:
			ecargoMapper.selectByPage(page, ecargo);
			break;
		default:
			break;
		}
		
		List<EcargoPojo> list = page.getResult();
		if (list != null && list.size() > 0) {
			for (EcargoPojo ecargoInfo : list) {
				convertParamOut(ecargoInfo);
			}
		}

		return page;
	}

	@Override
	public EcargoPojo selectEcargoByNo(String ecargoNo) {
		EcargoPojo ecargoInfo = ecargoMapper.selectEcargoByNo(ecargoNo);
		convertParamOut(ecargoInfo);
		return ecargoInfo;
	}
	
	@Override
	public EcargoPojo selectEcargoApplyNo(String applyNo) {
		EcargoPojo ecargoInfo = ecargoMapper.selectEcargoByApplyNo(applyNo);
		convertParamOut(ecargoInfo);
		return ecargoInfo;
	}

	public void convertParamOut(EcargoPojo ecargo) {
		String superType = ecargo.getSuperTypeQuery();
		ecargo.setIsFaildQuery(ecargo.getIsFaildQuery().equals("1") ? "成功" : "失败");
		ecargo.setSuperTypeQuery(getCodeNameByPidAndValue(CommonUtils.DALEI, ecargo.getSuperTypeQuery()));
		ecargo.setPackagesQuery(getCodeNameByPidAndValue(CommonUtils.PACKAGES, ecargo.getPackagesQuery()));
		ecargo.setDisputeFunctionQuery(
				getCodeNameByPidAndValue(CommonUtils.DISPUTE_FUNCTION, ecargo.getDisputeFunctionQuery()));
		ecargo.setInsuranceTypeQuery(
				getCodeNameByPidAndValue(CommonUtils.INSURANCE_TYPE, ecargo.getInsuranceTypeQuery()));
		if (superType != null) {
			switch (superType) {
			case "1":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_1, ecargo.getTypeQuery()));
				break;
			case "2":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_2, ecargo.getTypeQuery()));
				break;
			case "3":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_3, ecargo.getTypeQuery()));
				break;
			case "4":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_4, ecargo.getTypeQuery()));
				break;
			case "5":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_5, ecargo.getTypeQuery()));
				break;
			case "6":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_6, ecargo.getTypeQuery()));
				break;
			case "7":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_7, ecargo.getTypeQuery()));
				break;
			case "8":
				ecargo.setTypeQuery(getCodeNameByPidAndValue(CommonUtils.XL_8, ecargo.getTypeQuery()));
				break;
			default:
				ecargo.setTypeQuery("");
			}
		}

	}

	@Override
	public EcargoProjo insertEcargo(EcargoInfo ecargoInfo) throws ClientProtocolException, IOException, URISyntaxException, IdentCardException, ParseException {

		jizhang(ecargoInfo);

		// 插入ecargo表
		ecargoInfo.setIsFaild("0");
		ecargoInfo.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		ecargoInfo.setUserId(getUserId());
		//投保官网投保
		EcargoProjo ecargoProjo=ecargoUtils.sendEcargoInfo(ecargoInfo);
		
		
		//投保成功，代表投保操作成功，就是投保成功投到保险公司，并不代表核保就通过
		if(ecargoProjo.getEcargoMsg().getResult())
		{
			
			ecargoMapper.insertEcargo(ecargoInfo);
			
			// 调用ecargo方法 TODO 魔法数字修改
			/*String ecargoNo = String.format("ECO%s",
				DateFormatUtils.DateFormatetoString(new Date(), CommonUtils.DATE_FORMATE_TOTAL_DEAL_NO));
		ecargoInfo.setPolicyNo(ecargoNo);*/
			ecargoInfo.setIsFaild("1");
			
			updateEcargo(ecargoInfo);
			addLog(OperationType.ADD.getOption(), BusinessUtils.ECARGO);
			
			return ecargoProjo;
		}
		else
		{
			return ecargoProjo;
		}
	}

	public void updateEcargo(EcargoInfo ecargoInfo) {
		// 调用蔡龙方法发送投保
		String ecargoNo = "";
		EcargoInfo update = new EcargoInfo();
		update.setId(ecargoInfo.getId());
		update.setPolicyNo(ecargoNo);
		update.setIsFaild(ecargoInfo.getIsFaild());
		update.setFaildMessage(ecargoInfo.getFaildMessage());
		update.setApplyStatus(ecargoInfo.getApplyStatus());
		update.setApplyPolicyNo(ecargoInfo.getApplyPolicyNo());
		
		ecargoMapper.updatePolicyNo(ecargoInfo);
	}

	/**
	 * 获取当前用户的投保种类费率
	 * 
	 * @param ecargoInfo
	 * @return
	 */
	public String getUserRate(EcargoInfo ecargoInfo) {
		UserRateInfo userRate = new UserRateInfo();
		userRate.setUserId(getUserId());
		userRate.setInsuranceType(ecargoInfo.getInsuranceType());
		String rate = ecargoMapper.findUserRate(userRate);
		if (rate == null || rate.trim().equals("null") || rate.trim().equals("")) {
			throw new BizException(BizCode.USER_RATE_IS_NULL);
		}
		return rate;
	}

	public void jizhang(EcargoInfo ecargoInfo) {
		String rate = getUserRate(ecargoInfo);		
		// 投保用户费率
		BigDecimal currentUserRate = StringUtil.getDecimalMillesimal(rate);
		ecargoInfo.setEcRatio(rate);//不要动
		// 保额
		BigDecimal totalSumamount = new BigDecimal(ecargoInfo.getSumamount());
		// 得到当前保费
		BigDecimal currenteUserPremium = getCurrentRateMoney(currentUserRate, totalSumamount);
		// 当前保单的保费
		ecargoInfo.setPremium(String.valueOf(currenteUserPremium));
		logger.info(String.format("-->保单流水号：%s,当前投保用户:%s,用户id:%s,费率：%s,保额：%s,本次保费:%s", ecargoInfo.getId(),
				getUserName(), getUserId(), rate, totalSumamount, currenteUserPremium));

		// 默认管理员账户金额为当前用户保费，若有代理，则为减去代理抽成部分
		BigDecimal adminMoney = currenteUserPremium;

		// 用户记账减少
		updateBalance(currenteUserPremium, CommonUtils.DIRECTION_OF_REDUCE, CommonUtils.USER_ACCOUNT,
				ecargoInfo.getId(), getUserId());
		logger.info(String.format("-->保单流水号：%s,当前用户%s,扣账成功，发生额：%s", ecargoInfo.getId(), getUserName(),
				currenteUserPremium));

		// 代理记账，得到代理扣除总费用
		BigDecimal totalProxyMoney = proxyJiZhang(ecargoInfo, currenteUserPremium, totalSumamount);

		// 默认虚拟账户金额为当前用户保费，若有代理，则为减去代理抽成部分
		adminMoney = currenteUserPremium.subtract(totalProxyMoney);

		// 剩余爆粉=用户总保费-代理总保费 记入公共虚拟账户。用于流水勾稽核对
		updateBalance(adminMoney, CommonUtils.DIRECTION_OF_ADD, CommonUtils.USER_ACCOUNT, ecargoInfo.getId(),
				adminAccount);
	}

	/**
	 * 
	 * @param ecargoInfo
	 * @param currenteUserPremium
	 *            当前用户投保总额
	 * @param totalSumamount
	 * 
	 * @return
	 */
	public BigDecimal proxyJiZhang(EcargoInfo ecargoInfo, BigDecimal currenteUserPremium, BigDecimal totalSumamount) {

		// 为所属代理进行抽成计算
		User parentUser = getUser(getUserId(), ecargoInfo.getInsuranceType());
		if (parentUser != null) {
			// 代理余额增加
			BigDecimal totalProxyMoney = updateProxyBalance(parentUser.getId(), currenteUserPremium, totalSumamount,
					new BigDecimal("0"), ecargoInfo.getId(),
					StringUtil.getDecimalMillesimal(parentUser.getCurrentUserRate()), ecargoInfo.getInsuranceType());
			return totalProxyMoney;
		}
		return new BigDecimal("0");
	}

	/**
	 * 计算当前投保用户费率 最低保费2元
	 * 
	 * @param currentUserRate
	 * @param totalSumamount
	 * @return
	 */
	public BigDecimal getCurrentRateMoney(BigDecimal currentUserRate, BigDecimal totalSumamount) {
		BigDecimal premium = totalSumamount.multiply(currentUserRate).setScale(2, BigDecimal.ROUND_UP);
		if (premium.compareTo(new BigDecimal(defaultPremium)) < 0) {
			premium = new BigDecimal(defaultPremium);
		}
		return premium;
	}

	/**
	 * 思路 查询userId的上级代理的id与费率 计算出上级代理的保费 每笔投保代理抽成=当前用户的保费-上级代理的保费
	 * 
	 * @param userId
	 *            用户id
	 * @param currenteUserPremium
	 *            投保用户保费总金额
	 * @param totalSumamount
	 *            总保额
	 * @param totalProxySumamount
	 *            各级代理的总抽成
	 */
	public BigDecimal updateProxyBalance(String userId, BigDecimal currenteUserPremium, BigDecimal totalSumamount,
			BigDecimal totalProxySumamount, String id, BigDecimal userRate, String type) {
		// 计算保费，得到费率
		BigDecimal proxyPremium = totalSumamount.multiply(userRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		// 得到当前代理赚取的抽成
		BigDecimal proxyMoney = currenteUserPremium.subtract(proxyPremium);
		// 各级代理抽成相加
		totalProxySumamount = totalProxySumamount.add(proxyMoney);
		updateBalance(proxyMoney, CommonUtils.DIRECTION_OF_ADD, CommonUtils.USER_ACCOUNT, id, userId);
		logger.info(String.format("--> 保单流水号：%s,投保人员：%s,上级代理id：%s,投保总金额：%s,费率：%s,总保费：%s,代理级别保费:%s,抽成：%s", id,
				getUserName(), userId, totalSumamount, userRate, currenteUserPremium, proxyPremium, proxyMoney));
		User user = getUser(userId, type);
		if (user != null) {
			if (user.getParentId() != null) {
				updateProxyBalance(user.getParentId(), currenteUserPremium, totalSumamount, totalProxySumamount, id,
						new BigDecimal(user.getCurrentUserRate()), type);
			}
		}

		return totalProxySumamount;
	}

	public User getUser(String userId, String type) {
		EcargoInfo info = new EcargoInfo();
		info.setUserId(userId);
		info.setInsuranceType(type);
		// 如果当前进行计算的用户有上级代理，则给上级代理进行代理费用计算
		User user = ecargoMapper.findParentIdAndUserRate(info);
		if (user == null) {
			logger.info(String.format("未找到用户 %s 的上级代理用户", getUserName()));
			return user;
		}
		if (user.getCurrentUserRate() == null) {
			logger.info(String.format("用户 %s 的上级代理用户的%s未设置，上级代理用户id为：%s", getUserName(),
					CommonUtils.RATE_TYPE_1.equals(type) ? "综合费率" : "一般费率", user.getId()));
			throw new BizException(BizCode.USER_PAR_RATE_IS_NULL);
		}
		return user;
	}

	/**
	 * 增加金额\减少金额
	 * 
	 * @param fse
	 */
	public void updateBalance(BigDecimal fse, String fsfx, String accountNum, String id, String userId) {
		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo.setEcargoPolicyNo(id);
		balanceInfo.setDirectionOf(fsfx);
		balanceInfo.setFse(String.valueOf(fse));
		balanceInfo.setUserId(userId);
		balanceInfo.setAccountNum(accountNum);
		balanceInfo.setEcargoPolicyNo(id);
		balanceInfo.setModule(BusinessUtils.ECARGO);
		balanceService.ecargoUpdateBalance(balanceInfo);
	}

}

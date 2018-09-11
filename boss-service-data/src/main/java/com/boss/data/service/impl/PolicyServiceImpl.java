/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Insurcomp;
import com.boss.core.db.Policy;
import com.boss.core.db.Stream;
import com.boss.core.db.User;
import com.boss.core.pojo.InsurcompAndPolicyType;
import com.boss.core.pojo.PolicyPojo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.DetailsMapper;
import com.boss.data.mapper.PolicyMapper;
import com.boss.data.service.InsurcompService;
import com.boss.data.service.PolicyService;
import com.boss.data.service.StreamService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.ExportExcel;
import com.boss.utils.enums.OperationType;
import com.boss.utils.excel.ExcelUtils;

/**
 * @description
 * @data 2018年3月12日下午7:54:04
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PolicyServiceImpl extends BaseServiceImpl implements PolicyService {

	@Autowired
	private PolicyMapper policyMapper;

	@Autowired
	private StreamService streamServcie;

	@Autowired
	private InsurcompService insurcompService;

	@Autowired
	private DetailsMapper detailsMapper;

	/**
	 * 根据userId查询保单
	 * 
	 * @param userId
	 * @return
	 */
	public List<Policy> getPolicyByUserId(Page<InsurcompAndPolicyType> page,
			InsurcompAndPolicyType insurcompAndPolicyType) {
		Insurcomp insurcomp = null;
		insurcompAndPolicyType.setUserId(getUserId());
		if (("1").equals(insurcompAndPolicyType.getIsCompany())) {
			insurcomp = insurcompService.queryInsurcompByFullName(CommonUtils.ZHONG_GUO_REN_SHOU_BAO_XIAN);
		} else if (("2").equals(insurcompAndPolicyType.getIsCompany())) {
			insurcomp = insurcompService.queryInsurcompByFullName(CommonUtils.REN_MIN_BAO_XIAN_GONG_SI);
		} else if (("3").equals(insurcompAndPolicyType.getIsCompany())) {
			insurcomp = insurcompService.queryInsurcompByFullName(CommonUtils.GUO_SHOU_CAI_XIAN);
		}
		if (insurcomp != null) {
			insurcompAndPolicyType.setInsurcompId(insurcomp.getId());
		} else {
			throw new BizException(BizCode.INSURCOMP_INEXISTENCE);
		}
		List<Policy> policyList = policyMapper.getPolicyByUserId(page, insurcompAndPolicyType);
		for (Policy policy : policyList) {
			policy.setProductTypeName(getCodeNameByPidAndValue(CommonUtils.POLICY_TYPE, policy.getProductTypeValue()));
			policy.setTransDealNo(getTotalDealNo(policy.getPolicyNo(), CommonUtils.DATE_FORMATE_TOTAL_DEAL_NO));
		}
		getAleradySendTime(policyList);
		return policyList;
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param policy
	 * @return
	 */
	@Override
	public Page<Policy> getByPage(Page<Policy> page, Policy policy) {
		switch (getUserRole()) {
		case CommonUtils.ROLE_ADMIN:
			policyMapper.getByPage(page, policy);
			break;
		case CommonUtils.ROLE_INSURANCE_MAN:
			policy.setInsurcompId(getInsurcompId());
			policyMapper.findByRoleInsurance(page, policy);
			break;
		case CommonUtils.ROLE_PROXY:
			policy.setUserId(getUserId());
			policyMapper.findByRolePoxy(page, policy);
			break;
		default:
			throw new BizException(BizCode.THERE_IS_NO_SOUECH_TYPE);
		}
		getAleradySendTime(page.getResult());
		return page;
	}

	/**
	 * 查询保单号是否存在
	 * 
	 * @param policyNo
	 * @return
	 */
	public Policy getByPolicyNo(String policyNo) {
		return policyMapper.getByPolicyNo(policyNo);
	}

	/**
	 * 根据productId查询保单是否存在
	 * 
	 * @param productId
	 * @return
	 */
	public int getPolicyByProductId(String productId) {
		return policyMapper.getPolicyByProductId(productId);
	}

	/**
	 * 添加保单
	 * 
	 * @param policy
	 * @author retrun
	 */
	@Override
	public boolean addPolicy(Policy policy) {
		Policy policys = getByPolicyNo(policy.getPolicyNo());
		if (policys != null) {
			throw new BizException(BizCode.FAILD_POLICYNO_ALREADY_EXIST);
		}	
		policy.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		this.num = policyMapper.addPolicy(policy);
		if (num < 0) {
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
		addLog(OperationType.ADD.getOption(), BusinessUtils.POLICY);
		initStream(policy);
		return true;
	}

	public void initStream(Policy policy) {
		Stream st = new Stream();
		st.setPolicyNo(policy.getPolicyNo());
		st.setBeforeBalance(policy.getPolicyTotalMoney());
		st.setAccrual("0");
		st.setAfterBalance(policy.getPolicyTotalMoney());
		st.setDirectionOf(CommonUtils.DIRECTION_OF_REDUCE);
		streamServcie.insertStream(st);
	}

	/**
	 * 更新保单
	 * 
	 * @param policy
	 * @return
	 */
	@Override
	public boolean updatePolicy(Policy policy) {
		policy.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = policyMapper.updatePolicy(policy);
		if (num < 0) {
			throw new BizException(BizCode.FAILD_UPDATE_EXCEPTION);
		}
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.POLICY);
		return true;
	}

	/**
	 * 根据保单id查询保单的保单的总额、剩余额度、剩余车次
	 */
	public Policy getPolicyByPolicyId(String policyId) {
		return policyMapper.getPolicyByPolicyId(policyId);
	}

	/**
	 * 确定发生后可用金额
	 * 
	 * @param policy
	 * @param surplusCarNoMoney
	 * @return
	 */
	public String convertPolicyAfterNum(PolicyPojo policy, String surplusCarNoMoney) {
		if (CommonUtils.DIRECTION_OF_ADD.equals(policy.getDirectionOf())) {
			return String.valueOf(new BigDecimal(surplusCarNoMoney).add(new BigDecimal(policy.getAccrual())));
		}
		if (CommonUtils.DIRECTION_OF_REDUCE.equals(policy.getDirectionOf())) {
			// 减少
			BigDecimal after = new BigDecimal(surplusCarNoMoney).subtract((new BigDecimal(policy.getAccrual())));
			if (after.compareTo(new BigDecimal("0")) < 0) {
				throw new BizException(BizCode.INSUFFICIENT_BALANCE);
			}
			if (after.compareTo(new BigDecimal("0")) == 0) {
				if (policy.getFlag() == 3) {
					policy.setState(CommonUtils.POLICY_STATE_TRAIN_NUM);

				} else if (policy.getFlag() == 2) {
					policy.setState(CommonUtils.POLICY_STATE_INSURE_AMOUNT);
				}
			}
			return String.valueOf(after);
		}
		return "0";
	}

	/**
	 * 插入流水
	 * 
	 * @param policyPojo
	 * @param policy
	 * @param afterBalance
	 * @param beforeBalance
	 */
	public void insertStream(PolicyPojo policyPojo, Policy policy, String afterBalance, String beforeBalance) {
		Stream st = new Stream();
		st.setPolicyNo(policy.getPolicyNo());
		st.setBeforeBalance(beforeBalance);
		st.setAccrual(policyPojo.getAccrual());
		st.setAfterBalance(afterBalance);
		st.setDirectionOf(policyPojo.getDirectionOf());
		streamServcie.insertStream(st);
	}

	/**
	 * 确定发生后总金额/总车次
	 * 
	 * @param totalMoney
	 * @param accrual
	 * @param directionOf
	 * @return
	 */
	public String convertPolicyTotalNum(String totalMoney, String accrual, String directionOf) {
		if (CommonUtils.DIRECTION_OF_ADD.equals(directionOf)) {
			return String.valueOf(new BigDecimal(totalMoney).add(new BigDecimal(accrual)));
		}
		if (CommonUtils.DIRECTION_OF_REDUCE.equals(directionOf)) {
			// 减少
			return String.valueOf(new BigDecimal(totalMoney).subtract((new BigDecimal(accrual))));
		}
		return "0";
	}

	/**
	 * 续保
	 * 
	 * @param policyPojo
	 */
	@Override
	public void addPolicyBalance(PolicyPojo policyPojo) {
		Policy policy = getPolicyByPolicyId(policyPojo.getId());
		updatePolicyBalance(policyPojo, policy);
	}

	/**
	 * 保额投保更新额度信息
	 * 
	 * @param policyPojo
	 * @param policy
	 */
	public void updatePolicyBalance(PolicyPojo policyPojo, Policy policy) {
		// 发生后可用
		if(!policy.getInsurcompId().equals(CommonUtils.INSURCOMP_ID_RENBAO)&& policyPojo.getFlag() == 1){
			throw new BizException(BizCode.RENEWAL_OF_INSURCOMP);
		}
		String afterNum = convertPolicyAfterNum(policyPojo, policy.getSurplusCarNoMoney());

		String afterTotalNum = convertPolicyTotalNum(policy.getPolicyTotalMoney(), policyPojo.getAccrual(),
				policyPojo.getDirectionOf());
		insertStream(policyPojo, policy, afterNum, policy.getSurplusCarNoMoney());
		policyPojo.setPolicyTotalMoney(policy.getPolicyTotalMoney());
		// 发生后总额 只有续保的时候改值会产生变化
		if (policyPojo.getFlag() == 1) {
			if (policy.getState().equals(CommonUtils.POLICY_STATE_INSURE_AMOUNT)
					|| policy.getState().equals(CommonUtils.POLICY_STATE_NORMAl)) {
				policyPojo.setState(CommonUtils.POLICY_STATE_NORMAl);
			}
			policyPojo.setPolicyTotalMoney(afterTotalNum);
		}
		policyPojo.setSurplusCarNoMoney(afterNum);
		policyPojo.setPolicyNo(policy.getPolicyNo());
		policyPojo.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = policyMapper.updatePolicyBalance(policyPojo);
		if (num < 1) {
			throw new BizException(BizCode.FAILD_ADD_BALANCE);
		}

	}

	/**
	 * 车次投保
	 * 
	 * @param policyPojo
	 */
	public void supdateCarPolicy(PolicyPojo policyPojo, Policy policy) {
		// 发生后可用车次
		String afterNum = convertPolicyAfterNum(policyPojo, policy.getSurplusCarNo());
		insertStream(policyPojo, policy, afterNum, policy.getSurplusCarNo());
		policyPojo.setSurplusCarNo(afterNum);
		policyPojo.setState(policy.getState());
		policyPojo.setPolicyNo(policy.getPolicyNo());
		policyPojo.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = policyMapper.updatePolicyBalance(policyPojo);
		policyPojo.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		if (num < 1) {
			throw new BizException(BizCode.FAILD_ADD_BALANCE);
		}

	}

	/**
	 * 添加额度 增加：总车次/总额度 增加 剩余额度/车次 增加
	 * 
	 * 增加：总车次/总额度 减少 剩余额度/车次 减少
	 * 
	 * @param policyPojo
	 * @return
	 */
	@Override
	public void addBalance(PolicyPojo policyPojo) {
		Policy policy = policyMapper.getByPolicyNo(policyPojo.getPolicyNo());
		policyPojo.setState(policy.getState());
		policyPojo.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		switch (policyPojo.getFlag()) {
		case 2:// 保额投保
			updatePolicyBalance(policyPojo, policy);
			break;
		case 3:// 车次投保
			supdateCarPolicy(policyPojo, policy);
			break;
		}
	}

	/**
	 * 删除保单
	 * 
	 * @param id
	 *            return
	 */
	@Override
	public boolean deletePolicy(String id) {
		this.num = detailsMapper.findDetailsByPolicy(id);
		if (num > 0) {
			throw new BizException(BizCode.EXISTS_DETAILS);
		}
		this.num = policyMapper.deletePolicy(id);
		if (num < 0) {
			throw new BizException(BizCode.FAILD_DELETE_EXCEPTION);
		}
		addLog(OperationType.DELETE.getOption(), BusinessUtils.POLICY);
		return true;
	}

	/**
	 * 到期提醒
	 * 
	 * @param page
	 * @param policy
	 * @return
	 */
	@Override
	public List<Policy> selectExpirePolicy() {
		return policyMapper.selectExpirePolicy();
	}

	/**
	 * 额度提醒
	 * 
	 * @param page
	 * @param policy
	 * @return
	 */
	@Override
	public List<Policy> selectLimitPolicy() {
		return policyMapper.selectLimitPolicy();
	}

	/**
	 * 导出
	 * 
	 * @param policy
	 */
	@Override
	public void exportPolicy(Page<Policy> page, Policy policy,HttpServletRequest request,HttpServletResponse response) {
		policyMapper.getByPage(page, policy);
		if (page.getResult() == null || page.getResult().size() == 0) {
			throw new BizException(BizCode.EXPORT_FILE_FAILD);
		}
		List<String> title = excelCache.getById(ExportExcel.POLICY_EXPORT_DETAIL_ID);
		List<List<String>> content = getExcelBody(page.getResult());

		String sheetName = BusinessUtils.POLICY;// 导出文件名
		try {
			ExcelUtils.exportData(sheetName, title, content, request, response);
		} catch (IOException e) {
			throw new BizException(BizCode.EXPORT_FAILD);
		}
	}

	/**
	 * 保单导出
	 */
	public List<List<String>> getExcelBody(List<Policy> list) {
		getAleradySendTime(list);
		List<List<String>> excelBody = new ArrayList<>();
		List<String> excelContent = null;
		for (Policy policy : list) {
			excelContent = new ArrayList<>();
			excelContent.add(policy.getFullName());
			excelContent.add(policy.getStateName());
			excelContent.add(policy.getPolicyNo());
			excelContent.add(policy.getCustName());
			excelContent.add(policy.getPolicyStartDate());
			excelContent.add(policy.getPolicyEndDate());
			excelContent.add(policy.getAmount());
			excelContent.add(policy.getTotalCarNo());
			excelContent.add(policy.getAlreadyStartTime());
			excelContent.add(policy.getSurplusCarNo());
			excelContent.add(policy.getPolicyTotalMoney());
			excelContent.add(policy.getAlreadySendMoney());
			excelContent.add(policy.getSurplusCarNoMoney());
			excelContent.add(policy.getUserName());
			excelContent.add(policy.getLastTdate());
			excelContent.add(policy.getPrimaryAgency());
			excelContent.add(policy.getSecondaryAgent());
			excelContent.add(policy.getPremiums());
			excelContent.add(policy.getPaymentAmount());
			excelBody.add(excelContent);
		}
		return excelBody;
	}

	/**
	 * 根据用id查询用户邮箱
	 * 
	 * @param userId
	 */
	public User findUserByUserId(String userId) {
		return policyMapper.findUserByUserId(userId);
	}

	/**
	 * 根据保单id获取保险员的信息 @
	 */
	public List<User> findUserByPolicyId(String policyId) {
		return policyMapper.findUserByPolicyId(policyId);
	}

	/**
	 * 查询用户的信息
	 */
	public List<User> findUserByRoleName() {
		return policyMapper.findUserByRoleName();
	}
}

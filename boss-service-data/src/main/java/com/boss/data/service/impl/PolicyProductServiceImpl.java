/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.PolicyProduct;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.PolicyProductMapper;
import com.boss.data.service.PolicyProductService;
import com.boss.data.service.PolicyService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;
@Service
@Transactional(rollbackFor=Exception.class)
public class PolicyProductServiceImpl extends BaseServiceImpl implements PolicyProductService {

	@Autowired
	private PolicyProductMapper policyProductMapper;
	
	@Autowired
	private  PolicyService policyService;
	/**
	 * 获取所有保险产品
	 */
	public List<PolicyProduct> getAll(){
		return policyProductMapper.getAll();
	}
	/**
	 * 根据保险公司id获取保险产品
	 * @param id
	 * @return
	 */
	public List<PolicyProduct> getByInsurcompId(String id){
		List<PolicyProduct> list = policyProductMapper.getByInsurcompId(id);
		for (PolicyProduct policyProduct : list) {
			policyProduct.setProductTypeName(getCodeNameByPidAndValue(CommonUtils.POLICY_TYPE,policyProduct.getProductTypeValue()));
		}
		return list;
	}
	/**
	 * 分页查询保险产品信息
	 */
	@Override
	public Page<PolicyProduct> findByPage(Page<PolicyProduct> page, PolicyProduct policyProduct) {
		policyProductMapper.findByPage(page, policyProduct);
		List<PolicyProduct> policyProductpageList = page.getResult();
		for(PolicyProduct policyProducts:policyProductpageList){
			String getName = getCodeNameByPidAndValue(CommonUtils.POLICY_TYPE, policyProducts.getProductTypeValue());
			policyProducts.setProductTypeName(getName);
		}
		return page;
	}
	/**
	 * 新增保险产品信息
	 */
	@Override
	public boolean insertPolicyProduct(PolicyProduct policyProduct) {
		policyProduct.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		this.num = policyProductMapper.insertPolicyProduct(policyProduct);
		if(num < 0){
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
		addLog(OperationType.ADD.getOption(), BusinessUtils.POLICYPRODUCT);
		return true;
	}
	/**
	 * 更新保险产品信息
	 */
	@Override
	public boolean updatePolicyProduct(PolicyProduct policyProduct) {
		policyProduct.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = policyProductMapper.updatePolicyProduct(policyProduct);
		if(num <= 0){
			throw new BizException(BizCode.FAILD_UPDATE_EXCEPTION);
		}
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.POLICYPRODUCT);
		return true;
	}
	/**
	 * 删除保险产品信息
	 */
	@Override
	public boolean deletePolicyProduct(String id) {
		if(policyService.getPolicyByProductId(id) > 0){
			throw new BizException(BizCode.EXISTS_POLICY);
		}
		this.num = policyProductMapper.deletePolicyProduct(id);
		if(num < 0){
			throw new BizException(BizCode.FAILD_DELETE_EXCEPTION);
		}
		addLog(OperationType.DELETE.getOption(), BusinessUtils.POLICYPRODUCT);
		return true;
	}
}

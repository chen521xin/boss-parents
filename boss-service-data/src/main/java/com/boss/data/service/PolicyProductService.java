/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.PolicyProduct;
import com.boss.db.feature.orm.mybatis.Page;

@Transactional
public interface PolicyProductService {

	/**
	 * 获取所有保险产品
	 */
	List<PolicyProduct> getAll();
	/**
	 * 根据保险公司id获取保险产品
	 * @param id
	 * @return
	 */
	List<PolicyProduct> getByInsurcompId(String id);
	/**
	 * 分页查询
	 * @param JPolicyProduct
	 * @return
	 */
	Page<PolicyProduct> findByPage(Page<PolicyProduct> page,PolicyProduct policyProduct);
	/**
	 * 新增保险产品
	 * @param jPolicyProduct
	 * @return
	 */
	boolean insertPolicyProduct(PolicyProduct policyProduct);
	/**
	 * 更新保险产品信息
	 * @param jPolicyProduct
	 * @return
	 */
	boolean updatePolicyProduct(PolicyProduct policyProduct);
	/**
	 * 删除保险产品
	 * @param jPolicyProduct
	 * @return
	 */
	boolean deletePolicyProduct(String id);
	
}

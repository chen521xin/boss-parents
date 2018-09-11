/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.mapper;

import java.util.List;

import com.boss.core.db.PolicyProduct;
import com.boss.db.feature.orm.mybatis.Page;

public interface PolicyProductMapper {
	/**
	 * 获取所有保险产品
	 * @return
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
	 * @param PolicyProduct
	 * @return
	 */
	List<PolicyProduct> findByPage(Page<PolicyProduct> page,PolicyProduct policyProduct);
	/**
	 * 新增保险产品
	 * @param jPolicyProduct
	 * @return
	 */
	int insertPolicyProduct(PolicyProduct policyProduct);
	/**
	 * 更新保险产品信息
	 * @param jPolicyProduct
	 * @return
	 */
	int updatePolicyProduct(PolicyProduct policyProduct);
	/**
	 * 删除保险产品
	 * @param jPolicyProduct
	 * @return
	 */
	int deletePolicyProduct(String id);
}

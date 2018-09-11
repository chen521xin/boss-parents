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

import com.boss.core.db.Policy;
import com.boss.core.db.User;
import com.boss.core.pojo.InsurcompAndPolicyType;
import com.boss.core.pojo.PolicyPojo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年3月12日下午7:54:43
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface PolicyMapper {

	/**
	 * 根据policyId查询保单
	 */
	Policy getPolicyByPolicyId(String policyId);
	/**
	 * 根据userId查询保单
	 * @param userId
	 * @return
	 */
	List<Policy> getPolicyByUserId(Page<InsurcompAndPolicyType> page,InsurcompAndPolicyType insurcompAndPolicyType);
	/**
	 * 分页查询保单
	 * @param page
	 * @param policy
	 * @return 
	 */
	List<Policy> getByPage(Page<Policy> page,Policy policy);
	/**
	 * 分页查询保单
	 * @param page
	 * @param policy
	 * @return 
	 */
	List<Policy> getByPage(Policy policy);
	/**
	 * 添加保单
	 * @param policy
	 * @return
	 */
	int addPolicy(Policy policy);
	/**
	 * 根据保单号查询保单
	 *@param policyNo
	 *@return
	 */
	Policy getByPolicyNo(String policyNo);
	/**
	 * 根据productId查询保单是否存在
	 */
	int getPolicyByProductId(String productId);
	/**
	 * 更新保单
	 * @param policy
	 * @return
	 */
	int updatePolicy(Policy policy); 
	/**
	 * 添加额度
	 * @param policyPojo
	 * @return
	 */
	int updatePolicyBalance(PolicyPojo policyPojo);
	/**
	 * 删除保单
	 * @param id
	 * @return
	 */
	int deletePolicy(String id);
	/**
	 * 到期提醒
	 * @param page
	 * @param policy
	 * @return 
	 */
	List<Policy> selectExpirePolicy();
	/**
	 * 额度提醒
	 * @param page
	 * @param policy
	 * @return 
	 */
	List<Policy> selectLimitPolicy();
	/**
	 * 根据角色查询之代理查询
	 * @param page
	 * @param policy
	 * @return
	 */
	List<Policy> findByRolePoxy(Page<Policy> page,Policy policy);
	/**
	 * 根据角色查询之保险员查询
	 * @param page
	 * @param policy
	 * @return 
	 */
	List<Policy> findByRoleInsurance(Page<Policy> page,Policy policy);
	/**
	 * 根据保单号查询保额、车次
	 */
	Policy findPolicyByPolicyNo(PolicyPojo policyPojo);
	/**
	 * 根据用id查询用户邮箱
	 * @param userId
	 */
	User findUserByUserId(String userId);
	/**
	 * 根据保单id获取保险员的信息
	 * @
	 */
	List<User> findUserByPolicyId(String policyId);
	/**
	 * 查询用户的信息
	 */
	List<User> findUserByRoleName();
	/**
	 * 保单到期更新保单状态
	 */
	void thePolicyExpiration();
}

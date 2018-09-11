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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boss.core.db.Policy;
import com.boss.core.db.User;
import com.boss.core.pojo.InsurcompAndPolicyType;
import com.boss.core.pojo.PolicyPojo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年3月12日下午7:46:49
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface PolicyService {
	/**
	 * 根据userId查询保单
	 * @param userId
	 * @retrun
	 */
	List<Policy> getPolicyByUserId(Page<InsurcompAndPolicyType> page,InsurcompAndPolicyType insurcompAndPolicyType);
	/**
	 * 分页查询保单
	 * @param page
	 * @param policy
	 * @return 
	 */
	Page<Policy> getByPage(Page<Policy> page,Policy policy);
	/**
	 * 添加保单
	 * @param policy
	 * @return
	 */
	boolean addPolicy(Policy policy);
	/**
	 * 根据保单号查询保单
	 *@param policyNo
	 *@return
	 */
	Policy getByPolicyNo(String policyNo);
	/**
	 * 根据保单号查询保单的保单的总额、剩余额度、剩余车次
	 */
	Policy getPolicyByPolicyId(String policyId);
	/**
	 * 根据productId查询保单是否存在
	 */
	int getPolicyByProductId(String productId);
	/**
	 * 更新保单
	 * @param policy
	 * @return
	 */
	boolean updatePolicy(Policy policy);
	/**
	 * 添加保额
	 * @param policyPojo
	 * @return
	 */
	void addBalance(PolicyPojo policyPojo);
	/**
	 * 删除保单
	 * @param id
	 * @return
	 */
	boolean deletePolicy(String id);
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
	 *导出保单
	 *@param 
	 */
	void exportPolicy(Page<Policy> page,Policy policy,HttpServletRequest request,HttpServletResponse response);
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
	 * 保额续保
	 * @param policyPojo
	 */
    void addPolicyBalance(PolicyPojo policyPojo);
}

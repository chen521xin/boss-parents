/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.mapper;

import java.util.List;

import com.boss.core.db.Policy;
import com.boss.core.db.Resource;
import com.boss.core.pojo.InsurcompAndPolicyType;

/**
 * @description
 * @data 2018年2月4日下午5:07:33
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface ResourceMapper {

	List<Resource> findResourceByRole(Resource resource);
	/**
	 * 查询所有不分页
	 * @return
	 */
	List<Resource> findAllResource();
	
	/**
	 * 根据角色code查询菜单
	 * @param roleCode
	 * @return
	 */
	List<Resource> findMenuByRole(String roleCode);
	
	List<Policy> getPolicyByUserId(InsurcompAndPolicyType policyType);
	
}

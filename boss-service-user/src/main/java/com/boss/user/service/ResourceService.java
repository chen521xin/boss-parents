/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service;

import java.util.List;

import com.boss.core.db.Resource;
import com.boss.core.db.ResourceInfo;

/**
 * @description
 * @data 2018年2月4日下午5:05:22
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface ResourceService {

	/**
	 * 根据角色id查询信息
	 * @param role
	 * @return
	 */
	List<Resource> findResourceByRole(Resource resource);
	/**
	 * 根据角色id查询信息
	 * @param role
	 * @return
	 */
	List<ResourceInfo> findMenuByRole(String roleCode);
	/**
	 * 查询所有不分页
	 * @return
	 */
	List<Resource> findAllResource();
}

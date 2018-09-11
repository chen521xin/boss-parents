/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.PermissionInfo;
import com.boss.user.mapper.PermissionMapper;
import com.boss.user.service.PermissionService;
import com.boss.utils.cons.CommonUtils;

/**
 * @description 
 * @data 2018年3月25日下午8:21:09
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper mapper;
	@Override
	public int addPermission(PermissionInfo permission) {
		permission.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		return mapper.addPermission(permission);
	}

	@Override
	public int deletePermissionInfo(String id) {
		return mapper.deletePermissionInfo(id);
	}

	@Override
	public List<String> selectPermissionIdByRoleId(String roleId) {
		return mapper.selectPermissionIdByRoleId(roleId);
	}


}



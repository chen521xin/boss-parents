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

import com.boss.core.db.Role;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.user.mapper.RoleMapper;
import com.boss.user.service.RoleService;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description
 * @data 2018年2月3日下午11:43:27
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImple extends BaseServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Override
	public Role findRoleByUsername(String username) {
		return roleMapper.findRoleByUsername(username);
	}

	@Override
	public void updateRoleById(Role role) {
		role.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		roleMapper.updateRoleById(role);
//		List<String> permissionId = permissionService.selectPermissionIdByRoleId(role.getId());
//		List<String> idList=new ArrayList<String>();
//		for(PermissionInfo permission:role.getPermission()){
//			if(permission.getId()==null){
//				permission.changeStatus(CommonUtils.METHOD_ADD, getUserName());
//				permissionService.addPermission(permission);
//			}else{
//				idList.add(permission.getId());
//			}
//		}
//		for(String id:permissionId){
//			if(!idList.contains(id)){
//				permissionService.deletePermissionInfo(id);
//			}
//		}
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.ROLE);
	}

	@Override
	public List<Role> findRoleListByPage(Page<Role> page, Role role) {
		return roleMapper.findRoleListByPage(page, role);
	}

	@Override
	public void delteRoleById(String id) {
		if(roleMapper.validateDeleteRole(id)>0){
			throw new BizException(BizCode.DELETE_CODE_FAILD);
		}
		Role role =new Role();
		role.setId(id);
		role.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		role.setRowstate(CommonUtils.ROWSTATE_DELETE);
		roleMapper.updateRoleById(role);
		addLog(OperationType.DELETE.getOption(), BusinessUtils.ROLE);
	}

	@Override
	public List<Role> findRoleListByPage() {
		return roleMapper.findRoleListByPage(null);
	}

	@Override
	public String selectRoleCodeById(String id) {
		return roleMapper.selectRoleCodeById(id);
	}

}

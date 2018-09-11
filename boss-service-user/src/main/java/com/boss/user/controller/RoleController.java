/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.Role;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.user.service.RoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年3月16日上午12:03:08
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * 根据用户名查找所属角色
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/{userName}")
	@ApiOperation(value = "根据用户名查找所属角色", httpMethod = "GET", response = Role.class, notes = "根据用户名查找所属角色")
	public String findRoleByUsername(
			@ApiParam(required = true, name = "username", value = "用户名") @PathVariable String username) {
		return ResultInfo.successToJsonString(roleService.findRoleByUsername(username));
	}

	/**
	 * 修改角色信息
	 * 
	 * @param role
	 * @return
	 */
	@PutMapping("/")
	@ApiOperation(value = "修改角色信息", httpMethod = "PUT", response = String.class, notes = "修改角色信息")
	public String updateRoleById(@ApiParam(required = true, name = "role", value = "角色信息") @RequestBody @Valid Role role,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		roleService.updateRoleById(role);
		return ResultInfo.successToJsonString("更新成功");
	}

	/**
	 * 查询角色分页
	 * 
	 * @param role
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "查询角色分页", httpMethod = "POST", response = Role.class, notes = "查询角色分页")
	public String findRoleListByPage(@ApiParam(required = true, name = "role", value = "角色信息") @RequestBody Role role) {
		Page<Role> page = new Page<Role>(role.getPageNo(), role.getPageSize());
		roleService.findRoleListByPage(page, role);
		return ResultPage.successToJsonString(page);
	}

	/**
	 * 根据id删除角色
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "根据id删除角色", httpMethod = "DELETE", response = String.class, notes = "根据id删除角色")
	public String deleteRoleBy(@ApiParam(required = true, name = "id", value = "角色id") @PathVariable String id) {
		roleService.delteRoleById(id);
		return ResultInfo.successToJsonString("删除成功");
	}
	
	/**
	 * 查询角色分页
	 * 
	 * @param role
	 * @return
	 */
	@GetMapping("/all")
	@ApiOperation(value = "查询角色分页", httpMethod = "GET", response = Role.class, notes = "查询角色分页")
	public String findRoleList() {
		List<Role> roleList=roleService.findRoleListByPage();
		return ResultInfo.successToJsonString(roleList);
	}
}

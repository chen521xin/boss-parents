/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.boss.core.db.Resource;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.user.service.ResourceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年2月4日下午5:04:52
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController{

	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 根据角色代码查找所有的接口
	 * @param roleCode
	 * @return
	 */
	@GetMapping("/{roleId}")
	@ApiOperation(value = "根据角色代码查找所有的接口", httpMethod = "GET", response = String.class, notes = "根据角色代码查找所有的接口")
	public ResultPage findResourceByRole(@ApiParam(required = true, name = "roleCode", value = "角色代码") @PathVariable String roleId){
		Resource resource=new Resource();
		resource.setRoleId(roleId);
		return ResultPage.success(resourceService.findResourceByRole(resource));
	}

	/**
	 * 菜单
	 * @param roleCode
	 * @return
	 */
	@GetMapping("/menu")
	@ApiOperation(value = "菜单", httpMethod = "GET", response = String.class, notes = "菜单")
	public String findMenuByRole(){	
		return JSONObject.toJSONString(ResultPage.success(resourceService.findMenuByRole(getUserRole())),SerializerFeature.DisableCircularReferenceDetect);
		
	}
	
	/**
	 * 将系统中存在的所有接口查询给
	 * @return
	 */
	@GetMapping("/findAll")
	@ApiOperation(value = "将系统中存在的所有接口查询给", httpMethod = "GET", response = String.class, notes = "将系统中存在的所有接口查询给")
	public String findAllResource(){
		Resource resource=new Resource();
		resource.setRoleId(getUserRole());
		return ResultInfo.successToJsonString(resourceService.findAllResource());
	}
}

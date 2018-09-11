/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.Policy;
import com.boss.core.pojo.InsurcompAndPolicyType;
import com.boss.core.pojo.PolicyPojo;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.service.PolicyService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年3月12日下午7:45:44
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("保单")
@RestController
@RequestMapping("/policy")
public class PolicyController{

	@Autowired
	private PolicyService policyService;
	/**
	 * 根据用户id查询保单
	 * @param userId
	 * @return
	 */
	@PostMapping("/findByUserId")
	@ApiOperation(value = "根据用户id查询保单", httpMethod = "POST", response = Policy.class, notes = "根据用户id查询保单")
	public String getById(@RequestBody InsurcompAndPolicyType insurcompAndPolicyType){
		Page<InsurcompAndPolicyType> page = new Page<>(insurcompAndPolicyType.getPageNo(),insurcompAndPolicyType.getPageSize());
		policyService.getPolicyByUserId(page,insurcompAndPolicyType);

		return ResultInfo.successToJsonString(page);
	}
	/**
	 * 分页查询保单信息
	 * @param policy
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页查询", httpMethod = "POST", response = Policy.class, notes = "分页查询")
	public String findByPage(@ApiParam(required = false, name = "policy", value = "保单查询入参")@RequestBody Policy policy){
		Page<Policy> page = new Page<>(policy.getPageNo(),policy.getPageSize());
		policyService.getByPage(page, policy);
		return ResultPage.successToJsonString(page);
	}
	/**
	 * 添加保单
	 * @param policy
	 * @param result
	 * @return
	 */
	@PostMapping("/")
	@ApiOperation(value = "添加保单", httpMethod = "POST", response = Policy.class, notes = "添加保单")
	public String addPolicy(@ApiParam(required = false, name = "policy", value = "新增保单入参")@RequestBody @Valid Policy policy,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		if(policy.getProductTypeValue().equals("1")){
			throw new BizException(BizCode.ERROR_TYPE);
		}
		policyService.addPolicy(policy);
		return ResultInfo.successToJsonString("添加成功");
	}
	/**
	 * 更新保单
	 * @param policy
	 * @param result
	 * @return
	 */
	@PutMapping("/")
	@ApiOperation(value = "更新保单", httpMethod = "PUT", response = Policy.class, notes = "更新保单")
	public String updatePolicy(@ApiParam(required = false, name = "policy", value = "更新保单入参")@RequestBody @Valid Policy policy,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		policyService.updatePolicy(policy) ;
		return ResultInfo.successToJsonString("更新成功");
	}
	/**
	 * 添加额度
	 * @param policyPojo
	 * @return
	 */
	@PutMapping("/addBalance")
	@ApiOperation(value = "续保额", httpMethod = "PUT", response = Policy.class, notes = "续保额")
	public String addBalance(@ApiParam(required = false, name = "policy", value = "保单续保")@RequestBody @Valid PolicyPojo policyPojo,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		policyService.addPolicyBalance(policyPojo);
		return ResultInfo.successToJsonString("添加成功");
	}
	/**
	 * 删除保单
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除保单", httpMethod = "POST", response = Policy.class, notes = "删除保单")
	public String deletePolicy(@ApiParam(required = false, name = "id", value = "删除保单入参")@PathVariable String id){
		policyService.deletePolicy(id);
		return ResultInfo.successToJsonString("删除成功");
	}
	/**
	 *导出exel
	 *@param name
	 *@return
	 */
	@PostMapping("/export")
	@ApiOperation(value = "导出", httpMethod = "POST", response = Policy.class, notes = "导出保单")
	public void exportExcl(@ApiParam(required = false, name = "policy", value = "导出保单")@RequestBody Policy policy,HttpServletRequest request,HttpServletResponse response){
		Page<Policy> page = new Page<>(policy.getPageNo(),policy.getPageSize());
		policyService.exportPolicy(page,policy,request,response);
	}
}

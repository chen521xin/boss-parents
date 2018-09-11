/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.controller;

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

import com.boss.core.db.PolicyProduct;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.data.service.PolicyProductService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api("保险产品管理")
@RestController
@RequestMapping("/policyProduct")
public class PolicyProductController {

	@Autowired
	private PolicyProductService policyProductService;
	/**
	 * 获取所有保险产品
	 * @return
	 */
	@GetMapping("/")
	@ApiOperation(value = "获取所有保险产品", httpMethod = "GET", response = PolicyProduct.class, notes = "获取所有保险产品")
	public String getAll(){
		List<PolicyProduct> policyProductList = policyProductService.getAll();
		return ResultInfo.successToJsonString(policyProductList);
	}
	/**
	 * 根据保险公司id获取保险产品
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "根据保险公司id获取保险产品", httpMethod = "GET", response = PolicyProduct.class, notes = "获取所有保险产品")
	public String getByInsurcompId(@ApiParam(required = false, name = "id", value = "根据保险公司id获取保险产品") @PathVariable String id){
		List<PolicyProduct> policyProductList = policyProductService.getByInsurcompId(id);
		return ResultInfo.successToJsonString(policyProductList);
	}
	/**
	 * 保险产品分页查询
	 * @param jPolicyProduct
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页查询", httpMethod = "POST", response = PolicyProduct.class, notes = "分页查询")
	public String findByPage(@ApiParam(required = false, name = "jPolicyProduct", value = "保险产品查询入参")@RequestBody PolicyProduct policyProduct){
		Page<PolicyProduct> page = new Page<>(policyProduct.getPageNo(),policyProduct.getPageSize());
		policyProductService.findByPage(page, policyProduct);
		return ResultPage.successToJsonString(page); 
	}  
	/**
	 * 新增保险产品
	 * @param jPolicyProduct
	 * @param result
	 * @return
	 */
	@PostMapping("/")
	@ApiOperation(value = "新增保险产品", httpMethod = "POST", response = PolicyProduct.class, notes = "新增保险产品")
	public String insertJPolicyProduct(@ApiParam(required = false, name = "jPolicyProduct", value = "新增保险产品入参") @RequestBody @Valid PolicyProduct policyProduct,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		policyProductService.insertPolicyProduct(policyProduct);
		return ResultInfo.successToJsonString("新增成功");
	}
	/**
	 * 更新保险产品
	 */
	@PutMapping("/")
	@ApiOperation(value = "更新保险产品", httpMethod = "PUT", response = PolicyProduct.class, notes = "更新保险产品")
	public String updateJPolicyProduct(@ApiParam(required = false, name = "jPolicyProduct", value = "更新保险产品入参") @RequestBody @Valid PolicyProduct policyProduct,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		policyProductService.updatePolicyProduct(policyProduct);
		return ResultInfo.successToJsonString("更新成功");
	}
	/**
	 * 删除保险产品
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除保险产品", httpMethod = "PUT", response = PolicyProduct.class, notes = "删除保险产品")
	public String deleteJPolicyProduct(@ApiParam(required = false, name = "id", value = "删除保险产品入参") @PathVariable String id){
		policyProductService.deletePolicyProduct(id);
		return ResultInfo.successToJsonString("删除成功");
	}
	
}

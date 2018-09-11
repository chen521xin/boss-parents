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

import com.boss.core.db.Insurcomp;
import com.boss.core.struct.ResultPage;
import com.boss.core.struct.ResultInfo;
import com.boss.data.service.InsurcompService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 保险公司管理
 * @author Administrator
 *
 */
@Api("保险公司")
@RestController
@RequestMapping("/insurcomp")
public class InsurcompController {

	@Autowired
	private InsurcompService insurcompService;
	/**
	 * 所有保险公司信息
	 * @return
	 */
	@ApiOperation(value = "所有保险公司信息", httpMethod = "GET", response = Insurcomp.class, notes = "所有保险公司信息")
	@GetMapping("/")
	public String findAll(){
		List<Insurcomp> insurcompList = insurcompService.queryAllInsurcomp();
		return ResultInfo.successToJsonString(insurcompList);
	}
	/**
	 * 根据id查询保险公司信息
	 * @return
	 */
	@ApiOperation(value = "根据id查询保险公司信息", httpMethod = "GET", response = Insurcomp.class, notes = "根据id查询保险公司信息")
	@GetMapping("/{id}")
	public String findById(@ApiParam(required = false, name = "id", value = "保险公司查询入参vo") @PathVariable String id){
		Insurcomp insurcomp = insurcompService.queryInsurcompById(id);
		return ResultInfo.successToJsonString(insurcomp);
	}
	/**
	 * 保险公司分页查询
	 * @param insurcomp
	 * @return
	 */
	@ApiOperation(value = "保险公司分页查询", httpMethod = "POST", response = Page.class, notes = "保险公司分页查询")
	@PostMapping("/list")
	public String queryByPage(@ApiParam(required = false, name = "insurcomp", value = "保险公司查询入参vo")@RequestBody Insurcomp insurcomp){
		Page<Insurcomp> page = new Page<>(insurcomp.getPageNo(),insurcomp.getPageSize());
		insurcompService.queryInsurcompByPage(page, insurcomp);
		return ResultPage.successToJsonString(page);
	}
	/**
	 * 新增保险公司
	 * @param insurcomp
	 * @param result
	 * @return
	 */
	@ApiOperation(value = "新增保险公司", httpMethod = "POST", response = Page.class, notes = "新增保险公司")
	@PostMapping("/")
	public String insertInsurcomp(@ApiParam(required = false, name = "Insurcomp", value = "新增保险公司入参vo")@RequestBody @Valid Insurcomp insurcomp,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		insurcompService.insertInsurcomp(insurcomp);
		return ResultInfo.successToJsonString("新增成功");
	}
	/**
	 * 更新保险公司信息
	 * @param insurcomp
	 * @param result
	 * @return
	 */
	@ApiOperation(value = "更新保险公司信息", httpMethod = "PUT", response = Page.class, notes = "更新保险公司信息")
	@PutMapping("/")
	public String updateInsurcomp(@ApiParam(required = false, name = "Insurcomp", value = "更新保险公司信息入参vo")@RequestBody @Valid Insurcomp insurcomp,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		insurcompService.updateInsurcomp(insurcomp);
		return ResultInfo.successToJsonString("更新成功");
	}
	/**
	 * 删除保险公司
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除保险公司信息", httpMethod = "DELETE", response = Page.class, notes = "删除保险公司信息")
	@DeleteMapping("/{id}")
	public String deleteInsurcomp(@ApiParam(required = false, name = "id", value = "删除保险公司信息入参vo")@PathVariable String id){
		insurcompService.deleteInsurcomp(id);
		return ResultInfo.successToJsonString("删除成功");
	}
	
}

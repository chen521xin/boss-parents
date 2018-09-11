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

import com.boss.core.db.ProductParam;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.data.service.ProductParamService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年3月3日下午11:11:22
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("产品参数管理")
@RestController
@RequestMapping("/productParam")
public class ProductParamController {

	@Autowired
	private ProductParamService productParamService;
	/**
	 * 根据id
	 * @param productId
	 * @return
	 */
	@GetMapping("/{productId}")
	@ApiOperation(value = "根据productId查询产品参数", httpMethod = "GET", response = ProductParam.class, notes = "根据productId查询产品参数")
	public String findByProductId(@ApiParam(required = false, name = "productId", value = "productId入参")@PathVariable String productId){
		List<ProductParam> paramValue2List = productParamService.findByProductId(productId);
		return ResultInfo.successToJsonString(paramValue2List);
	}
	/**
	 * 分页查询产品参数
	 * @param productParam
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页查询", httpMethod = "POST", response = ProductParam.class, notes = "分页查询")
	public String findByPage(@ApiParam(required = false, name = "productParam", value = "产品参数查询入参")@RequestBody ProductParam productParam){
		Page<ProductParam> page = new Page<>(productParam.getPageNo(),productParam.getPageSize());
		productParamService.findByPage(page, productParam);
		return ResultPage.successToJsonString(page);
	}
	/**
	 * 新增产品参数
	 * @param productParam
	 * @param result
	 * @return
	 */
	@PostMapping("/")
	@ApiOperation(value = "新增产品参数", httpMethod = "POST", response = ProductParam.class, notes = "新增产品参数")
	public String insertProductParam(@ApiParam(required = false, name = "productParam", value = "新增产品参数入参")@RequestBody @Valid ProductParam productParam,BindingResult result){
		if(result.hasErrors()){
			return ResultPage.getValidationMessage(result);
		}
		 productParamService.insertProductParam(productParam);
		return ResultInfo.successToJsonString("新增成功");
	}
	@PutMapping("/")
	@ApiOperation(value = "更新产品参数", httpMethod = "PUT", response = ProductParam.class, notes = "更新产品参数")
	public String updateProductParam(@ApiParam(required = false, name = "productParam", value = "更新产品参数入参")@RequestBody @Valid ProductParam productParam,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		productParamService.updateProductParam(productParam);
		return ResultInfo.successToJsonString("更新成功");
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除产品参数", httpMethod = "DELETE", response = ProductParam.class, notes = "删除产品参数")
	public String deleteProductParam(@ApiParam(required = false, name = "id", value = "删除产品参数入参") @PathVariable String id){
		productParamService.deleteProductParam(id);
		return ResultInfo.successToJsonString("删除成功");
	}
}

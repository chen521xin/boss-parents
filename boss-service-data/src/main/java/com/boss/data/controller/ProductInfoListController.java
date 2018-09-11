/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.controller;

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

import com.boss.core.db.ProductInfo;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.data.service.ProductInfoListService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description 
 * @data 2018年4月1日上午11:27:35
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/product")
public class ProductInfoListController extends BaseController{
	
	@Autowired
	private ProductInfoListService productService;
	/**
	 * 增加产品
	 * @param code
	 * @return 
	 */
	@PostMapping("/")
	@ApiOperation(value = "增加保险产品", httpMethod = "POST", response = ResultPage.class, notes = "增加保险产品")
	public String insertProduct(@ApiParam(required = true, name = "productInfo", value = "产品信息") @RequestBody @Valid ProductInfo productInfo
			,BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		productService.insertImage(productInfo);
		return ResultInfo.successToJsonString("添加成功") ;
	}
	
	/**
	 * 修改
	 * @param code
	 * @return 
	 */
	@PutMapping("/")
	@ApiOperation(value = "修改保险产品", httpMethod = "PUT", response = ResultPage.class, notes = "修改保险产品")
	public String updateProduct(@ApiParam(required = true, name = "productInfo", value = "产品信息") @RequestBody @Valid ProductInfo productInfo,BindingResult result){
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		productService.updateImage(productInfo);
		return ResultInfo.successToJsonString("修改成功") ;
	}
	/**
	 * 查询分页
	 * @param code
	 * @return 
	 */
	@PostMapping("/list")
	@ApiOperation(value = "查询保险产品分页", httpMethod = "POST", response = ProductInfo.class, notes = "查询保险产品分页")
	public String selectproductListByPage(@ApiParam(required = true, name = "productInfo", value = "产品信息") @RequestBody ProductInfo productInfo){
		Page<ProductInfo> page=new Page<>(productInfo.getPageNo(),productInfo.getPageSize());
		productService.selectProductListPyPage(page, productInfo);
		return ResultPage.successToJsonString(page) ;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除保险产品", httpMethod = "DELETE", response = String.class, notes = "删除保险产品")
	public String deleteProductList(@ApiParam(required = true, name = "ID", value = "产品id") @PathVariable String id){
		productService.deleteProduct(id);
		return ResultInfo.successToJsonString("删除成功");
	}

}

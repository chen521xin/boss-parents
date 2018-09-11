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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.Details;
import com.boss.core.db.DetailsQueryInfo;
import com.boss.core.db.DetailsUpdateInfo;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.data.service.DetailsService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年4月2日下午8:06:13
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("起运书明细管理")
@RestController
@RequestMapping("/details")
public class DetailsController {

	@Autowired
	private DetailsService detailsService;

	/**
	 * 根据id查询起运书明细信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "根据id查询起运书明细信息", httpMethod = "GET", response = Details.class, notes = "根据id查询起运书明细信息")
	private String fingById(
			@ApiParam(required = false, name = "Details", value = "根据id查询起运书明细信息") @PathVariable String id) {
		Details details = detailsService.findById(id);
		return ResultInfo.successToJsonString(details);
	}

	/**
	 * 起运书明细分页查询
	 * 
	 * @param Details
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "起运书明细分页查询", httpMethod = "POST", response = Details.class, notes = "起运书明细分页查询")
	public String findByPage(
			@ApiParam(required = false, name = "Details", value = "起运书明细分页查询入参") @RequestBody DetailsQueryInfo details) {
		Page<DetailsQueryInfo> page = new Page<>(details.getPageNo(), details.getPageSize());
		detailsService.findByPage(page, details);
		return ResultPage.successToJsonString(page);
	}

	/**
	 * 新增起运书明细
	 * 
	 * @param Details
	 * @return
	 */
	@PostMapping("/")
	@ApiOperation(value = "新增起运书明细", httpMethod = "POST", response = Details.class, notes = "新增起运书明细")
	public String insertDetails(
			@ApiParam(required = false, name = "Details", value = "新增起运书明细入参") @RequestBody @Valid Details details,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		detailsService.insertDetails(details);
		return ResultInfo.successToJsonString(details);
	}

	/**
	 * 更新起运书明细
	 * 
	 * @param Details
	 * @return
	 */
	@PutMapping("/")
	@ApiOperation(value = "更新起运书明细", httpMethod = "PUT", response = Details.class, notes = "更新起运书明细")
	public String updateDetails(
			@ApiParam(required = false, name = "Details", value = "更新起运书明细入参") @RequestBody @Valid DetailsUpdateInfo details,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		detailsService.updateDetails(details);
		return ResultInfo.successToJsonString("更新成功");
	}

	/**
	 * 逻辑删除起运书明细
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除起运书明细", httpMethod = "DELETE", response = Details.class, notes = "删除起运书明细")
	public String deleteDetails(
			@ApiParam(required = false, name = "Details", value = "删除起运书明细入参") @PathVariable String id) {
		detailsService.deleteDetails(id);
		return ResultInfo.successToJsonString("删除成功");
	}

	/**
	 * 导出起运书明细
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/export")
	@ApiOperation(value = "到处起运书明细", httpMethod = "DELETE", response = Details.class, notes = "到处起运书明细")
	public void exportDetails(@ApiParam(required = false, name = "Details", value = "起运书明细到处入参") @RequestBody DetailsQueryInfo details, HttpServletRequest request,
			HttpServletResponse response) {
		Page<DetailsQueryInfo> page = new Page<>(details.getPageNo(), details.getPageSize());
		detailsService.exportDetails(page, details,request,response);
	}
}

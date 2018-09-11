/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.BalanceLsInfo;
import com.boss.core.struct.ResultPage;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.ecargo.service.impl.BalanceLsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description 
 * @data 2018年4月21日下午3:14:28
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("流水")
@RestController
@RequestMapping("/balanceLs")
public class BalanceLsController {

	@Autowired
	private BalanceLsServiceImpl lsService;
	
	
	@ApiOperation(value = "账单", httpMethod = "POST", response = Page.class, notes = "账单")
	@PostMapping("/list")
	public String trancations(
			@ApiParam(required = false, name = "balanceInfo", value = "余额信息") @RequestBody BalanceLsInfo lsInfo) {
		if(lsInfo.getBalanceId()==null){
			throw new BizException(BizCode.DEFAULT_PARAM_NULL_EXCEPTION);
		}
		Page<BalanceLsInfo> page = new Page<>(lsInfo.getPageNo(), lsInfo.getPageSize());
		lsService.selectBalanceLs(page, lsInfo);
		return ResultPage.successToJsonString(page);
	}
	
	@ApiOperation(value = "账单导出", httpMethod = "POST", response = Page.class, notes = "账单导出")
	@PostMapping("/export")
	public void export(
			@ApiParam(required = false, name = "balanceInfo", value = "余额信息") @RequestBody BalanceLsInfo lsInfo, HttpServletRequest request, HttpServletResponse response) {
		if(lsInfo.getBalanceId()==null){
			throw new BizException(BizCode.DEFAULT_PARAM_NULL_EXCEPTION);
		}
		lsService.export(lsInfo,request,response);
	}
}

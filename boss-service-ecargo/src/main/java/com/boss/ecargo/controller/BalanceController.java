/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.BalanceInfo;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.ecargo.service.impl.BalanceServiceImpl;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description 
 * @data 2018年4月21日上午12:06:08
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("余额")
@RestController
@RequestMapping("/balance")
public class BalanceController extends BaseController{

	@Autowired
	private BalanceServiceImpl balanceService;


	@ApiOperation(value = "余额分页查询", httpMethod = "POST", response = Page.class, notes = "余额分页查询")
	@PostMapping("/list")
	public String getBalanceByPage(
			@ApiParam(required = false, name = "balanceInfo", value = "余额信息") @RequestBody BalanceInfo balanceInfo) {
		balanceInfo.setUserId(getUserId());
		Page<BalanceInfo> page = new Page<>(balanceInfo.getPageNo(), balanceInfo.getPageSize());
		balanceService.queryBalanceByPage(page, balanceInfo);
		return ResultPage.successToJsonString(page);
	}
	
	@ApiOperation(value = "充值", httpMethod = "POST", response = Page.class, notes = "余额分页查询")
	@PutMapping("/")
	public String updateBalance(
			@ApiParam(required = false, name = "balanceInfo", value = "余额信息") @RequestBody @Valid BalanceInfo balanceInfo,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		balanceInfo.setModule(BusinessUtils.BALANCE);
		balanceInfo.setUsername(getUserName());
		balanceInfo.setAccountNum(balanceInfo.getAccountNum()==null?CommonUtils.USER_ACCOUNT:balanceInfo.getAccountNum());
		balanceService.updateBalance(balanceInfo);

		return ResultInfo.successToJsonString("充值成功");
	}

}

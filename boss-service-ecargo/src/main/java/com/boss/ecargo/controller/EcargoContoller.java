/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.EcargoInfo;
import com.boss.core.pojo.EcargoPojo;
import com.boss.core.pojo.EcargoProjo;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.ecargo.service.impl.EcargoServiceImpl;
import com.boss.utils.DateFormatUtils;
import com.boss.utils.exceptions.IdentCardException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年5月11日下午2:03:55
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RequestMapping("/ecargo")
@RestController
public class EcargoContoller extends BaseController {

	@Autowired
	private EcargoServiceImpl ecargoService;

	@ApiOperation(value = "投保", httpMethod = "POST", response = Page.class, notes = "账单")
	@PostMapping("/")
	public String trancations(
			@ApiParam(required = false, name = "lsInfo", value = "投保") @RequestBody @Valid EcargoInfo ecargoInfo,
			BindingResult result) throws ClientProtocolException, IOException, URISyntaxException, IdentCardException, ParseException {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		convertParamIn(ecargoInfo);
		EcargoProjo ecargoProjo=ecargoService.insertEcargo(ecargoInfo);
		return ResultInfo.successToJsonString(ecargoProjo.getEcargoMsg());
	}

	@ApiOperation(value = "余额分页查询", httpMethod = "POST", response = Page.class, notes = "余额分页查询")
	@PostMapping("/list")
	public String getBalanceByPage(
			@ApiParam(required = false, name = "ecargoInfo", value = "查询字段") @RequestBody EcargoPojo ecargoInfo) {
		Page<EcargoPojo> page = new Page<>(ecargoInfo.getPageNo(), ecargoInfo.getPageSize());
		ecargoInfo.setUserId(getUserId());
		ecargoService.selectByPage(page, ecargoInfo);
		return ResultPage.successToJsonString(page);
	}

	@ApiOperation(value = "根据保单号和投保单号查询", httpMethod = "POST", response = Page.class, notes = "根据保单号和投保单号查询")
	@GetMapping("/{policyNo}")
	public String getBalanceByNo(
			@ApiParam(required = false, name = "policyNo", value = "ecargo保单号") @PathVariable String policyNo) {
		return ResultPage.successToJsonString(ecargoService.selectEcargoByNo(policyNo));
	}
	
	
	@ApiOperation(value = "根据投保号", httpMethod = "POST", response = Page.class, notes = "根据投保号")
	@GetMapping("/applyNo/{applyNo}")
	public String getBalanceByApplyNo(
			@ApiParam(required = false, name = "applyNo", value = "ecargo投保号") @PathVariable String policyNo) {
		return ResultPage.successToJsonString(ecargoService.selectEcargoApplyNo(policyNo));
	}
	
	public void convertParamIn(EcargoInfo ecargoInfo) {
		if (ecargoInfo.getStartTime().compareTo(DateFormatUtils.DateFormateToDay())<0) {
			ecargoInfo.setStartTime(DateFormatUtils.AfterTwentyMin());
		}
	}

}

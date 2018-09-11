/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.Jlog;
import com.boss.core.pojo.JlogPojo;
import com.boss.core.struct.ResultPage;
import com.boss.data.service.JlogService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


@Api("日志管理")
@RestController
@RequestMapping("/log")
public class JlogController extends BaseController{

	@Autowired
	private JlogService jLogService;
	
	@ApiOperation(value = "日志分页查询", httpMethod = "POST", response = Page.class, notes = "日志分页查询")
	@PostMapping("/list")
	public String findAllByPage(@ApiParam(required = false, name = "baseEntity", value = "日志vo")@RequestBody JlogPojo jLog){
		Page<Jlog> page = new Page<>(jLog.getPageNo(),jLog.getPageSize());
		convertParam(jLog);
		jLogService.findAllByPage(page,jLog);
		return ResultPage.successToJsonString(page);
	}
	
	@ApiOperation(value = "插入日志", httpMethod = "POST", response = Page.class, notes = "插入日志")
	@PostMapping("/")
	public ResultPage addLog(@ApiParam(required = false, name = "baseEntity", value = "插入日志入参")@RequestBody Jlog jLog){
		jLogService.addJlog(jLog);		
		return ResultPage.success("插入成功");
	}
	
	public void convertParam(JlogPojo jLog){
		jLog.setMessage(getCodeNameByPidAndValue(CommonUtils.OPERA_TYPE,jLog.getMessage()));
	}
}

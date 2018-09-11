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
import java.util.Map;

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

import com.boss.core.db.Code;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.data.service.CodeService;
import com.boss.db.feature.orm.mybatis.Page;

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

@Api("参数管理")
@RestController
@RequestMapping("/code")
public class CodeController {

	@Autowired
	private CodeService codeService;
	
	/**
	 * 根据Pid查询所有的子级,多个pid之间使用分号隔开
	 * @param code
	 * @return 
	 */
	@GetMapping("/{pid}")
	@ApiOperation(value = "根据Pid查询的参数", httpMethod = "POST", response = Code.class, notes = "根据Pid查询的参数")
	public String getByPid(@ApiParam(required = false, name = "code", value = "code入参vo")@PathVariable String pid){
		Map<String,List<Code>> codeMap = codeService.getByPid(pid);
		return ResultPage.successToJsonString(codeMap) ;
	}
	/**
	 * 根据Pid和value值查询单个子级
	 */
	@PostMapping("/getByPidAndValue")
	@ApiOperation(value = "根据Pid和value查询的参数", httpMethod = "POST", response = Code.class, notes = "根据Pid和value查询的参数")
	public String getByPidAndValue(@ApiParam(required = false, name = "code", value = "code入参vo")@RequestBody Code code){
		Code codes = codeService.getPidAndValue(code.getPid(), code.getValue());
		return ResultInfo.successToJsonString(codes);
	}
	/**
	 * 分页查询所有参数
	 * @param codePojo
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页查询", httpMethod = "POST", response = Code.class, notes = "分页查询")
	public String findByPage(@ApiParam(required = false, name = "CodePojo", value = "分页查询vo") @RequestBody Code code){	
		Page<Code> pageCode = new Page<>(code.getPageNo(),code.getPageSize());
		codeService.queryCode(pageCode,code);
		return ResultPage.successToJsonString(pageCode);
	}
	/**
	 * 新增参数
	 * @param codePojo
	 * @return
	 */
	@PostMapping("/")
	@ApiOperation(value = "新增参数", httpMethod = "POST", response = Code.class, notes = "新增参数")
	public String addCode(@ApiParam(required = false, name = "Code", value = "新增参数入参vo")@RequestBody @Valid Code code,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		codeService.addCode(code);
		return ResultInfo.successToJsonString("新增成功");
	}
	/**
	 * 更新参数
	 * @param codePojo
	 * @return
	 */
	@PutMapping("/")
	@ApiOperation(value = "更新参数", httpMethod = "PUT", response = Code.class, notes = "更新参数")
	public String updateCode(@ApiParam(required = false, name = "CodePojo", value = "新增参数入参vo") @RequestBody @Valid Code code,BindingResult result){
		if(result.hasErrors()){
			return ResultInfo.getValidationMessage(result);
		}
		 codeService.updateCode(code);

		return ResultInfo.successToJsonString("更新成功");
	}
	/**
	 * 删除参数；删除父级时，该父级下所有子级参数均被删除
	 * @param codePojo
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除参数", httpMethod = "DELETE", response = Code.class, notes = "删除参数")
	public String deleteCode(@ApiParam(required = false, name = "id", value = "删除参数入参vo") @PathVariable String id){
		codeService.deleteCode(id);
		return ResultInfo.successToJsonString("删除成功");
	}
}

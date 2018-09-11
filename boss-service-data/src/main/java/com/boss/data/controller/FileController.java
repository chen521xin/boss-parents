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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boss.core.struct.ResultInfo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.service.FileService;
import com.boss.db.feature.orm.mybatis.Page;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description 
 * @data 2018年4月1日下午12:10:37
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/productFile")
public class FileController extends BaseController{
	
	@Autowired
	private FileService fileService;
	/**
	 * 上传文件
	 * @param code
	 * @return 
	 */
	@PostMapping("/upload")
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ApiOperation(value = "上传文件", httpMethod = "POST", response = Page.class, notes = "上传文件")
	public  String uploadFile(@RequestParam("partFile") MultipartFile partFile,@ApiParam(required = false, name = "request", value = "HttpServletRequest") HttpServletRequest request){
		
		   if (partFile == null || partFile.getOriginalFilename() == null || partFile.getOriginalFilename().length() <= 0) {
			   throw new BizException(BizCode.PARAM_IS_ERROR);
	        } 
	            return ResultInfo.successToJsonString(fileService.uploadFile(partFile));
	    
	}
}

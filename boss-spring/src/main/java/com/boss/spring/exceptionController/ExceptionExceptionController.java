/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.spring.exceptionController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.boss.core.struct.ResultInfo;
import com.boss.core.value.BizException;

/**
 * @description
 * @data 2017年10月26日下午6:37:54
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@ControllerAdvice
public class ExceptionExceptionController {

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public ResultInfo bizException(BizException biz) {
		return ResultInfo.error(biz.getBizCode().toString());
	}
}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.value.BizCode;

/**
 * @description 通用错误处理器
 *              springboot
 * @data 2018年2月9日下午7:17:42
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ZuulExcepionController  extends AbstractErrorController{
private static final Logger logger=LoggerFactory.getLogger(ZuulExcepionController.class);
	@Value("${error.path:/error}")
	private String errorPath;
	

	
	@Value("${server.error.path:${error.path:/error}}")
	private String url;
	
	public ZuulExcepionController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@Override
	public String getErrorPath() {
		return null;
	}
	
	 @RequestMapping(value="$error.path:/error",produces="application/json;charset=UTF-8")
	public ResponseEntity<BizCode> error(HttpServletRequest request){
		HttpStatus status=getStatus(request);
		final Throwable exc=(Throwable) request.getAttribute("java.servlet.error.exception");
		logger.error(exc.getMessage(),exc);
		return ResponseEntity.status(status).body(BizCode.SYSTEM_IS_BUSY);
	}

}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.value;
/**
 * @description
 * @data 2017年10月26日下午6:27:13
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class BizCode {

	public static final BizCode DEFAULT_PARAM_NULL_EXCEPTION = new BizCode(10001,"入参不能为空");
	public BizCode(int code,String message){
		this.code=code;
		this.message=message;
	}
	private int code;
	private String message;
	
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return getMessage();
	}
	
}

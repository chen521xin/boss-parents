/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.struct;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @description
 * @data 2017年10月26日下午6:45:23
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7918804887612417358L;
	private String message;
	private boolean success = false;
	private Object resultObject;

	public static Result success() {
		return suc(true, "");
	}
	public Result(boolean success) {
		this.success=success;
	}
	public static Result suc(boolean success, String message) {
		return new Result(success, message);
	}
	
	public Result() {
	}

	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public static Result success(Object obj) {
		Result result = success();
		result.setResultObject(obj);
		return result;
	}

	public static Result error(Object obj) {
		Result result = new Result(false);
		result.setResultObject(obj);
		return result;
	}
	public static Result error(String message) {
		return new Result(false,message);
	}
	public static String successToJsonString(Object obj) {
		Result result=success(obj);
		return JSONObject.toJSONString(result);
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	@Override
	public String toString() {
		return "Result [message=" + message + ", success=" + success + ", resultObject=" + resultObject + "]";
	}

}

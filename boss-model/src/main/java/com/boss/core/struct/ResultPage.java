/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.struct;

import java.io.Serializable;

import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSONObject;

/**
 * @description
 * @data 2017年10月26日下午6:45:23
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class ResultPage implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success = false;
	private Object data;
	private Code code;
	private Integer status;

	public ResultPage(boolean succeed, String message, Integer status) {
		this.success = succeed;
		this.code = new Code(message);
		this.status = status;
	}

	public static ResultPage success() {
		return suc(true, "");
	}

	public ResultPage(boolean success) {
		this.success = success;
	}

	public  boolean isSuccessd(){
		return success;
	}
	public static ResultPage suc(boolean success, String message) {
		return new ResultPage(success, message);
	}

	public static String getValidationMessage(BindingResult bindingResult) {
		return errorToJsonString(bindingResult.getFieldErrors().get(0).getDefaultMessage());
	}

	public ResultPage() {
	}

	public ResultPage(boolean success, String message) {
		this.success = success;
		this.code = new Code(message);
	}

	public static ResultPage success(Object obj) {
		ResultPage result = success();
		result.setData(obj);
		return result;
	}

	public static ResultPage error(String message, Integer status) {
		return new ResultPage(false, message, status);
	}

	public static ResultPage error(Object obj) {
		ResultPage result = new ResultPage(false);
		result.setData(obj);
		return result;
	}

	public static ResultPage error(String message) {
		return new ResultPage(false, message);
	}

	public static String successToJsonString(Object obj) {
		ResultPage result = success(obj);
		return JSONObject.toJSONString(result);
	}

	public static String errorToJsonString(Object obj) {
		ResultPage result = error(obj);
		return JSONObject.toJSONString(result);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public static class Code implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1425729764755029441L;
		private String msg;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Code(String msg) {
			this.msg = msg;
		}
		public Code() {
		}
	}
}

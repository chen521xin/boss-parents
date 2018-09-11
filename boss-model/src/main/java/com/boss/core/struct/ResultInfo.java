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
 * @description 前端框架局限，需要把所有返回的数据放在result字段下
 * @data 2018年5月18日下午4:14:51
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class ResultInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7918804887612417358L;
	private boolean success = false;
	private Result data;
	private Code code;
	private Integer status;

	public ResultInfo(boolean succeed, String message, Integer status) {
		this.success = succeed;
		this.code = new Code(message);
		this.status = status;
	}

	public static ResultInfo success() {
		return suc(true, "");
	}

	public ResultInfo(boolean success) {
		this.success = success;
	}

	public boolean isSuccessd() {
		return success;
	}

	public static ResultInfo suc(boolean success, String message) {
		return new ResultInfo(success, message);
	}

	public static String getValidationMessage(BindingResult bindingResult) {
		return errorToJsonString(bindingResult.getFieldErrors().get(0).getDefaultMessage());
	}

	public ResultInfo() {
	}

	public ResultInfo(boolean success, String message) {
		this.success = success;
		this.code = new Code(message);
	}

	public static ResultInfo success(Object obj) {
		ResultInfo result = success();
		result.setData(new Result(obj));
		return result;
	}

	public static ResultInfo error(String message, Integer status) {
		return new ResultInfo(false, message, status);
	}

	public static ResultInfo error(Object obj) {
		ResultInfo result = new ResultInfo(false);
		result.setCode(new Code(obj.toString()));
		return result;
	}

	public static ResultInfo error(String message) {
		return new ResultInfo(false, message);
	}

	public static String successToJsonString(Object obj) {
		ResultInfo result = success(obj);
		return JSONObject.toJSONString(result);
	}

	public static String errorToJsonString(Object obj) {
		ResultInfo result = error(obj);
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

	public Result getData() {
		return data;
	}

	public void setData(Result data) {
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

	public static class Result implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1425729764755029441L;
		private Object result;

		public Object getResult() {
			return result;
		}

		public void setResult(Object result) {
			this.result = result;
		}

		public Result(Object result) {
			this.result = result;
		}

		public Result() {
		}
	}
}

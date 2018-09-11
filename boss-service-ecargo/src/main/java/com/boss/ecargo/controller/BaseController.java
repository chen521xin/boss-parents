/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.boss.ecargo.util.CodeLocalCache;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.CommonUtils;

/**
 * @description 
 * @data 2018年3月16日上午12:05:49
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class BaseController {

	@Autowired
	private CodeLocalCache localCache;
	@Autowired
	private HttpServletRequest request;
	/**
	 * 根据字典Id得到对应的value值
	 * @param codeId
	 * @return
	 */
	public String getCodeValueById(String codeId){
		return localCache.getById(codeId).getId();
	}
	
	/**
	 * 根据字典id得到对应的name值
	 * @param codeId
	 * @return
	 */
	public String getCodeNameById(String codeId){
		return localCache.getById(codeId).getName();
	}
	/**
	 * 获取用户名
	 * @return
	 */
	public String getUserName(){
		return StringUtil.convertString(request.getHeader("username"), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF);
	}
	/**
	 * 获取用户名中文名稱
	 * @return
	 */
	public String getFullName(){
		return StringUtil.convertString(request.getHeader("fullName"), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF);
	}
	/**
	 * 获取登录IP
	 * @return
	 */
	public String getIP(){
		return request.getHeader("remoteIp");
	}

	public String getTimeZone(){
		return "中国标准时间";
	}
	/**
	 * 当前登录用户的id
	 * @return
	 */
	public String getUserId(){
		return request.getHeader("userId");
	}
	
	/**
	 * 当前登录用户的角色代码
	 * @return
	 */
	public String getUserRole(){
		return request.getHeader("userRole");
	}
	/**
	 * 当前登录用户的角色代码
	 * @return
	 */
	public String getInsurcompId(){
		return request.getHeader("insurcompId");
	}
	
}



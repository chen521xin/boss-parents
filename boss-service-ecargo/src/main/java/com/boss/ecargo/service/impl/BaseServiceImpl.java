/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Jlog;
import com.boss.ecargo.service.JlogService;
import com.boss.ecargo.util.CodeLocalCache;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.CommonUtils;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl {

	@Autowired

	private CodeLocalCache localCache;
	
	@Autowired
	private JlogService jLogService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	public int num = 0;
	public void addLog(String option,String business){
		Jlog log=new Jlog();
		log.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		log.setBizType(business);
		log.setUserName(getUserName());
		log.setUserIp(getIP());
		log.setMessage(option);
		log.setInstanceId(applicationName);
		log.setServiceHost(null);
		log.setServiceName(applicationName);
		log.setUserId(getUserId());
		log.setTimeZone(getTimeZone());
		log.setFullName(getFullName());
		jLogService.insertJlog(log);
	}
	/**
	 * 获取用户名
	 * @return
	 */
	public String getUserName(){
		return StringUtil.convertString(request.getHeader("username"), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF);
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
	 * 获取用户名中文名稱
	 * @return
	 */
	public String getFullName(){
		return StringUtil.convertString(request.getHeader("fullName"), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF);
	}
	
	/**
	 * 一般险
	 * @return
	 */
	public String getCommonlyRate(){
		return request.getHeader("commonlyRate");
	}
	/**
	 * 综合险
	 * @return
	 */
	public String getSynthesizeRate(){
		return request.getHeader("synthesizeRate");
	}
	/**
	 * 根据字典Id得到对应的value值
	 * @param pid
	 * @param value
	 * @return
	 */
	public String getCodeValueByPidAndValue(String pid,String value){
		return localCache.getByPidAndValue(pid,value).getValue();
	}
	/**
	 * 根据字典Id得到对应的value值
	 * @param pid
	 * @param value
	 * @return
	 */
	public String getCodeNameByPidAndValue(String pid,String value){
		return localCache.getByPidAndValue(pid,value).getName();
	}
	
	/**
	 * 获取运输协议号
	 * @param 
	 * @param
	 * @return 
	 */
	public String getTotalDealNo(String subPolicNo,String nowDate){
		SimpleDateFormat sdf = new SimpleDateFormat(nowDate);
		return subPolicNo.substring(subPolicNo.length()-4)+sdf.format(new Date());
	}
}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.boss.core.db.Code;
import com.boss.core.db.Jlog;
import com.boss.core.db.Policy;
import com.boss.data.service.JlogService;
import com.boss.data.util.CodeLocalCache;
import com.boss.data.util.ExcelCache;
import com.boss.utils.DateFormatUtils;
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
	public HttpServletRequest request;
	
	@Autowired
	public HttpServletResponse response;
	
	@Autowired
	public ExcelCache excelCache;
	
	/**
	 * 刷新缓存
	 */
	public void refreshCache(){
		localCache.resetCacheJob();
	}
	
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
	 * 获取用户名中文名稱
	 * @return
	 */
	public String getFullName(){
		return StringUtil.convertString(request.getHeader("fullName"), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF);
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
	 * 根据pid获取所有子集
	 * @param pid
	 * @return
	 */
	public List<Code> getCodeList(String pid){
		List<Code> codeList = localCache.getByPid(pid);
		return codeList;
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
	/**
	 *获取用户角色
	 */
	public String getUserRole(){
		return request.getHeader("userRole");
	}
	/**
	 * 获取保险公司id
	 */
	public String getInsurcompId(){
		return request.getHeader("insurcompId");
	}
	/**
	 * 保单获取已发车次
	 * @param list
	 * @return
	 */
	public List<Policy> getAleradySendTime(List<Policy> list){
		for(Policy policy:list){
			if(!StringUtils.isEmpty(policy.getTotalCarNo()) && !StringUtils.isEmpty(policy.getSurplusCarNo())){
				policy.setAlreadyStartTime(new BigDecimal(policy.getTotalCarNo()).subtract(new BigDecimal(policy.getSurplusCarNo())).toString());
			}
			if(!StringUtils.isEmpty(policy.getPolicyTotalMoney()) && !StringUtils.isEmpty(policy.getSurplusCarNoMoney())){
				policy.setAlreadySendMoney(new BigDecimal(policy.getPolicyTotalMoney()).subtract(new BigDecimal(policy.getSurplusCarNoMoney())).toString());
			}
			if(StringUtils.isNotBlank(policy.getRemindNormExpireDay())){
				policy.setRemindNormExpireDate(DateFormatUtils.beforeDateNum(policy.getPolicyEndDate(),Integer.parseInt(policy.getRemindNormExpireDay())));
			}
			policy.setProductTypeName(getCodeNameByPidAndValue(CommonUtils.PRODUCT_TYPE_VALUE, policy.getProductTypeValue()));
			policy.setStateName(getCodeNameByPidAndValue(CommonUtils.POLICY_STATUS_TYPE, policy.getState()));
	
		} 
		return list;
	}
	/**
	 * 精确获取两个值差
	 * @param String
	 * @param String
	 * @return
	 */
	public double getDifferenceValue(String first,String second){
		return new BigDecimal(first).subtract(new BigDecimal(second)).doubleValue();
	}
	/**
	 * 精确获取两个值和
	 * @param String
	 * @param String
	 * @return
	 */
	public String getSumValue(String first,String second){
		return String.valueOf(new BigDecimal(first).add(new BigDecimal(second)));
	}
}

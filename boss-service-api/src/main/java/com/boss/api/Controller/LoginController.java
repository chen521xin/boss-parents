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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boss.api.client.LogServiceClient;
import com.boss.api.client.UserServiceClient;
import com.boss.core.db.Jlog;
import com.boss.core.db.User;
import com.boss.core.struct.ResultPage;
import com.boss.utils.RequestIpUtils;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description 登入 登出Controller
 * @data 2018年2月9日下午6:36:57
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Controller
public class LoginController extends BaseController{
	private final static Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LogServiceClient logClient;
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	HttpServletRequest request;
	
	@Value("${spring.application.name}")
	private String applicationName;
	@RequestMapping(value="/login")
	public String login( @RequestBody User user){
		System.out.println("===========登录接口");
		return "";
	}
	
	@RequestMapping(value="/logouts")
	@PreAuthorize("authenticated")
	public String logout(HttpServletRequest request){
		User user=getUser();
		ResultPage logResult  = insertLog(user);
		if(logResult.isSuccessd()){
			logger.info(String.format("user: %s logout insert log is successful", user.getUsername()));
		}
		
		user.setStatus(CommonUtils.USER_STATUS_LOGOUT);
		//修改用户为登出状态
		userServiceClient.updateStatus(user);
		logger.info(String.format("user: %s LoginOut Success!", user.getUsername()));
		return "forward:/logout";
	}

	public ResultPage insertLog(User user) {
		Jlog log = new Jlog();
		log.setBizType(log.getBizType());
		log.setUserName(user.getUsername());
		log.setUserIp(RequestIpUtils.getIPAddr(request));
		log.setMessage(log.getMessage());
		log.setInstanceId(applicationName);
		log.setServiceHost(null);
		log.setServiceName(applicationName);
		log.setUserId(user.getId());
		log.setFullName(StringUtil.convertString(user.getFullName(), CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
		log.setBizType(StringUtil.convertString(BusinessUtils.LOGOUT, CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
		log.setMessage(
				StringUtil.convertString(OperationType.LOGOUT.getOption(), CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
		return logClient.creatUserLog(log);
	}
	
	
}

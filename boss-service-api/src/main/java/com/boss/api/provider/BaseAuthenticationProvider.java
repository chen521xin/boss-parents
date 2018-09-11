/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.provider;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.boss.api.client.LogServiceClient;
import com.boss.api.client.UserServiceClient;
import com.boss.core.db.Jlog;
import com.boss.core.db.User;
import com.boss.core.pojo.UserContextPojo;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.utils.RequestIpUtils;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description 验证逻辑实现
 * @data 2018年2月3日下午12:32:18
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class BaseAuthenticationProvider {
	private final static Logger logger = LoggerFactory.getLogger(BaseAuthenticationProvider.class);
	@Autowired
	private UserServiceClient userServiceClient;

	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private LogServiceClient logClient;

	@Value("${jwt.token.expiration}")
	private Integer expiration;

	@Value("${jwt.token.secret}")
	private String secert;

	@Value("${redis.expire.time}")
	private long redisExpire;

	@SuppressWarnings("unchecked")
	public Authentication authentica(User user, String bizType) {
		ResultPage result = userServiceClient.validateUser(user);
		if (!result.isSuccess()) {
			throw new BadCredentialsException(result.getCode().getMsg());
		}
		HashMap<String, Object> map = (HashMap<String, Object>) result.getData();
		HashMap<String, String> role = (HashMap<String, String>) map.get("role");
		String roleCode = role.get("roleCode");
		UserContextPojo userContext = new UserContextPojo();
		userContext.setExpiration(this.expiration);
		userContext.setUserRole(roleCode);
		userContext.setId(map.get("id").toString());
		userContext.setFullName(map.get("fullName").toString());
		userContext.setUsername(map.get("username").toString());
		userContext.setIsSendMail(map.get("isSendMail").toString());
		userContext.setSecert(this.secert);
		userContext.setAgentId(roleCode.equals(CommonUtils.ROLE_PROXY) ? String.valueOf(map.get("agentLevel")) : "");
		userContext.setParentId(roleCode.equals(CommonUtils.ROLE_PROXY) ? String.valueOf(map.get("parentId")) : "");
		userContext.setInsurcompId(
				roleCode.equals(CommonUtils.ROLE_INSURANCE_MAN) ? String.valueOf(map.get("insurcompId")) : "");

		logger.info(String.format("user: %s Login Success!", userContext.getUsername()));
		ResultPage logResult = insertLog(userContext);
		if (logResult.isSuccessd()) {
			logger.info(String.format("user: %s login insert log is successful", userContext.getUsername()));
		}

		// 将用户登录状态改为已登录
		if (map.get("status") != null && !CommonUtils.USER_STATUS_LOGIN.equals(map.get("status"))) {
			User updateUser = new User();
			updateUser.setId(userContext.getId());
			updateUser.setStatus(CommonUtils.USER_STATUS_LOGIN);
			ResultInfo update = userServiceClient.updateStatus(updateUser);
			if (update.isSuccessd()) {
				logger.info(String.format("user: %s update user status successful", userContext.getUsername()));
			}
		} else {
			logger.info(String.format("user: %s logined", userContext.getUsername()));
		}

		return new UsernamePasswordAuthenticationToken(JSONObject.toJSONString(userContext), user.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(roleCode));
	}

	public ResultPage insertLog(UserContextPojo user) {
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
		log.setBizType(StringUtil.convertString(BusinessUtils.LOGIN, CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
		log.setMessage(
				StringUtil.convertString(OperationType.LOGIN.getOption(), CommonUtils.CODE_UTF, CommonUtils.CODE_ISO));
		return logClient.creatUserLog(log);
	}
}

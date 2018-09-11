/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.boss.api.client.ResourceServiceClient;
import com.boss.api.client.UserServiceClient;
import com.boss.api.token.JwtAuthenticationToken;
import com.boss.api.utils.JwtTokenUtils;
import com.boss.core.db.ResourceInfo;
import com.boss.core.db.Role;
import com.boss.core.db.User;
import com.boss.core.struct.ResultPage;
import com.boss.utils.cons.CommonUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * @description
 * @data 2018年2月5日下午6:44:36
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);
	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private ResourceServiceClient resourceClient;

	@Value("${jwt.token.expiration}")
	private Integer expiration;

	@Value("${jwt.token.secret}")
	private String secert;

	@Value("${redis.expire.time}")
	private int redisExpire;

	@Value("${auth.interface.control}")
	private Boolean authControl;

	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		JwtTokenUtils.secret = this.secert;
		JwtTokenUtils.expiration = this.expiration;
		// token已经生效或者token为空
		if (!StringUtils.isEmpty(token) && JwtTokenUtils.isTokenExpired(token)) {
			User user = userServiceClient.getUserbyUsername(JwtTokenUtils.getUsernameFromExpiredToken(token));
			if (user.getStatus().equals(CommonUtils.USER_STATUS_LOGIN)) {
				user.setStatus(CommonUtils.USER_STATUS_LOGOUT);
				userServiceClient.updateStatus(user);
				// 加入log
			} else {
				logger.info(String.format("user: %s already logout", user.getUsername()));
			}
		}
		String userName = JwtTokenUtils.getUsernameFormToken(token);
		User user = userServiceClient.getUserbyUsername(userName);
		if (CommonUtils.USER_STATUS_LOGOUT.equals(user.getStatus())) {
			logger.info(String.format("user:%s already logout ,Please login", user.getUsername()));
			throw new BadCredentialsException("用户已登出");
		}
		Jws<Claims> claimsJws = JwtTokenUtils.validateToken(token);
		String subject = claimsJws.getBody().getSubject();
		List<String> scopes = claimsJws.getBody().get("scops", List.class);
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (scopes != null) {
			for (String s : scopes) {
				authorities.add(new SimpleGrantedAuthority(s));
			}
		}

		if (authControl) {
			validateUrl(user);
		}
		authentication = new UsernamePasswordAuthenticationToken(subject, null, authorities);
		if (JwtTokenUtils.canTokenRefershed(token)) {
			response.setHeader("Authorization",
					String.format("%s %s", JwtTokenUtils.TOKEN_PERFIX, JwtTokenUtils.refreshToken(token)));
		}
		return authentication;
	}

	@SuppressWarnings("unchecked")
	private void validateUrl(User user) {
			Role role=null;
				role = user.getRole();
			ResultPage resourceResult = resourceClient.findResourceByRole(role.getId());
			List<ResourceInfo> resourceList = (List<ResourceInfo>) resourceResult.getData();
		String currentUri = request.getRequestURI();
		Boolean hashUri = false;
		logger.info(String.format("========================= requestUrl:%s ; method: %s", currentUri,request.getMethod()));
		for(int i=0;i<resourceList.size();i++){
			Object ss=resourceList.get(i);
			HashMap<Object,Object> map=(HashMap<Object, Object>) ss;
			if (request.getMethod().equals(map.get("code").toString()) && validateUrl(currentUri, replaceRestUrl(map.get("url").toString()))) {
				hashUri = true;
				break;
			}
		}
		if (!hashUri) {
			logger.info(String.format("========================= requestUrl:%s ; message: %s", currentUri,"无权访问该URL"));
			throw new BadCredentialsException(String.format("user:%s 无权访问该URL", user.getUsername()));
		}

	}

	public static boolean validateUrl(String requestUrl, String resourceUrl) {
		Pattern p = Pattern.compile(resourceUrl);
		Matcher m = p.matcher(requestUrl);
		return m.find();
	}

	public static String LEFT_BRACE="{";
	public static String RIGHT_BRACE="}";
	
	public static String replaceRestUrl(String url){
		int begin=url.lastIndexOf(LEFT_BRACE);
		int end =url.lastIndexOf(RIGHT_BRACE);
		if(begin<0&&end<0){
			return url;
		}
		String substr=url.substring(begin, end+1);
		String result=url.replace(substr, "\\w*");
		
		if(result.lastIndexOf(LEFT_BRACE)>0){
			result=replaceRestUrl(result);
		}
		return result;		
	}
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

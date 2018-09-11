/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.boss.core.pojo.UserContextPojo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @description
 * @data 2018年2月1日下午7:30:00
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component
public class JwtTokenUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

	public static final String CLAIM_KEY_USERNAME = "sub";
	public static final String CLAIM_KEY_CREATED = "created";
	public static final String TOKEN_PERFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";

	/**
	 * 生成Token所用的秘钥
	 */
	public static String secret;
	/**
	 * token有效期，单位为妙
	 */
	public static int expiration;

	public static Date generateExpireDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * 生成token
	 * 
	 * @param 所需claims
	 * @return
	 */
	public static String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpireDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * 生成token
	 * 
	 * @param username 用户名
	 * @param authorities 用户的权限信息
	 * @return token自妇产
	 */
	public static String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, username);
		claims.put(CLAIM_KEY_CREATED, new Date());
		List<String> authStrs = new ArrayList<>();
		for (GrantedAuthority authority : authorities) {
			authStrs.add(authority.toString());
		}
		claims.put("scopes", authStrs);
		return generateToken(claims);
	}
	
	/**
	 * 获取token的claims记录信息
	 * @param token
	 * @return
	 */
	public static Claims getClaimsFromToken(String token){
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	/**
	 * 判断token是否过期
	 * @param token
	 * @return
	 */
	public static Boolean isTokenExpired(String token){
		final Date expiration = getExpireDateFormToken(token);
		return expiration==null || expiration.before(new Date());
	}
	/**
	 * 验证token是否可刷新
	 * @param token
	 * @return
	 */
	public static Boolean canTokenRefershed(String token){
		return !isTokenExpired(token);
	}
	/**
	 * token刷新
	 * @param token
	 * @return
	 */
	public static String refreshToken(String token){
		final Claims claims = getClaimsFromToken(token);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
	/**
	 * token刷新
	 * @param token
	 * @return
	 */
	public static String getUsernameFromExpiredToken(String token){
		String username;
		try {
			username = getClaimsFromToken(token).getSubject();
		} catch (ExpiredJwtException ex) {
			String context=(String) ex.getClaims().get(CLAIM_KEY_USERNAME);
			UserContextPojo userContextPojo=JSONObject.parseObject(context,UserContextPojo.class);
			username=userContextPojo.getUsername();
		}catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
			logger.info(String.format("Invalid JWT token，token：%s，", token));
			throw new BadCredentialsException("Token失效，请重新登录",ex);
		}
		return username;
	}
	
	public static Jws<Claims> validateToken(String token){
		try{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		}catch(UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex){
			logger.info(String.format("Invalid JWT token,token: %s", token));
			throw new BadCredentialsException("Token失效，请重新登录，",ex);
		}catch(ExpiredJwtException ex){
			logger.info(String.format("expire JWT token,token: %s", token));
			throw new BadCredentialsException("Token失效，请重新登录，",ex);
		}
	}

	
	/**
	 * token刷新
	 * @param token
	 * @return
	 */
	public static String getUsernameFormToken(String token){
		String username;
		try {
			username = getClaimsFromToken(token).getSubject();
			UserContextPojo userContextPojo=JSONObject.parseObject(username,UserContextPojo.class);
			username = userContextPojo.getUsername();
		} catch (Exception ex) {
			logger.info(String.format("Can not get username from token: %s", token));
			throw new BadCredentialsException("Token失效，请重新登录，",ex);
		}
		return username;
	}
	
	/**
	 *获取失效时间
	 * @param token
	 * @return
	 */
	public static Date getExpireDateFormToken(String token){
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		}catch(UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex){
			logger.info(String.format("Invalid JWT token,token: %s", token));
			throw new BadCredentialsException("Token失效，请重新登录，",ex);
		}catch(ExpiredJwtException ex){
			logger.info(String.format("expire JWT token,token: %s", token));
			throw new BadCredentialsException("Token失效，请重新登录，",ex);
		}
		return expiration;
	}
	
	
	/**
	 * 获取创建时间
	 * @param token
	 * @return
	 */
	public static Date getCreateDateFormToken(String token){
		Date create;
		try {
			final Claims claims = getClaimsFromToken(token);
			create = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		}catch(Exception ex){
			logger.info(String.format("Can not get CreateDate from token: %s", token));
			throw new BadCredentialsException("Token失效，请重新登录，",ex);
		}
		return create;
	}
	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		JwtTokenUtils.secret = secret;
	}
	
}

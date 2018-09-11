/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.finalclases;

public class AppContents {
	
	/**开启识别验证码,bztype=ECARGO_NEED_CHECK_IMG*/
	public static final String ECARGO_NEED_CHECK_IMG_YES="YES";
	/**关闭识别验证码,bztype=ECARGO_NEED_CHECK_IMG*/
	public static final String ECARGO_NEED_CHECK_IMG_NO="NO";
	
	
	
	
	/**第三方验证码识别帐号code，bztype=CJY*/
	public static final String CJY_USER="user";
	/**第三方验证码识别密码code，bztype=CJY*/
	public static final String CJY_PASS="pass";
	/**第三方验证码识别，验证码类型code，bztype=CJY*/
	public static final String CJY_CODE_TYPE="codetype";
	/**第三方验证码识别，软件编号code，bztype=CJY*/
	public static final String CJY_SOFT_ID="softid";
	/**第三方验证码识别，URI地址code，bztype=CJY*/
	public static final String CJY_CURI="curi";
	
	
	
	
	/**eargo用户名code,bztype=ECARGO_LOGIN*/
	public static final String ECARGO_LOGIN_USER="eargoUser";
	/**eargo用密码code,bztype=ECARGO_LOGIN*/
	public static final String ECARGO_LOGIN_PWD="ecargoPwd";
	/**eargo用图片验证码地址URI,bztype=ECARGO_LOGIN*/
	public static final String ECARGO_LOGIN_URI="loginUri";
	/**eargo用图片验证码地址URI,bztype=ECARGO_LOGIN*/
	public static final String ECARGO_LOGIN_IMG_CODE_URI="imgCodeUri";
	/**开启/关闭识别验证码,bztype=ECARGO_LOGIN*/
	public static final String ECARGO_LOGIN_NEED_VALIDE_IMG="needValidImg";
	/**登录请求的字符集*/
	public static final String ECARGO_LOGIN_CAHARSET="loginRequstCharSet";
	
	/**前端登录时的参数，后期不用保存COOKIE*/
	public static final String ECARGO_LOGIN_SSID="Ssid";
	
	
	/**sid，bztype=ECARGO_COKIE*/
	public static final String ECARGO_COOKIE_SID="sid";
	/**epicc_tid，bztype=ECARGO_COKIE*/
	public static final String ECARGO_COOKIE_EPICC_TID="epicc_tid";
	/**gzentJU90u，bztype=ECARGO_COKIE*/
	public static final String ECARGO_COOKIE_GZENTJU90U="gzentJU90u";
	/**FrnVCj0TSG，bztype=ECARGO_COKIE*/
	public static final String ECARGO_COOKIE_FRNVCJ0TSG="FrnVCj0TSG";
	/**sessionid，bztype=ECARGO_COKIE*/
	public static final String ECARGO_COOKIE_SESSIONID="JSESSIONID";
	
	
	/**ecargo官网的session过期时间 ，也是系统保活时间，bztype=ECARGO_SESSION_SAVE*/
	public static final String ECARGO_SESSION_SAVE_TIMEOUT="timeOut";
	/**ecargo官网的session保活的定时刷新页面的URI地址*/
	public static final String ECARGO_SESSION_SAVE_ACTIVE_URI="activeURI";
	
	/**ecargo投保地址，bztype=ECARGO_INSURE*/
	public static final String ECARGO_INSURE_SAVE_URI="saveInsureURI";
	/**ecargo投保字符集，bztype=ECARGO_INSURE*/
	public static final String ECARGO_INSURE_CHARSET="insureCharSet";
	/**ecargo投保页面初始化URI，bztype=ECARGO_INSURE*/
	public static final String ECARGO_INSURE_INIT_URI="initURI";
	
	/**基本险，类型*/
	public static final String ECARGO_INSURE_TYPE_BASE="2";
	/**综合险，类型*/
	public static final String ECARGO_INSURE_TYPE_MULTIPLE="1";
}

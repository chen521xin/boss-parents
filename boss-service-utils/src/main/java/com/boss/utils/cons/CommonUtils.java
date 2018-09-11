/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.cons;

/**
 * @description
 * @data 2018年2月3日下午2:48:56
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface CommonUtils {

	/**
	 * 用户状态:冻结
	 */
	String USER_STATUS_FREEZE = "2";
	/**
	 * 用户状态:锁定
	 */
	String USER_STATUS_LOCK = "3";
	/**
	 * 用户状态:删除
	 */
	String USER_STATUS_DELETE = "0";
	/**
	 * 用户状态:正常
	 */
	String USER_STATUS_NORMAL = "1";
	/**
	 * 用户状态:登录
	 */
	String USER_STATUS_LOGIN = "4";
	/**
	 * 用户状态:登出
	 */
	String USER_STATUS_LOGOUT = "5";

	/**
	 * 新增 用于处理数据库表字段值
	 */
	String METHOD_ADD = "add";
	/**
	 * 修改 用于处理数据库表字段值
	 */
	String METHOD_UPDATE = "update";

	String DATE_FORMATE_YYYY_MM_DD = "yyyy-MM-dd HH:MM:ss";
	
	String DATE_FORMATE_YYYY_MM_DD_STRING = "yyyy-MM-dd";

	String DATE_FORMATE_TOTAL_DEAL_NO = "yyyyMMddHHMMSS";// 拼接运输协议号

	String DATE_FORMATE_YMD = "yyyyMMdd";
	String[] SKIP_URL = { "/login", "/api/**", "/info", "/menu" };
	String[] IMAGE = { "gif", "png", "jpg" };
	/**
	 * 角色：管理员
	 */
	String ROLE_ADMIN = "ROLE_ADMIN";
	/**
	 * 角色：代理
	 */
	String ROLE_PROXY = "ROLE_PROXY";
	/**
	 * 角色：用户
	 */
	String ROLE_USER = "ROLE_USER";
	/**
	 * 角色：保险员
	 */
	String ROLE_INSURANCE_MAN = "ROLE_INSURANCE_MAN";
	/**
	 * 角色：游客
	 */
	String ROLE_VISITOR = "ROLE_VISITOR";
	/**
	 * 一级代理
	 */
	String AGENT_1 = "1";
	/**
	 * 二级代理
	 */
	String AGENT_2 = "2";

	/**
	 * HTTP请求方式：GET
	 */
	String HTTP_GET = "GET";
	/**
	 * HTTP请求方式：DELETE
	 */
	String HTTP_DELETE = "DELETE";
	/**
	 * 角色字典id
	 */
	String ROLE_PID = "793918992aaa11e8be7740167eafddf0";
	/**
	 * 代理级别字典id
	 */
	String PROXY_LEVEL_PID = "c34a816f00f011e8bc3418dbf239df73";
	/**
	 * 是否发送邮件
	 */
	String YES_OR_NOT = "1eb7a2a2012211e8a13a20898491ed6f";
	/**
	 * 用户状态
	 */
	String USER_STATUS = "6c884dfe2b9211e8a9a640167eafddf0";

	String PROXY_ID = "4028b8815567bebb015567cb3e9b0002";
	/**
	 * 接口类型 1 菜单
	 */
	String RESOURCE_TYPE = "1";
	/**
	 * 数据状态：有效状态
	 */
	int ROWSTATE_FREEZE = 1;
	/**
	 * 数据状态：删除状态
	 */
	int ROWSTATE_DELETE = 0;
	/**
	 * 投保类型
	 */
	String POLICY_TYPE = "86e4eabc011711e8a13a20898491ed6f";

	String INSURANCE_TYPE = "9e685ec05cd411e8b5e518dbf239df73";
	/**
	 * 
	 * 流水发生方向增加
	 */
	String DIRECTION_OF_ADD = "1";
	/**
	 * 流水发生方向减少
	 */
	String DIRECTION_OF_REDUCE = "0";
	/**
	 * 发生前余额
	 */
	String BEFORE_BALANCE = "0";
	/**
	 * 产品类型 团体意外险。。。
	 */
	String PRODUCT_TYPE = "e5a544f3356111e8a78518dbf239df73";
	/**
	 * 是
	 */
	String Y = "1";
	/**
	 * 否
	 */
	String N = "0";

	String CODE_UTF = "UTF-8";

	String CODE_GBK = "GBK";

	String CODE_ISO = "ISO-8859-1";

	/**
	 * 默认账户
	 */
	String USER_ACCOUNT = "0141";

	/**
	 * 默认账户
	 */
	String USER_ACCOUNT_NUM = "account";

	/**
	 * 公共虚拟用户 用户ecargo投保，扣除各级代理费用后，剩下金额记入该账户 用户系统记账核对勾稽
	 */
	String COMMON_ACCOUNT = "0320";

	/**
	 * 流水发生方向
	 */
	String LS_FSFX = "722274b3453011e89be818dbf239df73";

	String HEADER_MSIE = "MSIE";
	String HEADER_TRIDENT = "TRIDENT";
	String HEADER_EDGE = "EDGE";
	/**
	 * 操作
	 */
	String OPERA_TYPE = "44c98987071411e89e5420898491ed6f";
	/**
	 * 大类 小类
	 */
	String DALEI = "9d9218a7543511e8b30318dbf239df73";
	String XL_1 = "0f553883543611e8b30318dbf239df73";
	String XL_2 = "3b89c1a1543611e8b30318dbf239df73";
	String XL_3 = "43245bc0543611e8b30318dbf239df73";
	String XL_4 = "46c0bad3543611e8b30318dbf239df73";
	String XL_5 = "4a802de0543611e8b30318dbf239df73";
	String XL_6 = "4ebd1af2543611e8b30318dbf239df73";
	String XL_7 = "530bc1f4543611e8b30318dbf239df73";
	String XL_8 = "56a45927543611e8b30318dbf239df73";
	/**
	 * 包装词
	 */
	String PACKAGES = "592713bc543c11e8b30318dbf239df73";
	/**
	 * 争议解决途径
	 */
	String DISPUTE_FUNCTION = "dbfa4a49543c11e8b30318dbf239df73";

	String PRODUCT_TYPE_VALUE = "86e4eabc011711e8a13a20898491ed6f";

	String POLICY_STATUS_TYPE="f9dd94cc011211e8a13a20898491ed6f";
	/**
	 * 综合费率
	 */
	String RATE_TYPE_1 = "1";
	/**
	 * 一般费率
	 */
	String RATE_TYPE_2="2";
	/**
	 * 中国人寿保险
	 */
	String ZHONG_GUO_REN_SHOU_BAO_XIAN = "中国人寿保险";
	/**
	 * 人民保险公司
	 */
	String REN_MIN_BAO_XIAN_GONG_SI = "人民保险公司";
	/**
	 * 国寿财险
	 */
	String GUO_SHOU_CAI_XIAN = "国寿财险";
	/**
	 * 保单状态，保额用完
	 */
	String POLICY_STATE_INSURE_AMOUNT = "5";
	/**
	 * 保单状态，车次用完
	 */
	String POLICY_STATE_TRAIN_NUM = "4";
	/**
	 * 保单状态正常
	 */
	String POLICY_STATE_NORMAl = "1";
	
	/**
	 * 保险公司：中国人寿
	 */
	String INSURCOMP_ID_RENSHOU="4028b8815566eef0015566f8660c0001";
	/**
	 * 保险公司：人保
	 */
	String INSURCOMP_ID_RENBAO="f9a8b79a5b0f275e015b709bc7e20893";
	/**
	 * 保险公司：国寿财
	 */
	String INSURCOMP_ID_GUOSHOU="2c90732362a3f29c0162a3f501620001";
}

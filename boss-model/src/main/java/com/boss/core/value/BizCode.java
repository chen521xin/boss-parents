/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.value;

/**
 * @description
 * @data 2017年10月26日下午6:27:13
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class BizCode {


	public static final BizCode DEFAULT_PARAM_NULL_EXCEPTION = new BizCode(10001, "入参不能为空");
	public static final BizCode USER_FREEZE_EXCEPTION = new BizCode(10002, "登录用户已冻结");
	public static final BizCode USER_DELETE_EXCEPTION = new BizCode(10003, "登录用户不存在");
	public static final BizCode USER_PASSWORD_EXCEPTION = new BizCode(10004, "用户名密码错误");
	public static final BizCode USER_NOT_FOND_ROLE_EXCEPTION = new BizCode(10005, "用户未分配角色");
	public static final BizCode SUCCESS_LOGIN = new BizCode(10006, "登录成功");
	public static final BizCode SUCCESS_LOGOUT = new BizCode(10007, "登出成功");
	public static final BizCode SYSTEM_IS_BUSY = new BizCode(10008,"系统繁忙，请稍后再试");
	public static final BizCode FAILD_DELETE_EXCEPTION = new BizCode(10009,"删除失败！");
	public static final BizCode UPDATE_FILE_FAILE = new BizCode(10010, "上传文件失败！");
	public static final BizCode PARAM_IS_ERROR = new BizCode(10011, "缺少必要参数！");
	public static final BizCode FILE_UPDATE_ERROR = new BizCode(10012, "图片格式不正确，只能输入jpg、png、gif!");
	public static final BizCode INSERT_USER_FAILD = new BizCode(10013, "用户角色为代理，必须选择代理级别！");
	public static final BizCode PARENT_ID_IS_NULL = new BizCode(10014, "当前用户为二级代理，上级代理不能为空！");
	
	public static final BizCode FAILD_ADD_EXCEPTION = new BizCode(10100, "添加失败");
	public static final BizCode FAILD_UPDATE_EXCEPTION = new BizCode(10110, "修改失败");
	public static final BizCode FAILD_ADD_BALANCE= new BizCode(10101,"添加保额失败");
	public static final BizCode INSUFFICIENT_BALANCE= new BizCode(10102,"额度减少后小于0，请确认！");
	public static final BizCode INSURE_AGAINST_FAILURE= new BizCode(10103,"余额不足，投保失败");
	public static final BizCode FAILD_POLICYNO_ALREADY_EXIST= new BizCode(10104,"保单号已经存在,添加保单失败");
	public static final BizCode FAILD_USERNAME_EXISTS_EXCEPTION = new BizCode(10111, "用户名已经存在，请确认！");
	public static final BizCode BALANCE_EXCEPTION = new BizCode(10112, "当前剩余余额小于0，请确认！");
	public static final BizCode FSFX_EXCEPTION = new BizCode(10113, "发生方向异常，请确认！");
	public static final BizCode REQUEST_EXCEPTION = new BizCode(10114, "请求错误！");
	public static final BizCode THERE_IS_NO_SOUECH_TYPE = new BizCode(11111, "没有该类型，请确认！");
	public static final BizCode USER_ROLE_EXCEPTION = new BizCode(10115, "改用户未设置角色信息,请联系管理员！");
	public static final BizCode UPDATE_IS_FAILED = new BizCode(10116, "修改失败！");
	public static final BizCode DELETE_CODE_FAILD = new BizCode(10117, "删除失败，请确认删除角色下已无任何用户！！");
	public static final BizCode DELETE_USER_FAILD = new BizCode(10118, "删除失败，请确认删除用户下午任何保单！！");
	public static final BizCode USER_RATE_IS_NULL = new BizCode(10119, "当前用户未设置费率，请设置后再进行ecargo投保！");
	public static final BizCode USER_PAR_RATE_IS_NULL = new BizCode(10120, "当前用户的代理未设置费率，请设置后再进行ecargo投保！");
	public static final BizCode USER_IS_NULL = new BizCode(10121, "未找到上级代理用户！");
	public static final BizCode PARENT_USER_RATE_EXCEPTION = new BizCode(10122, "不能小于上级代理用户费率！");
	public static final BizCode ECARGO_XL_NOT_EXISTS = new BizCode(10123, "ecargo小类不存在！");
	
	public static final BizCode EXPORT_FAILD = new BizCode(10124, "导出失败，请重试!");
	public static final BizCode EXPORT_FILE_FAILD = new BizCode(10125, "无导出数据，请确认!");
	public static final BizCode EXCEPTION_UPDATE_PWD_NOT_SAME = new BizCode(10126, "两次输入密码不一致请确认！");
	public static final BizCode EXCEPTION_UPDATE_USER = new BizCode(10127, "修改密码失败，未找到用户！");
	public static final BizCode EXISTS_POLICY_PRODUCT = new BizCode(10128, "存在正在使用该保险公司的保险产品，保险公司不能被删除！");
	public static final BizCode EXISTS_POLICY = new BizCode(10129, "存在正在使用该保险产品的保单，保险产品不能被删除！");
	public static final BizCode TRAIN_NUM_INSURE_AGAINST_FAILURE = new BizCode(10130, "存在正在使用该保险产品的保单，保险产品不能被删除！");
	public static final BizCode USER_RATE_EXCEPTION = new BizCode(10131, "上级代理未设置费率");
	public static final BizCode EXISTS_DETAILS = new BizCode(10132,"存在使用本保单起运书，该保单不能被删除！");
	public static final BizCode INSURCOMP_INEXISTENCE = new BizCode(10133,"保险公司不存在！");
	public static final BizCode USER_IS_NOT_EXISTS_ERROR = new BizCode(10134,"用户不存在！");
	public static final BizCode INSURE_AGAINST_FAILURE_MAIL = new BizCode(10135,"邮件发送失败，请联系管理员！");
	public static final BizCode INSURE_AGAINST_FAILURE_RENBAO_XML = new BizCode(10136,"人保调用接失败，请联系管理员！");
	public static final BizCode NO_ADMIN_EMAIL = new BizCode(10137,"管理员未设置邮箱，邮件发送失败，请联系管理员！");
	public static final BizCode NO_USER_EMAIL = new BizCode(10138,"用户未设置邮箱，邮件发送失败，请联系管理员！");
	public static final BizCode NO_INSURCOMP_EMAIL = new BizCode(10139,"保险员未设置邮箱，邮件发送失败，请联系管理员！");
	public static final BizCode RENEWAL_OF_INSURCOMP  = new BizCode(10140,"该保险公司无续保权限，请确认！");
	public static final BizCode ERROR_TYPE  = new BizCode(10141,"暂不支持该类型投保，请选择保额投保！");
	public BizCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return getMessage();
	}

}

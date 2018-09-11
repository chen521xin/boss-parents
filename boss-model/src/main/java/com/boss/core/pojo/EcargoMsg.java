package com.boss.core.pojo;

/**
 * 
 * @author long.cai
 *
 */
public class EcargoMsg 
{
	/**
	 * 投保结果，成功/失败
	 */
	private boolean result;
	
	private String policyNo;
	
	private String applyNo;
	private String applyStatus;

	/**
	 * 失败后的异常信息
	 */
	private String errMsg;
	
	
	
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}


	
	/**
	 * 投保结果，成功/失败
	 */
	public boolean getResult() {
		return result;
	}

	/**
	 * 投保结果，成功/失败
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * 失败后的异常信息
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 失败后的异常信息
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	
	/**
	 * 构造方法
	 * 
	 * @param result 投保结果
	 * @param erString 错误信息
	 */
	public EcargoMsg(boolean result,String errMsg)
	{
		this.result=result;
		this.errMsg=errMsg;
	}
	
	/**
	 * 构造方法
	 */
	public EcargoMsg(){}
}

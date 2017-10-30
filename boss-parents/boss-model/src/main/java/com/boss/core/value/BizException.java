/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.value;
/**
 * @description
 * @data 2017年10月26日下午6:38:09
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class BizException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8690668125816251935L;

	public BizException(BizCode bizCode){
		super(bizCode.getMessage());
		this.bizCode=bizCode;
	}
	public BizException(BizCode bizCode,Exception e){
		super(e);
		this.bizCode=bizCode;
	}
	public BizCode bizCode;

	public BizCode getBizCode() {
		return bizCode;
	}

	public void setBizCode(BizCode bizCode) {
		this.bizCode = bizCode;
	}
	
}

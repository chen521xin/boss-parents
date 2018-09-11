package com.boss.core.pojo;

import com.boss.core.db.EcargoInfo;

/**
 * ecargo投保信息封装类
 * @author long.cai
 *
 */
public class EcargoProjo {
	
	public EcargoProjo(){}
	
	/**
	 * 
	 * @param ecargoMsg 返回信息类
	 * @param ecargoInfo 投保信息类
	 */
	public EcargoProjo(EcargoMsg ecargoMsg,EcargoInfo ecargoInfo){
		
		this.ecargoMsg=ecargoMsg;
		this.ecargoInfo=ecargoInfo;
	}
	
	/**
	 * 投保结果返回
	 */
	private EcargoMsg ecargoMsg;
	
	/**
	 * 投保结果返回
	 */
	public EcargoMsg getEcargoMsg() {
		return ecargoMsg;
	}

	/**
	 * 投保结果返回
	 */
	public void setEcargoMsg(EcargoMsg ecargoMsg) {
		this.ecargoMsg = ecargoMsg;
	}

	/**
	 * Ecargo投保实体类
	 */
	public EcargoInfo getEcargoInfo() {
		return ecargoInfo;
	}

	/**
	 * Ecargo投保实体类
	 */
	public void setEcargoInfo(EcargoInfo ecargoInfo) {
		this.ecargoInfo = ecargoInfo;
	}

	/**
	 * Ecargo投保实体类
	 */
	private EcargoInfo ecargoInfo;
	
	

}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.apache.http.client.ClientProtocolException;

import com.boss.core.db.EcargoInfo;
import com.boss.core.pojo.EcargoPojo;
import com.boss.core.pojo.EcargoProjo;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.exceptions.IdentCardException;

/**
 * @description 
 * @data 2018年5月11日下午3:22:30
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface EcargoService {

	Page<EcargoPojo> selectByPage(Page<EcargoPojo> page,EcargoPojo ecargo );
	EcargoProjo insertEcargo(EcargoInfo ecargoInfo) throws ClientProtocolException, IOException, URISyntaxException, IdentCardException, ParseException;
	EcargoPojo selectEcargoByNo(String Ecargo);
	EcargoPojo selectEcargoApplyNo(String applyNo);
	
}

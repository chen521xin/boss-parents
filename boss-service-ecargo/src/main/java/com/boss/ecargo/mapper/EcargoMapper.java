/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.mapper;

import java.util.List;

import com.boss.core.db.EcargoInfo;
import com.boss.core.db.User;
import com.boss.core.db.UserRateInfo;
import com.boss.core.pojo.EcargoPojo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月21日上午12:34:13
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface EcargoMapper {

	List<EcargoPojo> selectByPage(Page<EcargoPojo> page,EcargoPojo ecargo );
	List<EcargoPojo> selectByPageByUserId(Page<EcargoPojo> page,EcargoPojo ecargo );
	List<EcargoPojo> selectByPageProx(Page<EcargoPojo> page,EcargoPojo ecargo );
	EcargoPojo selectByPage(EcargoPojo ecargo );
	int updatePolicyNo(EcargoInfo ecargoInfo);
	int insertEcargo(EcargoInfo ecargoInfo);
	EcargoPojo selectEcargoByNo(String Ecargo);
	EcargoPojo selectEcargoByApplyNo(String applyNo);
	User findParentIdAndUserRate(EcargoInfo ecargoInfo);
	String findUserRate(UserRateInfo rateInfo);
	
}

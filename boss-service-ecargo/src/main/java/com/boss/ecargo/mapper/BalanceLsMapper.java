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

import com.boss.core.db.BalanceLsInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月21日上午12:34:13
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public interface BalanceLsMapper {

	void insertBalanceLs(BalanceLsInfo balanceLs);
	
	List<BalanceLsInfo> selectBalanceLs(Page<BalanceLsInfo> page,BalanceLsInfo ls);
	List<BalanceLsInfo> selectBalanceLs(BalanceLsInfo ls);
}

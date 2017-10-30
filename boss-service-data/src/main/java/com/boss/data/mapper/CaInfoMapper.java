/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.mapper;

import java.util.List;

import com.boss.core.db.CaInfo;
import com.boss.utils.feature.orm.mybatis.Page;
/**
 * @description
 * @data 2017年10月24日下午6:48:33
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public interface CaInfoMapper {

	List<CaInfo> selectCaInfoListByPage(Page<CaInfo> page);
}

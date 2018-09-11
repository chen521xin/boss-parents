/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.mapper;

import java.util.List;

import com.boss.core.db.Jlog;
import com.boss.core.pojo.JlogPojo;
import com.boss.db.feature.orm.mybatis.Page;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


public interface JlogMapper {
	    
	//记录日志
	int addJlog(Jlog jLog);
	//日志查询
	List<Jlog> findAll(Page<Jlog> page,JlogPojo jLog);
}

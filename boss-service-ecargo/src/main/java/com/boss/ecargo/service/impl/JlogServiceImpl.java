/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Jlog;
import com.boss.ecargo.mapper.JlogMapper;
import com.boss.ecargo.service.JlogService;
import com.boss.utils.cons.CommonUtils;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


@Service
@Transactional(rollbackFor=Exception.class)
public class JlogServiceImpl extends BaseServiceImpl implements JlogService {

	@Autowired
	private JlogMapper jlogMapper;
	@Override
	public boolean addJlog(Jlog jLog) {
		jLog.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		this.num = jlogMapper.addJlog(jLog);
		return num > 0 ? true : false;
	}


	public boolean insertJlog(Jlog jLog) {
		jLog.changeStatus(CommonUtils.METHOD_ADD, jLog.getUserName());
		this.num = jlogMapper.addJlog(jLog);
		return num > 0 ? true : false;
	}
}

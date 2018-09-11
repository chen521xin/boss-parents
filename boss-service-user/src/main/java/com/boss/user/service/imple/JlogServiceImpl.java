/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Jlog;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.user.mapper.JlogMapper;
import com.boss.user.service.JlogService;
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
public class JlogServiceImpl implements JlogService {

	@Autowired
	private JlogMapper jlogMapper;
	@Override
	public int addJlog(Jlog jLog) {
		int flag = jlogMapper.addJlog(jLog);
		return flag;
	}
	@Override
	public Page<Jlog> findAllByPage(Page<Jlog> page,Jlog jLog) {
		jlogMapper.findAll(page,jLog);
		return page;
	}

}

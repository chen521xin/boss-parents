/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Jlog;
import com.boss.core.pojo.JlogPojo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.JlogMapper;
import com.boss.data.service.JlogService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.StringUtil;
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

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Autowired
	private JlogMapper jlogMapper;
	@Override
	public void addJlog(Jlog logs) {
		Jlog log =new Jlog();
		BeanUtils.copyProperties(logs, log);
		log.changeStatus(CommonUtils.METHOD_ADD, log.getUserName());
		log.setFullName(StringUtil.convertString(logs.getFullName(), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF));
		log.setMessage(StringUtil.convertString(logs.getMessage(), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF));
		log.setBizType(StringUtil.convertString(logs.getBizType(), CommonUtils.CODE_ISO, CommonUtils.CODE_UTF));
		this.num = jlogMapper.addJlog(log);
		if(num <= 0){
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
	}
	@Override
	public Page<Jlog> findAllByPage(Page<Jlog> page,JlogPojo jLog) {
		jlogMapper.findAll(page,jLog);
		return page;
	}

	public void insertJlog(Jlog jLog) {
		jLog.changeStatus(CommonUtils.METHOD_ADD, jLog.getUserName());
		this.num = jlogMapper.addJlog(jLog);
		if(num <= 0){
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
	}
}

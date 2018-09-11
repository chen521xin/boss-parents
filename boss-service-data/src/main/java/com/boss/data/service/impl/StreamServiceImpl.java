/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Stream;
import com.boss.data.mapper.StreamMapper;
import com.boss.data.service.StreamService;
import com.boss.utils.cons.CommonUtils;

/**
 * @description
 * @data 2018年4月8日下午8:29:27
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class StreamServiceImpl extends BaseServiceImpl implements StreamService {

	@Autowired
	private StreamMapper streamMapper;

	@Override
	public void insertStream(Stream st) {
		st.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		this.num = streamMapper.insertStream(st);
	}

}

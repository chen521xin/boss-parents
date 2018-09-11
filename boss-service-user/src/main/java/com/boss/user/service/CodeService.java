/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service;

import java.util.List;
import java.util.Map;

import com.boss.core.db.Code;
import com.boss.core.pojo.CodePojo;
import com.boss.db.feature.orm.mybatis.Page;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/

public interface CodeService {

	/**
	 * 分页查询父级参数
	 * @param page
	 * @param codePo
	 * @return
	 */
	Page<Code> queryCode(Page<Code> page ,CodePojo codePo);
	
	/**
	 * 查询所有参数
	 * @return
	 */
	List<Code> findCodeAll();
	
	/**
	 * 根据父级id查询所有子级
	 * @param key
	 * @return
	 */
	Map<String,List<Code>> getByPid(String key);
	/**
	 * 根据pid和value查询单个参数
	 * @param pid
	 * @param value
	 * @return
	 */
	Code getPidAndValue(String pid,String value);
	/**
	 * 新增参数
	 * @param code
	 * @return
	 */
	boolean addCode(CodePojo code);
	/**
	 * 更新参数
	 * @param code
	 * @return
	 */
	boolean updateCode(CodePojo code);
	/**
	 * 删除参数；删除父级参数时，删除该父级下所有子级参数
	 * @param code
	 * @return
	 */
	boolean deleteCode(String id);
	
	
}

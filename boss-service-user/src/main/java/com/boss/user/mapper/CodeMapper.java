/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.mapper;

import java.util.List;

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


/**
 * mybatis映射接口
 * @author Administrator
 */
public interface CodeMapper  {

	/**
	 * 分页查询
	 * @param codePage
	 * @param CodePojo
	 * @return
	 */
	List<Code> findParentCodeAll(Page<Code> codePage,CodePojo CodePojo);
	/**
	 * 查询所有参数
	 * @return
	 */
	List<Code> findCodeAll();
	/**
	 * 添加参数
	 * @param code
	 * @return
	 */
	int addCode(Code code);
	/**
	 * 更新参数
	 * @param code
	 * @return
	 */
	int updateCode(Code code);
	/**
	 * 删除参数
	 * @param id
	 * @return
	 */
	int deleteCode(String id);
}

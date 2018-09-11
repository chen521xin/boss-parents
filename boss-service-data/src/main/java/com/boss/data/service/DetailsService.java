/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boss.core.db.Details;
import com.boss.core.db.DetailsQueryInfo;
import com.boss.core.db.DetailsUpdateInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月2日下午6:53:35
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface DetailsService {

	/**
	 * 分页查询
	 * @param page
	 * @param details
	 * @return
	 */
	Page<DetailsQueryInfo> findByPage(Page<DetailsQueryInfo> page,DetailsQueryInfo details);
	/**
	 * 导出
	 * @param page
	 * @param details
	 * @return
	 */
	void exportDetails(Page<DetailsQueryInfo> page,DetailsQueryInfo details,HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * 根据id查询起运书明细信息
	 * @param id
	 * @reurn
	 */
	Details findById(String id);
	/**
	 * 新增起运书明细
	 * @param details
	 * @return 
	 */
	boolean insertDetails(Details details);
	/**
	 * 更新起运书明细
	 * @param details
	 * @return 
	 */
	boolean updateDetails(DetailsUpdateInfo details);
	/**
	 * 逻辑删除起运书明细
	 * @param id
	 * @return
	 */
	boolean deleteDetails(String id);
}

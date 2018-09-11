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

import com.boss.core.db.Details;
import com.boss.core.db.DetailsQueryInfo;
import com.boss.core.db.DetailsUpdateInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description 
 * @data 2018年4月1日下午7:36:25
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface DetailsMapper {

	/**
	 * 分页查询
	 * @param page
	 * @param details
	 * @return
	 */
	List<DetailsQueryInfo> findByPage(Page<DetailsQueryInfo> page,DetailsQueryInfo details);
	/**
	 * 保险员分页查询
	 * @param page
	 * @param details
	 * @return
	 */
	List<DetailsQueryInfo> findByInsurcompId(Page<DetailsQueryInfo> page,DetailsQueryInfo details);
	/**
	 * 代理分页查询
	 * @param page
	 * @param details
	 * @return
	 */
	List<DetailsQueryInfo> findByPoxy(Page<DetailsQueryInfo> page,DetailsQueryInfo details);
	/**
	 * 本人分页查询
	 * @param page
	 * @param details
	 * @return
	 */
	List<DetailsQueryInfo> findByUsers(Page<DetailsQueryInfo> page,DetailsQueryInfo details);
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
	int insertDetails(Details details);
	/**
	 * 更新起运书明细
	 * @param details
	 * @return 
	 */
	int updateDetails(DetailsUpdateInfo details);
	/**
	 * 逻辑删除起运书明细
	 * @param id
	 * @return
	 */
	int deleteDetails(String id);
	/**
	 * 删除保单时查询查询该保单是否进行了投保
	 */
	int findDetailsByPolicy(String policyId);
}

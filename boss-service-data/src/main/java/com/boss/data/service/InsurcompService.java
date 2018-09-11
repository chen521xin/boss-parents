/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Insurcomp;
import com.boss.db.feature.orm.mybatis.Page;

@Transactional
public interface InsurcompService {
	/**
	 * 查询所有保险公司信息
	 */
	List<Insurcomp> queryAllInsurcomp();
	/**
	 * 根据保险公司名称查询保险公司信息
	 */
	Insurcomp queryInsurcompByFullName(String fullName);
	/**
	 * 根据Id查询该保险公司信息
	 */
	Insurcomp queryInsurcompById(String id);
	/**
	 * 分页查询保险公司信息
	 * @param page
	 * @param insurcomp
	 * @return
	 */
	Page<Insurcomp> queryInsurcompByPage(Page<Insurcomp> page,Insurcomp insurcomp);
	/**
	 * 新增保险公司信息
	 * @param insurcomp
	 * @return
	 */
	boolean insertInsurcomp(Insurcomp insurcomp);
	/**
	 * 更新保险公司信息
	 * @param insurcomp
	 * @return
	 */
	boolean updateInsurcomp(Insurcomp insurcomp);
	/**
	 * 删除保险公司
	 * @param id
	 * @return
	 */
	boolean deleteInsurcomp(String id);
}

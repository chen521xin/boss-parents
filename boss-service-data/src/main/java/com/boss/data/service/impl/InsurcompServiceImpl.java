/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Insurcomp;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.InsurcompMapper;
import com.boss.data.service.InsurcompService;
import com.boss.data.service.PolicyProductService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;
/**
 * 保险公司管理
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class InsurcompServiceImpl extends BaseServiceImpl implements InsurcompService {

	@Autowired
	private InsurcompMapper insurcompMapper;
	
	@Autowired
	private PolicyProductService policyProductService;
	/**
	 * 分页查询保险公司信息
	 * @param page
	 * @param insurcomp
	 * @return
	 */
	
	@Override
	public Page<Insurcomp> queryInsurcompByPage(Page<Insurcomp> page, Insurcomp insurcomp) {
		insurcompMapper.queryInsurcompByPage(page, insurcomp);
		return page;
	}
	/**
	 * 新增保险公司信息
	 * @param insurcomp
	 * @return
	 */
	@Override
	public boolean insertInsurcomp(Insurcomp insurcomp) {
		insurcomp.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		this.num = insurcompMapper.insertInsurcomp(insurcomp);
		if(num < 0){
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
		addLog(OperationType.ADD.getOption(), BusinessUtils.INSURCOMP);
		return true;
	}
	/**
	 * 更新保险公司信息
	 * @param insurcomp
	 * @return
	 */
	@Override
	public boolean updateInsurcomp(Insurcomp insurcomp) {
		insurcomp.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = insurcompMapper.updateInsurcomp(insurcomp);
		if(num < 0){
			throw new BizException(BizCode.FAILD_UPDATE_EXCEPTION);
		}
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.INSURCOMP);
		return true;
	}
	/**
	 * 删除保险公司信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteInsurcomp(String id) {
		if(policyProductService.getByInsurcompId(id).size() > 0){
			throw new BizException(BizCode.EXISTS_POLICY_PRODUCT);
		}
		this.num =insurcompMapper.deleteInsurcomp(id);
		if(num < 0){
			throw new BizException(BizCode.FAILD_DELETE_EXCEPTION);
		}
		addLog(OperationType.DELETE.getOption(), BusinessUtils.INSURCOMP);
		return  true;
	}
	/**
	 * 查询所有保险公司
	 */
	@Override
	public List<Insurcomp> queryAllInsurcomp() {
		List<Insurcomp> insurcompList = insurcompMapper.queryAllInsurcomp();
		return insurcompList;
	}
	/**
	 * 根据保险公公司名称查询所有保险公司
	 */
	@Override
	public Insurcomp queryInsurcompByFullName(String fullName) {
		Insurcomp Insurcomp = insurcompMapper.queryInsurcompByFullName(fullName);
		return Insurcomp;
	}
	/**
	 * 根据Id查询该保险公司信息
	 */
	@Override
	public Insurcomp queryInsurcompById(String id) {
		Insurcomp insurcomp = new Insurcomp();
		insurcomp = insurcompMapper.queryInsurcompById(id);
		return insurcomp;
	}

}

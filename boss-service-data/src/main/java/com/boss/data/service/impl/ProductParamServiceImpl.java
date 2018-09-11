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

import com.boss.core.db.ProductParam;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.ProductParamMapper;
import com.boss.data.service.ProductParamService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description
 * @data 2018年3月5日上午8:12:36
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Service	
@Transactional(rollbackFor=Exception.class)
public class ProductParamServiceImpl extends BaseServiceImpl implements ProductParamService {

	@Autowired
	private ProductParamMapper productParamMapper;
	/**
	 * 根据产品productId查询保额值
	 */
	public List<ProductParam> findByProductId(String productId){
		List<ProductParam> paramList = productParamMapper.findByProductId(productId);
		return paramList;
	}

	/**
	 * 分页查询产品参数
	 * @param page
	 * @param productParam
	 * @return
	 */
	@Override
	public Page<ProductParam> findByPage(Page<ProductParam> page, ProductParam productParam) {
		productParamMapper.findByPage(page, productParam);
		return page;
	}

	@Override
	/**
	 * 新增产品参数
	 * @param productParam
	 * @return
	 */
	public boolean insertProductParam(ProductParam productParam) {
		productParam.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		this.num = productParamMapper.insertProductParam(productParam);
		if(num < 0){
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
		addLog(OperationType.ADD.getOption(), BusinessUtils.PRODUCTPARAM);
		return true;
	}

	@Override
	/**
	 * 修改产品参数
	 * @param productParam
	 * @return
	 */
	public boolean updateProductParam(ProductParam productParam) {
		productParam.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = productParamMapper.updateProductParam(productParam);
		if(num < 0){
			throw new BizException(BizCode.FAILD_UPDATE_EXCEPTION);
		}
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.PRODUCTPARAM);
		return  true;
	}

	@Override
	/**
	 * 删除产品参数
	 * @param id
	 * @return
	 */
	public boolean deleteProductParam(String id) {
		this.num = productParamMapper.deleteProductParam(id);
		if(num < 0){
			throw new BizException(BizCode.FAILD_DELETE_EXCEPTION);
		}
		addLog(OperationType.DELETE.getOption(), BusinessUtils.PRODUCTPARAM);
		return true;
	}

}

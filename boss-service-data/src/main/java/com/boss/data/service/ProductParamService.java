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

import com.boss.core.db.ProductParam;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年3月5日上午8:10:54
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface ProductParamService {
	/**
	 * 根据产品productId查询宝额值
	 */
	List<ProductParam> findByProductId(String productId);
	/**
	 * 分页查询产品参数
	 */
	Page<ProductParam> findByPage(Page<ProductParam> page,ProductParam productParam);
	/**
	 * 新增产品参数
	 */
	boolean insertProductParam(ProductParam productParam);
	/**
	 * 修改产品参数
	 */
	boolean updateProductParam(ProductParam productParam);
	/**
	 * 删除产品参数
	 */
	boolean deleteProductParam(String id);
}

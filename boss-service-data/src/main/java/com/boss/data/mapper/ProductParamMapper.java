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

import com.boss.core.db.ProductParam;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年3月4日下午11:28:09
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public interface ProductParamMapper {

	/**
	 * 根据产品productId查询宝额值
	 */
	List<ProductParam> findByProductId(String productId);
	/**
	 * 分页查询产品参数
	 */
	List<ProductParam> findByPage(Page<ProductParam> page,ProductParam productParam);
	/**
	 * 新增产品参数
	 */
	int insertProductParam(ProductParam productParam);
	/**
	 * 修改产品参数
	 */
	int updateProductParam(ProductParam productParam);
	/**
	 * 删除产品参数
	 */
	int deleteProductParam(String id);
}

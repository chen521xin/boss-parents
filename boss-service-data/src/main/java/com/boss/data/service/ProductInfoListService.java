/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import com.boss.core.db.ProductInfo;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2018年03月24日下午6:12:39
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public interface ProductInfoListService {
	/**
	 * 更新文件表
	 * @param product
	 * @return
	 */
	void  updateImage(ProductInfo product);
	/**
	 * 插入文件表
	 * @param product
	 * @return
	 */
	void insertImage(ProductInfo product);
	/**
	 * 列表分页
	 * @param page
	 * @param product
	 * @return
	 */
	Page<ProductInfo> selectProductListPyPage(Page<ProductInfo> page,ProductInfo product);
	
	/**
	 * 删除
	 * @param ID
	 * @return
	 */
	void deleteProduct(String id);
	
}

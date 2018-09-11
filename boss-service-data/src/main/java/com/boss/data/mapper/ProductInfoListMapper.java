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
public interface ProductInfoListMapper {
	/**
	 * 更新文件表
	 * @param product
	 * @return
	 */
	int  updateImage(ProductInfo product);
	/**
	 * 插入文件表
	 * @param product
	 * @return
	 */
	int insertImage(ProductInfo product);
	/**
	 * 列表分页
	 * @param page
	 * @param product
	 * @return
	 */
	List<ProductInfo> selectProductListPyPage(Page<ProductInfo> page,ProductInfo product);
	/**
	 * 删除
	 * @param ID
	 * @return
	 */
	int deleteProduct(String id);
	
}

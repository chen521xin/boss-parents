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

import com.boss.core.db.FileInfo;
import com.boss.core.db.ProductInfo;
import com.boss.data.mapper.ProductInfoListMapper;
import com.boss.data.service.FileService;
import com.boss.data.service.ProductInfoListService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description
 * @data 2018年03月24日下午6:12:39
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor=Exception.class)
public class ProductInfoListServiceImpl extends BaseServiceImpl implements ProductInfoListService {

	@Autowired
	private ProductInfoListMapper productMapper;

	@Autowired
	private FileService fileService;

	/**
	 * 修改产品
	 */
	@Override
	public void updateImage(ProductInfo product) {
		product.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		productMapper.updateImage(product);
		insertOrUpdateFile(product);
		addLog(OperationType.DELETE.getOption(), BusinessUtils.PRODUCT);
	}

	/**
	 * 增加产品
	 */
	@Override
	public void insertImage(ProductInfo product) {
		product.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		productMapper.insertImage(product);
		insertOrUpdateFile(product);
		addLog(OperationType.ADD.getOption(), BusinessUtils.PRODUCT);
	}

	public void insertOrUpdateFile(ProductInfo product) {	
		fileService.deleteFile(product.getId());	
		FileInfo fileInfo = product.getFile();
		fileInfo.setBizId(product.getId());
		fileInfo.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		fileInfo.setModuleType(BusinessUtils.PRODUCT);
		fileService.insertFile(fileInfo);

	}

	/**
	 * 查询产品管理分页
	 */
	@Override
	public Page<ProductInfo> selectProductListPyPage(Page<ProductInfo> page, ProductInfo product) {
		productMapper.selectProductListPyPage(page, product);
		converParamOut(page.getResult());
		return page;
	}

	/**
	 * 出参转换
	 * 
	 * @param productList
	 */
	public void converParamOut(List<ProductInfo> productList) {
		if (productList == null) {
			return;
		}
		FileInfo file = null;
		for (ProductInfo productInfo : productList) {
			productInfo.setProductTypeName(
					getCodeNameByPidAndValue(CommonUtils.PRODUCT_TYPE, productInfo.getProductTypeId()));
			file = new FileInfo();
			file.setId(productInfo.getFileId());
			file.setFileName(productInfo.getFileName());
			file.setFilePath(productInfo.getFilePath());
			productInfo.setFile(file);
		}
	}

	@Override
	public void deleteProduct(String id) {
		productMapper.deleteProduct(id);
		fileService.deleteFileById(id);
		addLog(OperationType.DELETE.getOption(), BusinessUtils.PRODUCT);
	}

}

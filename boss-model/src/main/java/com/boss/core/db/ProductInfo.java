/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.db;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @description
 * @data 2018年03月24日下午6:12:39
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class ProductInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 818166244402646083L;
	/**
	 * 产品名称
	 */
	@Size(max=200,message="产品名称字段长度超出限制，不能超过200位！")
	@NotNull(message="产品名称不能为空！")
	private String productName;
	/**
	 * 产品类型表ID
	 */
	@NotNull(message="产品类型不能为空！")
	private String productTypeId;
	/**
	 * 产品类型名称
	 */
	private String productTypeName;
	/**
	 * 产品描述，条款
	 */
	
	@Size(max=2000,message="产品描述字段长度超出限制，不能超过2000位！")
	private String productInfo;
	/**
	 * 产品所属保险公司
	 */
	@Size(max=32,message="产品描述字段长度超出限制，不能超过32位！")
	@NotNull(message="产品所属保险公司为空")
	private String companyId;
	/**
	 * 金额
	 */
	@Size(max=200,message="展示图片的标题长度超出限制，不能超过200位！")
	@NotNull(message="金额不能为空！")
	private String imgTitle;

	private FileInfo file;

	/**
	 * 图片路径
	 */
	private String filePath;
	/**
	 * 图片文件名称
	 */
	private String fileName;
	/**
	 * 文件id
	 */
	private String fileId;

	/**
	 * 文件绝对路径
	 */
	private String fileDecisionPath ;
	
	private String companyName;

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public FileInfo getFile() {
		return file;
	}

	public void setFile(FileInfo file) {
		this.file = file;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getFileDecisionPath() {
		return fileDecisionPath;
	}

	public void setFileDecisionPath(String fileDecisionPath) {
		this.fileDecisionPath = fileDecisionPath;
	}

	@Override
	public String toString() {
		return "ProductInfo [productName=" + productName + ", productTypeId=" + productTypeId + ", productInfo="
				+ productInfo + ", companyId=" + companyId + ", imgTitle=" + imgTitle + ", file=" + file + ", filePath="
				+ filePath + ", fileName=" + fileName + ", fileId=" + fileId + "]";
	}

}

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

import javax.validation.constraints.Size;

public class FileInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1586465937062144451L;
	/**
	 * 图片路径
	 */
	@Size(max=200,message="图片路径字段长度超出限制，不能超过200位！")
	private String filePath;
	/**
	 * 图片文件名称
	 */
	@Size(max=200,message="图片文件名称字段长度超出限制，不能超过200位！")
	private String fileName;
	/**
	 * 文件绝对路径
	 */
	private String fileDecisionPath ;
	/**
	 * 业务id
	 */
	private String bizId;
	/**
	 * 文件类别
	 */
	private String fileType;
	/**
	 * 模块类型，如：产品管理
	 */
	private String moduleType;

	private String fileId;

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

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileDecisionPath() {
		return fileDecisionPath;
	}

	public void setFileDecisionPath(String fileDecisionPath) {
		this.fileDecisionPath = fileDecisionPath;
	}

}

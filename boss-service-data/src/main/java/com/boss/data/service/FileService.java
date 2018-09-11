/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.boss.core.db.FileInfo;

public interface FileService {
	/**
	 * 根据id删除文件
	 * @param id
	 * @return
	 */
	int deleteFileById(String id);
	/**
	 * 插入fiel表
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int insertFile(FileInfo info);
	/**
	 * 根据业务id查找所有fileId
	 * @param bizId
	 * @return
	 */
	List<String> selectIdByBizId(String bizId);  
	
	int udpateFileById(FileInfo info);
	/**
	 * 插入fiel表
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	FileInfo uploadFile(MultipartFile partFile);
	
	/**
	 * 删除fiel表
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int deleteFile(String id);
}

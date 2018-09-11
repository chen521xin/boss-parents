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

import com.boss.core.db.FileInfo;

public interface FileMapper {

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
	 */
	int insertFile(FileInfo file);
	/**
	 * 根据业务id查找所有fileId
	 * @param bizId
	 * @return
	 */
	List<String> selectIdByBizId(String bizId);    
	
	/**
	 * 更新
	 * @param file
	 * @return
	 */
	int udpateFileById(FileInfo file);
	/**
	 * 删除id
	 * @param bizId
	 * @return
	 */
	int deleteFileByBizId(String bizId);
}

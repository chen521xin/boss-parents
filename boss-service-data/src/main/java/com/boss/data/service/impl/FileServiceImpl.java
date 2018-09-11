/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.boss.core.db.FileInfo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.FileMapper;
import com.boss.data.service.FileService;
import com.boss.utils.DateFormatUtils;
import com.boss.utils.cons.CommonUtils;

@Service
@Transactional(rollbackFor=Exception.class)
public class FileServiceImpl extends BaseServiceImpl implements FileService {

	private Logger logger = Logger.getLogger(FileServiceImpl.class);

	@Value("${file.rootPath}")
	private String rootPath;

	@Autowired
	private FileMapper fileMapper;

	/**
	 * 删除文件
	 */

	@Override
	public int deleteFileById(String id) {
		return fileMapper.deleteFileById(id);
	}

	/**
	 * 保存文件
	 */
	@Override
	public FileInfo uploadFile(MultipartFile partFile) {
		String path=String.format("%s%s%s", "/images/",DateFormatUtils.DateFormatetoString(new Date(), CommonUtils.DATE_FORMATE_YMD), "/");;
		String filePath = String.format("%s%s", rootPath, path);

		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String fileOriginalName = partFile.getOriginalFilename();
		if(!checkAmage(fileOriginalName)){
			throw new BizException(BizCode.FILE_UPDATE_ERROR);
		}
		String newFileName = UUID.randomUUID() + fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
		File file = new File(filePath + newFileName);
		// 文件写入磁盘
		try {
			partFile.transferTo(file);
		} catch (IllegalStateException e) {
			logger.error("--> 上传失败图片，失败原因：" + e.getMessage().toString());
			throw new BizException(BizCode.UPDATE_FILE_FAILE);
		} catch (IOException e) {
			logger.error("--> 上传失败图片，失败原因：" + e.getMessage().toString());
			throw new BizException(BizCode.UPDATE_FILE_FAILE);
		}
		// 返回存储的相对路径+文件名称
		FileInfo fileInfo=new FileInfo();
		fileInfo.setFileName(newFileName);
		fileInfo.setFilePath(path);
		fileInfo.setFileDecisionPath(String.format("%s%s", path,newFileName));
		return fileInfo;
	}

	/**
	 * 根据业务id查找下面所有的文件
	 */
	@Override
	public List<String> selectIdByBizId(String bizId) {
		return fileMapper.selectIdByBizId(bizId);
	}

	/**
	 * 更新
	 */
	@Override
	public int udpateFileById(FileInfo info) {
		return fileMapper.udpateFileById(info);
	}

	/**
	 * 插入
	 */
	@Override
	public int insertFile(FileInfo info) {
		return fileMapper.insertFile(info);
	}

	public static boolean checkAmage(String fileName){
		if(fileName==null || fileName.lastIndexOf(".")==-1){
			return false;
		}
		List<String> images=Arrays.asList(CommonUtils.IMAGE);
		if(!images.contains(fileName.substring(fileName.lastIndexOf(".")+1))){
			return false;
		}
		return true;
	}

	@Override
	public int deleteFile(String id) {
		return fileMapper.deleteFileByBizId(id);
	}
}
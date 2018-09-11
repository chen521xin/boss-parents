/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Code;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.mapper.CodeMapper;
import com.boss.data.service.CodeService;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;


/**
 * 
 * 业务类
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CodeServiceImpl extends BaseServiceImpl implements CodeService {

	@Autowired
	private CodeMapper codeMapper;
	/**
	 * 查询所有参数
	 */
	@Override
	public List<Code> findCodeAll() {
		return codeMapper.findCodeAll();
	}
	
	/**
	 * 分页查询参数
	 * @param page,codePo
	 * @return
	 */
	public Page<Code> queryCode(Page<Code> page ,Code code){
		codeMapper.findParentCodeAll(page,code);
		List<Code> parentList = page.getResult();
		for(Code codes : parentList){
			codes.setCodeList(getCodeList(codes.getId()));
		}
		return page;
	}
	/**
	 * 查询该父级下所有子级
	 * @param key
	 * @return
	 */
	public Map<String,List<Code>> getByPid(String key){
		Map<String,List<Code>> map = new HashMap<String,List<Code>>();
		String [] keys = key.split(",");
		for(String keyes : keys){
			List<Code> codeList = getCodeList(keyes);
			map.put(keyes, codeList);
		}
		return map;
	}
	/**
	 * 根据pid和value查询单个参数
	 * @param pid
	 * @param value
	 * @return
	 */
	public Code getPidAndValue(String pid,String value){
		return getPidAndValue(pid, value);
	}
	
	/**
	 * 新增参数
	 * @param codePojo
	 * @return
	 */
	public boolean addCode(Code code) {
		int index = 0;
		code.changeStatus(CommonUtils.METHOD_ADD, getFullName());
		if(code.getPid() == null){
			index = 0;
			code.setIndex(index);	
		}else{
			code.setIndex(index+1);//存在问题
		}
		this.num = codeMapper.addCode(code);
		if(num < 1){
			throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
		}
		refreshCache();
		addLog(OperationType.ADD.getOption(), BusinessUtils.CODE);
		return  true;
	}

	/**
	 * 更新参数
	 * @param codePojo
	 * @return
	 */
	@Override
	public boolean updateCode(Code code) {
		code.changeStatus(CommonUtils.METHOD_UPDATE, getFullName());
		this.num = codeMapper.updateCode(code);
		if(num < 1){
			throw new BizException(BizCode.FAILD_UPDATE_EXCEPTION);
		}
		refreshCache();
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.CODE);
		return true;
	}
	/**
	 * 删除参数
	 * @param id
	 */
	@Override
	public boolean deleteCode(String id) {
		this.num = codeMapper.deleteCode(id);
		if(num < 1){
			throw new BizException(BizCode.FAILD_DELETE_EXCEPTION);
		}
		refreshCache();
		addLog(OperationType.DELETE.getOption(), BusinessUtils.CODE);
		return  true;
	}

}

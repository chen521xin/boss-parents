/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.Code;
import com.boss.core.pojo.CodePojo;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.user.Util.CodeLocalCache;
import com.boss.user.mapper.CodeMapper;
import com.boss.user.service.CodeService;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.enums.OperationType;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


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
	@Autowired
	private CodeLocalCache codeLocalCache;
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
	public Page<Code> queryCode(Page<Code> page ,CodePojo codePo){
		codeMapper.findParentCodeAll(page,codePo);
		List<Code> parentList = page.getResult();
		for(Code code : parentList){
			code.setCodeList(codeLocalCache.getByPid(code.getId()));
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
			List<Code> codeList = codeLocalCache.getByPid(keyes);
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
		Code code = codeLocalCache.getByPidAndValue(pid, value);
		return code;
	}
	
	/**
	 * 新增参数
	 * @param codePojo
	 * @return
	 */
	public boolean addCode(CodePojo codePojo) {
		Code code = new Code();
		code.setName(codePojo.getParentName());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		code.setId(uuid);
		code.setIndex(0);
		int num = codeMapper.addCode(code);
		if(num < 1){
			return false;
		}
		List<Code> list = codePojo.getCodeList();
		for(int i = 0;i<list.size();i++){
			code.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			code.setName(list.get(i).getName());
			code.setValue(list.get(i).getValue());
			code.setIndex(i+1);
			code.setPid(uuid);
			codeMapper.addCode(code);
			if(num < 1){
				return false;
			}
		}
		codeLocalCache.resetCacheJob();
		addLog(OperationType.ADD.getOption(), BusinessUtils.CODE);
		return  true;
	}

	/**
	 * 更新参数
	 * @param codePojo
	 * @return
	 */
	@Override
	public boolean updateCode(CodePojo codePojo) {
		Code parentCode = new Code();
		parentCode.setId(codePojo.getParentId());
		parentCode.setName(codePojo.getParentName());
		int num = codeMapper.updateCode(parentCode);
		if(num < 1){
			return false;
		}
		//数据库原来的 1 2 3 
		List<Code> codeList = codeLocalCache.getByPid(codePojo.getParentId());
		
		List<String> codeIdList = new ArrayList<String>();
		List<Code> list = codePojo.getCodeList();
		for(Code code : list){
			if(code.getId() == null){
				num = codeMapper.addCode(code);
				if(num < 1){
					return false;
				}
				continue;
			}
			codeIdList.add(code.getId());
			num = codeMapper.updateCode(code);
			if(num < 1){
				return false;
			}
		}
		
		for(Code code:codeList){
			if(!codeIdList.contains(code.getId())){
				num = codeMapper.deleteCode(code.getId());
				if(num<1){
					return false;
				}
			}
		}
		codeLocalCache.resetCacheJob();
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.CODE);
		return  true;
	}
	/**
	 * 删除参数
	 * @param id
	 */
	@Override
	public boolean deleteCode(String id) {
		int num = codeMapper.deleteCode(id);
		if(num < 1){
			return false;
		}
		codeLocalCache.resetCacheJob();
		addLog(OperationType.DELETE.getOption(), BusinessUtils.CODE);
		return  true;
	}

}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.boss.core.db.Code;
import com.boss.data.mapper.CodeMapper;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


@Component("codeLocalCache")
public class CodeLocalCache implements InitializingBean {

	@Autowired
	private CodeMapper codeMapper;
	
	/**
	 * 缓存数据
	 * @key: pid
	 * @value: code
	 */
	private volatile Map<String,List<Code>> cacheCodePid = null;
	
	/**
	 * 缓存数据
	 * @key: id
	 * @value: code
	 */
	private volatile Map<String,Code> cacheCode = null;
	/**
	 * @param key
	 * @return
	 */
	public Code getById(String key){
		if(StringUtils.isEmpty(key)){
			return new Code();
		}
		if(cacheCode == null || cacheCode.size() == 0){
			resetCacheData();
		}
		Code data = cacheCode.get(key);
		if(data == null){
			data = new Code();
		}
		return data;
	}
	/**
	 * @param key
	 * @return
	 */
	public List<Code> getByPid(String key){
		if(StringUtils.isEmpty(key)){
			return new ArrayList<Code>();
		}
		if(cacheCodePid == null){
			resetCacheData();
		}
		List<Code> codeList = cacheCodePid.get(key);
		if(codeList == null){
			codeList = new ArrayList<Code>();
		}
		return Collections.unmodifiableList(codeList);
	}
	/**
	 * 
	 * @param pid
	 * @param value
	 * @return
	 */
	public Code getByPidAndValue(String pid,String value){
		if(StringUtils.isEmpty(pid) || StringUtils.isEmpty(value)){
			return new Code();
		}
		if(cacheCodePid == null){
			resetCacheData();
		}
		List<Code> codeList = cacheCodePid.get(pid);
		if(codeList == null){
			return new Code();
		}
		for(Code c : codeList){
			if(!StringUtils.isEmpty(c.getValue()) && c.getValue().equals(value)){
				return c;
			}
		}
		return new Code();
	}
	/**
	 * 处理数据
	 */
	private synchronized void resetCacheData(){
		List<Code> codeList = codeMapper.findCodeAll();
		Map<String ,Code> tempCodeMap = new HashMap<String,Code>();
		Map<String,List<Code>> tempCacheByPid = new HashMap<String,List<Code>>();
		for(Code code : codeList){
			tempCodeMap.put(code.getId(), code);
			String key = null;
			Code data = new Code();
			key = code.getPid();
			data.setId(code.getId());
			data.setName(code.getName());
			data.setValue(code.getValue());
			data.setIndex(code.getIndex());
			data.setStep(code.getStep());
			if(!tempCacheByPid.containsKey(key)){
				tempCacheByPid.put(key, new ArrayList<Code>());
			}
			tempCacheByPid.get(key).add(data);
		}
		this.cacheCode = Collections.unmodifiableMap(tempCodeMap);
		this.cacheCodePid = Collections.unmodifiableMap(tempCacheByPid);
	}
	/**
	 * 添加TimerJob()可定时刷新
	 */
	public void resetCacheJob(){
		resetCacheData();
	}
	/**
	 * 系统初始化时执行
	 */
	@Override
	public void afterPropertiesSet() {
		resetCacheData();
	}

}

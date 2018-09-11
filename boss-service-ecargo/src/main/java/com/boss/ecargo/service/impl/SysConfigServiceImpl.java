/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.SysConfig;
import com.boss.ecargo.mapper.SysConfigMapper;
import com.boss.ecargo.service.SysConfigService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysConfigServiceImpl implements SysConfigService {
	
	@Autowired
	private SysConfigMapper sysConfigMapper;

	@Override
	public Map<String, SysConfig> findByBusinessType(String busType) {
		
		SysConfig config=new SysConfig();
		config.setPamBuzType(busType);
		List<SysConfig> list=sysConfigMapper.selectSysConfig(config);
		HashMap<String, SysConfig> result=new HashMap<String, SysConfig>();
		for(SysConfig o : list)
		{
			result.put(o.getPamCode(), o);
		}
		return result;
	}

	@Override
	public int updateSysConfig(SysConfig sysConfig) {
		return sysConfigMapper.updateSysConfig(sysConfig);
	}

}

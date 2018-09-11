/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.BalanceLsInfo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.ecargo.mapper.BalanceLsMapper;
import com.boss.ecargo.service.BalanceLsService;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.excel.ExcelUtils;

/**
 * @description
 * @data 2018年4月21日上午12:40:51
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BalanceLsServiceImpl extends BaseServiceImpl implements BalanceLsService {

	@Autowired
	private BalanceLsMapper balanceLsMapper;

	@Override
	public void insertBalanceLs(BalanceLsInfo balanceLs) {
		balanceLs.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		balanceLsMapper.insertBalanceLs(balanceLs);
	}

	@Override
	public Page<BalanceLsInfo> selectBalanceLs(Page<BalanceLsInfo> page, BalanceLsInfo ls) {
		balanceLsMapper.selectBalanceLs(page, ls);
		convertBalance(page.getResult());
		return page;
	}

	public void convertBalance(List<BalanceLsInfo> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		for (BalanceLsInfo ls : list) {
			ls.setDirectionOf(getCodeNameByPidAndValue(CommonUtils.LS_FSFX, ls.getDirectionOf()));
		}
	}

	@Override
	public void export(BalanceLsInfo ls, HttpServletRequest request, HttpServletResponse response) {
		List<BalanceLsInfo> list = balanceLsMapper.selectBalanceLs(ls);
		if (list == null || list.size() == 0) {
			throw new BizException(BizCode.EXPORT_FILE_FAILD);
		}
		convertBalance(list);
		List<String> title = getExcelTitle();
		List<List<String>> content = getExcelBody(list);

		String sheetName = BusinessUtils.BALANCELS;// 导出文件名
		try {
			ExcelUtils.exportData(sheetName, title, content, request, response);
		} catch (IOException e) {
			throw new BizException(BizCode.EXPORT_FAILD);
		}
	}

	/**
	 * 保单导出
	 */
	public List<List<String>> getExcelBody(List<BalanceLsInfo> list) {
		List<List<String>> excelBody = new ArrayList<>();
		List<String> excelContent = null;
		for (BalanceLsInfo queryInfo : list) {
			excelContent = new ArrayList<>();
			excelContent.add(queryInfo.getUpdateDate());
			excelContent.add(queryInfo.getCreateBy());
			excelContent.add(queryInfo.getModule());
			excelContent.add(queryInfo.getFse());
			excelContent.add(queryInfo.getDirectionOf());
			excelContent.add(queryInfo.getBeforeBalance());
			excelContent.add(queryInfo.getAfterBalance());
			excelBody.add(excelContent);
		}
		return excelBody;
	}

	public List<String> getExcelTitle() {
		List<String> title = new ArrayList<String>();
		title.add("日期");
		title.add("操作人");
		title.add("来源");
		title.add("金额");
		title.add("操作");
		title.add("操作前金额");
		title.add("操作后金额");
		return title;
	}

}

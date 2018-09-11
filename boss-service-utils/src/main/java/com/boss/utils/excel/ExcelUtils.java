/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.excel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boss.utils.DateFormatUtils;
import com.boss.utils.cons.CommonUtils;

/**
 * @description
 * @data 2018年5月9日下午4:11:58
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class ExcelUtils {

	public static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

	public static void exportData(String name, List<String> title, List<List<String>> data, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Workbook result = null;
		ServletOutputStream out = null;

		try {
			result = fillData(name, title, data);

			name = DateFormatUtils.DateFormatetoString(new Date(), CommonUtils.DATE_FORMATE_TOTAL_DEAL_NO);
			response.reset();
			response.setContentType("application/msexcel");

			response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
			out = response.getOutputStream();
			result.write(out);
		} finally {
			if (out != null) {
				out.close();
			}
			if (result != null) {
				result.close();
			}
		}
	}

	public static Workbook fillData(String name, List<String> title, List<List<String>> data) {
		Workbook result = new SXSSFWorkbook();
		Sheet sheet = result.createSheet(name);
		Row row = null;
		Cell cell = null;
		String cellData = null;
		if (title != null && title.size() > 0) {
			row = sheet.createRow(0);
			for (int i = 0; i < title.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(title.get(i));
			}
		}

		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				List<String> rowData = data.get(i);
				row = sheet.createRow(sheet.getPhysicalNumberOfRows());
				if (rowData != null && data.size() > 0) {
					for (int j = 0; j < rowData.size(); j++) {
						cell = row.createCell(j);
						cellData = rowData.get(j);
						if (cellData != null && !"null".equalsIgnoreCase(cellData)) {
							if (cellData.contains("\n")) {
								cell.setCellValue(new XSSFRichTextString(cellData));
							} else {
								cell.setCellValue(cellData);
							}
						}
					}
				}
			}
		}
		return result;
	}
}

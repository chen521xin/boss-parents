/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.feature.orm.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description
 * @data 2017年11月14日下午1:41:49
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class MySql5PageHepler {

	/**
	 * 得到查询总数的SQL
	 * 
	 * @param querySelect
	 * @return
	 */
	public static String getCountString(String querySelect) {
		querySelect = getLineSql(querySelect);
		int orderIndex = getLastOrderInsertPoint(querySelect);
		int formIndex = getAfterFormInserPoint(querySelect);
		String select = querySelect.substring(0, formIndex);
		if (select.toLowerCase().indexOf("select distinct") != -1
				|| querySelect.toLowerCase().indexOf("group by") != -1) {
			return new StringBuffer(querySelect.length()).append("select count(1) count from (")
					.append(querySelect.substring(0, orderIndex)).append(") t").toString();
		} else {
			return new StringBuffer(querySelect.length()).append("select count(1) count ")
					.append(querySelect.substring(formIndex, orderIndex)).toString();
		}
	}

	/**
	 * 得到最后一个order by的插入点
	 * 
	 * @param querySelect
	 * @return
	 */
	private static int getLastOrderInsertPoint(String querySelect) {
		int orderIndex = querySelect.toLowerCase().lastIndexOf("order by");
		if (orderIndex == -1) {
			orderIndex = querySelect.length();
		}
		if (!isBracketCanPartnershop(querySelect.substring(orderIndex, querySelect.length()))) {
			throw new IllegalArgumentException("My SQL 分页必须要有Order by语句");
		}
		return orderIndex;
	}

	/**
	 * 得到分页SQL
	 * 
	 * @param querySelect
	 * @param offset
	 *            偏移量
	 * @param limit
	 *            位置
	 * @return
	 */
	public static String getLimitString(String querySelect, int offset, int limit) {
		querySelect = getLineSql(querySelect);
		String sql = querySelect + " limit " + offset + " , " + limit;
		return sql;
	}

	/**
	 * 得到SQL第一个正确的FROM的插入点
	 * 
	 * @param querySelect
	 * @return
	 */
	public static int getAfterFormInserPoint(String querySelect) {
		String regex = "\\s+FROM\\s+";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(querySelect);
		while (matcher.find()) {
			int formStartIndex = matcher.start(0);
			String text = querySelect.substring(0, formStartIndex);
			if (isBracketCanPartnershop(text)) {
				return formStartIndex;
			}
		}
		return 0;
	}

	/**
	 * 将SQL语句变成一条语句，并且每个单词的间隔都是一个空格
	 * 
	 * @param querySelect
	 * @return
	 */
	public static String getLineSql(String querySelect) {
		return querySelect.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
	}

	/**
	 * 判断括号（）是否匹配，并不会判断排列顺序是否正确
	 * 
	 * @param text
	 * @return
	 */
	private static boolean isBracketCanPartnershop(String text) {
		if (text == null || (getIndexOfCount(text, '(') != getIndexOfCount(text, ')'))) {
			return false;
		}
		return true;
	}

	/**
	 * 得到一个字符在另一个字符床中出现的次数
	 * 
	 * @param text
	 * @param ch
	 * @return
	 */
	public static int getIndexOfCount(String text, char ch) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			count = (text.charAt(i) == ch) ? count + 1 : count;
		}
		return count;
	}
}

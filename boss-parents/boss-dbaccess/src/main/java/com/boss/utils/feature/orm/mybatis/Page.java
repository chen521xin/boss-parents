/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.utils.feature.orm.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * @description
 * @data 2017年10月24日下午6:48:33
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class Page<T> extends RowBounds {

	protected int pageNo = 1;
	protected int pageSize = 10;
	protected int offset;
	protected int limit;
	protected List<T> result = new ArrayList<T>();
	protected int totalCount;
	protected int totalPages;

	/**
	 * 计算偏移量
	 */
	private void calcOffset() {
		this.offset = ((pageNo - 1) * pageSize);
	}

	private void calcLimit() {
		this.limit = pageSize;
	}

	public Page() {
		this.calcOffset();
		this.calcLimit();
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.calcOffset();
		this.calcLimit();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPages = this.getTotalPages();
	}

	/**
	 * 根据pageSize与totalCount计算总页数，默认值为-1
	 * 
	 * @return
	 */
	public int getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}
		int pages = totalCount / pageSize;
		return totalCount % pageSize > 0 ? ++pages : pages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}

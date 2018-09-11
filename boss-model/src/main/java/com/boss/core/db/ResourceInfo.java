/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.db;

import java.io.Serializable;
import java.util.List;

/**
 * @description
 * @data 2018年4月26日下午6:37:18
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class ResourceInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5738273504777066260L;
	private Resource current;
	private List<Resource> children;
	public Resource getCurrent() {
		return current;
	}
	public void setCurrent(Resource current) {
		this.current = current;
	}
	public List<Resource> getChildren() {
		return children;
	}
	public void setChildren(List<Resource> children) {
		this.children = children;
	}
	
}

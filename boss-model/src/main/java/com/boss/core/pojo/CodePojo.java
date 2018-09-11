/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.pojo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.boss.core.db.BaseEntity;
import com.boss.core.db.Code;
/**
 * @description
 * @data 2018年3月3日下午10:06:42
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/


/**
 * 参数入参
 * @author Administrator
 *
 */
public class CodePojo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912270774175907817L;

	/**
	 * 父级id
	 */
	private String parentId;
	/**
	 * 父级参数名
	 */
	@NotNull(message = "父级参数名不能为空") 
	private String parentName;
	/**
	 * 子级参数
	 */
	private List<Code> codeList;
	public CodePojo() {
		super();
	}
	public CodePojo(String parentId,String parentName, List<Code> codeList) {
		super();
		this.parentId = parentId;
		this.parentName = parentName;
		this.codeList = codeList;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public List<Code> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<Code> codeList) {
		this.codeList = codeList;
	}
	@Override
	public String toString() {
		return "CodePojo [parentId=" + parentId + ", parentName=" + parentName + ", codeList=" + codeList + "]";
	}
	
	
}

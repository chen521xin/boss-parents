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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 字典实体
 *
 */
@ApiModel("参数信息")
public class Code extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", dataType = "java.lang.String", required = false)
	private String name;
	/**
	 * 值
	 */
	@ApiModelProperty(value = "值", dataType = "java.lang.String", required = false)
	private String value;
	/**
	 * 序号
	 */
	@ApiModelProperty(value = "序号", dataType = "java.lang.integer", required = false)
	private Integer index;
	/**
	 * 父id
	 */
	@ApiModelProperty(value = "父ID", dataType = "java.lang.String", required = false)
	private String pid;
	/**
	 * 步长
	 */
	@ApiModelProperty(value = "步长", dataType = "java.lang.String", required = false)
	private String step;

	private List<Code> codeList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public List<Code> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<Code> codeList) {
		this.codeList = codeList;
	}

	@Override
	public String toString() {
		return "Code [name=" + name + ", value=" + value + ", index=" + index + ", pid=" + pid + ", step=" + step
				+ ", codeList=" + codeList + "]";
	}

}

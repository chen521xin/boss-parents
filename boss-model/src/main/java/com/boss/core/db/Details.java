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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2018年4月1日上午11:36:57
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("起运输明细实体类")
public class Details extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1275048893129484643L;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", dataType = "java.lang.String", required = false)
	private String userName;
	/**
	 * 保险公司全称
	 */
	@ApiModelProperty(value = "保险公司全称", dataType = "java.lang.String", required = false)
	private String fullName;
	/**
	 * 保单id
	 */
	@ApiModelProperty(value = "保单Id", dataType = "java.lang.String", required = false)
	private String policyId;
	/**
	 * 保单号
	 */
	@ApiModelProperty(value = "policyNo", dataType = "java.lang.String", required = false)
	@Size(max=50,message="保单号字段字段长度超出限制，不能超过50位！")
	private String policyNo;
	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "custName", dataType = "java.lang.String", required = false)
	@Size(max=50,message="客户名称字段长度超出限制，不能超过50位！")
	private String custName;
	/**
	 * 协议书编号
	 */
	@ApiModelProperty(value = "transDealNo", dataType = "java.lang.String", required = false)
	@Size(max=50,message="协议书编号字长度超出限制，不能超过50位！")
	private String transDealNo;
	/**
	 * 运单号
	 */
	@ApiModelProperty(value = "caroddnum", dataType = "java.lang.String", required = false)
	@NotNull(message = "运单号不能为空！")
	@Size(max=50,message="运单号字段长度超出限制，不能超过50位！")
	private String caroddnum;
	/**
	 * 发货单位！
	 */
	@ApiModelProperty(value = "consignee", dataType = "java.lang.String", required = false)
	@NotNull(message = "发货单位！不能为空！")
	@Size(max=50,message="发货单位！字段长度超出限制，不能超过50位！")
	private String consignee;
	/**
	 * 发货单位！电话
	 */
	@ApiModelProperty(value = "cinsigneeMobile", dataType = "java.lang.String", required = false)
	@NotNull(message = "发货单位！电话不能为空！")
	@Size(max=50,message="发货单位！电话字段长度超出限制，不能超过50位！")
	private String consigneeMobile;
	/**
	 * 货物名称
	 */
	@ApiModelProperty(value = "goodsName", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物名称不能为空！")
	@Size(max=50,message="货物名称字段长度超出限制，不能超过50位！")
	private String goodsName;
	/**
	 * 货物数量
	 */
	@ApiModelProperty(value = "goodsTotalNum", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物数量不能为空！")
	@Size(max=50,message="货物数量字段长度超出限制，不能超过50位！")
	private String goodsTotalNum;
	/**
	 * 货物重量
	 */
	@ApiModelProperty(value = "goodsWeightNum", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物重量不能为空！")
	@Size(max=50,message="货物重量字段长度超出限制，不能超过50位！")
	private String goodsWeightNum;
	/**
	 * 参数id
	 */
	@ApiModelProperty(value = "pamid", dataType = "java.lang.String", required = false)
	@Size(max=32,message="保险参数字段长度超出限制，不能超过32位！")
	private String pamid;
	/**
	 * 本次投保使用产品参数名
	 */
	@ApiModelProperty(value = "selectDcbe", dataType = "java.lang.String", required = false)
	@NotNull(message = "本车保额不能为空！")
	private String selectDcbe;
	/**
	 * 系数(车次投保使用)
	 */
	@ApiModelProperty(value = "xishu", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "系数字段只能填入正数")	
	private String xishu;
	/**
	 * 本次投保使用额度
	 */
	@ApiModelProperty(value = "selectMoney", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "保额值字段只能填入正数")	
	private String selectMoney;
	/**
	 * 发车日期
	 */
	@ApiModelProperty(value = "startDate", dataType = "java.lang.String", required = false)
	private String startDate;
	/**
	 * 填单终止时间（接参）
	 */
	@ApiModelProperty(value = "endDate", dataType = "java.lang.String", required = false)
	private String endDate;
	/**
	 * 起运地
	 */
	@ApiModelProperty(value = "fazhan", dataType = "java.lang.String", required = false)
	@NotNull(message = "起运地不能为空！")
	@Size(max=50,message="起运地字段长度超出限制，不能超过50位！")
	private String fazhan;
	/**
	 * 目的地
	 */
	@ApiModelProperty(value = "goodsTo", dataType = "java.lang.String", required = false)
	@NotNull(message = "目的地不能为空！")
	@Size(max=50,message="目的地字段长度超出限制，不能超过50位！")
	private String goodsTo;
	/**
	 * 驾驶员
	 */
	@ApiModelProperty(value = "driver", dataType = "java.lang.String", required = false)
	@Size(max=50,message="目的地字段长度超出限制，不能超过50位！")
	private String driver;
	/**
	 * 司机电话
	 */
	@ApiModelProperty(value = "driverMobile", dataType = "java.lang.String", required = false)
	@Size(max=50,message="司机手机字长度超出限制，不能超过50位！")
	private String driverMobile;
	/**
	 * 驾驶证号
	 */
	@ApiModelProperty(value = "driverLicense", dataType = "java.lang.String", required = false)
	@Size(min = 18, max = 18, message = "驾驶证号必须为18位！")
	private String driverLicense;
	/**
	 * 收货单位电话
	 */
	@ApiModelProperty(value = "goodsToMobile", dataType = "java.lang.String", required = false)
	@Size(max=50,message="收货单位电话字段长度超出限制，不能超过50位！")
	private String goodsToMobile;
	/**
	 * 收货单位！
	 */
	@ApiModelProperty(value = "goodsOwner", dataType = "java.lang.String", required = false)
	@Size(max=50,message="收货单位！字段长度超出限制，不能超过50位！")
	private String goodsOwner;
	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "vehicleNo", dataType = "java.lang.String", required = false)
	@NotNull(message = "车牌号不能为空！")
	@Size(max=50,message="车牌号字段长度超出限制，不能超过50位！")
	private String vehicleNo;
	/**
	 * 挂车号
	 */
	@ApiModelProperty(value = "vehicleType", dataType = "java.lang.String", required = false)
	@NotNull(message = "挂车号不能为空！")
	@Size(max=50,message="挂车号字段长度超出限制，不能超过50位！")
	private String vehicleType;
	/**
	 * 凭证填写时间
	 */
	@ApiModelProperty(value = "tdate", dataType = "java.lang.String", required = false)
	private String tdate;
	/**
	 * 投保状态
	 */
	@ApiModelProperty(value = "state", dataType = "java.lang.String", required = false)
	private String state;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "space", dataType = "java.lang.String", required = false)
	@Size(max=1000,message="备注字段长度超出限制，不能超过50位！")
	private String space;
	/**
	 * 投保保单的余额
	 * 
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.String", required = false)
	//@NotNull(message = "投保保单的余额不能为空！")
	private String surplusCarNoMoney;
	/**
	 * 投保保单总额
	 * 
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.String", required = false)
	//@NotNull(message = "投保保单的总额不能为空！")
	private String policyTotalMoney;
	
	/**
	 * 保险公司标识 1:中国人寿  2：人民保险  3：国寿财
	 */
	private String isCompany;
	/**
	 * 投保类型1：车次投保，保额投保
	 */
	private String isInsured;
	/**
	 * 人保系数
	 */
	private String rbXishu;
	/**
	 * 人保投保结果
	 */
	private String insureResult;
	
	
	public String getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}

	public String getIsInsured() {
		return isInsured;
	}

	public void setIsInsured(String isInsured) {
		this.isInsured = isInsured;
	}

	public String getInsureResult() {
		return insureResult;
	}

	public void setInsureResult(String insureResult) {
		this.insureResult = insureResult;
	}

	public String getRbXishu() {
		return rbXishu;
	}

	public void setRbXishu(String rbXishu) {
		this.rbXishu = rbXishu;
	}

	public String getPolicyTotalMoney() {
		return policyTotalMoney;
	}

	public void setPolicyTotalMoney(String policyTotalMoney) {
		this.policyTotalMoney = policyTotalMoney;
	}

	public String getSurplusCarNoMoney() {
		return surplusCarNoMoney;
	}

	public void setSurplusCarNoMoney(String surplusCarNoMoney) {
		this.surplusCarNoMoney = surplusCarNoMoney;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getTransDealNo() {
		return transDealNo;
	}

	public void setTransDealNo(String transDealNo) {
		this.transDealNo = transDealNo;
	}

	public String getCaroddnum() {
		return caroddnum;
	}

	public void setCaroddnum(String caroddnum) {
		this.caroddnum = caroddnum;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsTotalNum() {
		return goodsTotalNum;
	}

	public void setGoodsTotalNum(String goodsTotalNum) {
		this.goodsTotalNum = goodsTotalNum;
	}

	public String getGoodsWeightNum() {
		return goodsWeightNum;
	}

	public void setGoodsWeightNum(String goodsWeightNum) {
		this.goodsWeightNum = goodsWeightNum;
	}

	public String getPamid() {
		return pamid;
	}

	public void setPamid(String pamid) {
		this.pamid = pamid;
	}

	public String getSelectDcbe() {
		return selectDcbe;
	}

	public void setSelectDcbe(String selectDcbe) {
		this.selectDcbe = selectDcbe;
	}

	public String getXishu() {
		return xishu;
	}

	public void setXishu(String xishu) {
		this.xishu = xishu;
	}

	public String getSelectMoney() {
		return selectMoney;
	}

	public void setSelectMoney(String selectMoney) {
		this.selectMoney = selectMoney;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFazhan() {
		return fazhan;
	}

	public void setFazhan(String fazhan) {
		this.fazhan = fazhan;
	}

	public String getGoodsTo() {
		return goodsTo;
	}

	public void setGoodsTo(String goodsTo) {
		this.goodsTo = goodsTo;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String getGoodsToMobile() {
		return goodsToMobile;
	}

	public void setGoodsToMobile(String goodsToMobile) {
		this.goodsToMobile = goodsToMobile;
	}

	public String getGoodsOwner() {
		return goodsOwner;
	}

	public void setGoodsOwner(String goodsOwner) {
		this.goodsOwner = goodsOwner;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	@Override
	public String toString() {
		return "Details [userName=" + userName + ", fullName=" + fullName + ", policyId=" + policyId + ", policyNo="
				+ policyNo + ", custName=" + custName + ", transDealNo=" + transDealNo + ", caroddnum=" + caroddnum
				+ ", consignee=" + consignee + ", consigneeMobile=" + consigneeMobile + ", goodsName=" + goodsName
				+ ", goodsTotalNum=" + goodsTotalNum + ", goodsWeightNum=" + goodsWeightNum + ", pamid=" + pamid
				+ ", selectDcbe=" + selectDcbe + ", xishu=" + xishu + ", selectMoney=" + selectMoney + ", startDate="
				+ startDate + ", endDate=" + endDate + ", fazhan=" + fazhan + ", goodsTo=" + goodsTo + ", driver="
				+ driver + ", driverMobile=" + driverMobile + ", driverLicense=" + driverLicense + ", goodsToMobile="
				+ goodsToMobile + ", goodsOwner=" + goodsOwner + ", vehicleNo=" + vehicleNo + ", vehicleType="
				+ vehicleType + ", tdate=" + tdate + ", state=" + state + ", space=" + space + ", surplusCarNoMoney="
				+ surplusCarNoMoney + ", policyTotalMoney=" + policyTotalMoney + "]";
	}
}

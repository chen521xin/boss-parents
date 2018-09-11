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

import io.swagger.annotations.ApiModelProperty;

/**
 * @description 
 * @data 2018年5月16日下午9:16:35
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public class DetailsUpdateInfo extends BaseEntity implements Serializable {

	/**
	 * 保单号字段字段长度超出限制，不能超过50位
	 */
	private static final long serialVersionUID = 3721196921801287848L;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", dataType = "java.lang.String", required = false)
	private String userNameUpd;
	/**
	 * 保险公司全称
	 */
	@ApiModelProperty(value = "保险公司全称", dataType = "java.lang.String", required = false)
	private String fullNameUpd;
	/**
	 * 保单id
	 */
	@ApiModelProperty(value = "保单Id", dataType = "java.lang.String", required = false)
	private String policyIdUpd;
	/**
	 * 保单号
	 */
	@ApiModelProperty(value = "policyNo", dataType = "java.lang.String", required = false)
	@Size(max=50,message="保单号字段字段长度超出限制，不能超过50位！")
	private String policyNoUpd;
	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "custName", dataType = "java.lang.String", required = false)
	@Size(max=50,message="客户名称字段长度超出限制，不能超过50位！")
	private String custNameUpd;
	/**
	 * 协议书编号
	 */
	@ApiModelProperty(value = "transDealNo", dataType = "java.lang.String", required = false)
	@Size(max=50,message="协议书编号字段长度超出限制，不能超过50位！")	
	private String transDealNoUpd;
	/**
	 * 运单号
	 */
	@ApiModelProperty(value = "caroddnum", dataType = "java.lang.String", required = false)
	@NotNull(message = "运单号不能为空！")
	@Size(max=50,message="运单号字段长度超出限制，不能超过50位！")
	private String caroddnumUpd;
	/**
	 * 发货单位
	 */
	@ApiModelProperty(value = "consignee", dataType = "java.lang.String", required = false)
	@NotNull(message = "发货单位不能为空！")
	@Size(max=50,message="发货单位字段长度超出限制，不能超过50位！")
	private String consigneeUpd;
	/**
	 * 发货单位电话
	 */
	@ApiModelProperty(value = "cinsigneeMobile", dataType = "java.lang.String", required = false)
	@NotNull(message = "发货单位电话不能为空！")
	@Size(max=50,message="发货单位电话字段长度超出限制，不能超过50位！")
	private String consigneeMobileUpd;
	/**
	 * 货物名称
	 */
	@ApiModelProperty(value = "goodsName", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物名称不能为空！")
	@Size(max=50,message="货物名称字段长度超出限制，不能超过50位！")
	private String goodsNameUpd;
	/**
	 * 货物数量
	 */
	@ApiModelProperty(value = "goodsTotalNum", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物数量不能为空！")
	@Size(max=50,message="货物数量字段长度超出限制，不能超过50位！")
	private String goodsTotalNumUpd;
	/**
	 * 货物重量
	 */
	@ApiModelProperty(value = "goodsWeightNum", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物重量不能为空！")
	@Size(max=50,message="货物重量字段长度超出限制，不能超过50位！")
	private String goodsWeightNumUpd;
	/**
	 * 参数id
	 */
	@ApiModelProperty(value = "pamid", dataType = "java.lang.String", required = false)
	@Size(max=32,message="保险参数字段长度超出限制，不能超过32位！")
	private String pamidUpd;
	/**
	 * 本次投保使用产品参数名
	 */
	@ApiModelProperty(value = "selectDcbe", dataType = "java.lang.String", required = false)
	private String selectDcbeUpd;
	/**
	 * 系数(车次投保使用)
	 */
	@ApiModelProperty(value = "xishu", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "系数字段只能填入正数！")	
	private String xishuUpd;
	/**
	 * 本次投保使用额度
	 */
	@ApiModelProperty(value = "selectMoney", dataType = "java.lang.String", required = false)
	@Pattern(regexp = "^[+]?\\d+(\\.\\d+)?$", message = "保额值字段只能填入正数！")	
	private String selectMoneyUpd;
	/**
	 * 发车日期
	 */
	@ApiModelProperty(value = "startDate", dataType = "java.lang.String", required = false)
	private String startDateUpd;
	/**
	 * 填单终止时间（接参）
	 */
	@ApiModelProperty(value = "endDate", dataType = "java.lang.String", required = false)
	private String endDateUpd;
	/**
	 * 起运地
	 */
	@ApiModelProperty(value = "fazhan", dataType = "java.lang.String", required = false)
	@NotNull(message = "起运地不能为空！")
	@Size(max=50,message="起运地字段长度超出限制，不能超过50位符！")
	private String fazhanUpd;
	/**
	 * 目的地
	 */
	@ApiModelProperty(value = "goodsTo", dataType = "java.lang.String", required = false)
	@NotNull(message = "目的地不能为空！")
	@Size(max=50,message="目的地字段长度超出限制，不能超过50位！")
	private String goodsToUpd;
	/**
	 * 驾驶员
	 */
	@ApiModelProperty(value = "driver", dataType = "java.lang.String", required = false)
	@NotNull(message = "驾驶员不能为空！")
	@Size(max=50,message="目的地字段长度超出限制，不能超过50位！")
	private String driverUpd;
	/**
	 * 司机电话
	 */
	@ApiModelProperty(value = "driverMobile", dataType = "java.lang.String", required = false)

	@Size(max=50,message="司机手机字段长度超出限制，不能超过50位！")
	private String driverMobileUpd;
	/**
	 * 驾驶证号
	 */
	@ApiModelProperty(value = "driverLicense", dataType = "java.lang.String", required = false)
	@NotNull(message = "驾驶证号不能为空！")
	@Size(min = 18 ,max = 18,message="驾驶证号字段必须输入18位！")
	private String driverLicenseUpd;
	/**
	 * 收货单位电话
	 */
	@ApiModelProperty(value = "goodsToMobile", dataType = "java.lang.String", required = false)
	@NotNull(message = "收货单位电话不能为空！")
	@Size(max=50,message="收货单位电话字段长度超出限制，不能超过50位！")
	private String goodsToMobileUpd;
	/**
	 * 收货单位
	 */
	@ApiModelProperty(value = "goodsOwner", dataType = "java.lang.String", required = false)
	@NotNull(message = "收货单位不能为空！")
	@Size(max=50,message="收货单位字段长度超出限制，不能超过50位！")
	private String goodsOwnerUpd;
	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "vehicleNo", dataType = "java.lang.String", required = false)
	@NotNull(message = "车牌号不能为空！")
	@Size(max=50,message="车牌号字段长度超出限制，不能超过50位！")
	private String vehicleNoUpd;
	/**
	 * 挂车号
	 */
	@ApiModelProperty(value = "vehicleType", dataType = "java.lang.String", required = false)
	@NotNull(message = "挂车号不能为空！")
	@Size(max=50,message="挂车号字段长度超出限制，不能超过50位！")
	private String vehicleTypeUpd;
	/**
	 * 凭证填写时间
	 */
	@ApiModelProperty(value = "tdate", dataType = "java.lang.String", required = false)
	private String tdateUpd;
	/**
	 * 投保状态
	 */
	@ApiModelProperty(value = "state", dataType = "java.lang.String", required = false)
	private String stateUpd;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "space", dataType = "java.lang.String", required = false)
	@Size(max=1000,message="备注字段长度超出限制，不能超过50位！")
	private String spaceUpd;
	/**
	 * 投保保单的余额
	 * 
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.String", required = false)
	private Double surplusCarNoMoneyUpd;
	/**
	 * 投保保单总额
	 * 
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.String", required = false)
	private Double policyTotalMoneyUpd;
	public String getUserNameUpd() {
		return userNameUpd;
	}
	public void setUserNameUpd(String userNameUpd) {
		this.userNameUpd = userNameUpd;
	}
	public String getFullNameUpd() {
		return fullNameUpd;
	}
	public void setFullNameUpd(String fullNameUpd) {
		this.fullNameUpd = fullNameUpd;
	}
	public String getPolicyIdUpd() {
		return policyIdUpd;
	}
	public void setPolicyIdUpd(String policyIdUpd) {
		this.policyIdUpd = policyIdUpd;
	}
	public String getPolicyNoUpd() {
		return policyNoUpd;
	}
	public void setPolicyNoUpd(String policyNoUpd) {
		this.policyNoUpd = policyNoUpd;
	}
	public String getCustNameUpd() {
		return custNameUpd;
	}
	public void setCustNameUpd(String custNameUpd) {
		this.custNameUpd = custNameUpd;
	}
	public String getTransDealNoUpd() {
		return transDealNoUpd;
	}
	public void setTransDealNoUpd(String transDealNoUpd) {
		this.transDealNoUpd = transDealNoUpd;
	}
	public String getCaroddnumUpd() {
		return caroddnumUpd;
	}
	public void setCaroddnumUpd(String caroddnumUpd) {
		this.caroddnumUpd = caroddnumUpd;
	}
	public String getConsigneeUpd() {
		return consigneeUpd;
	}
	public void setConsigneeUpd(String consigneeUpd) {
		this.consigneeUpd = consigneeUpd;
	}
	public String getConsigneeMobileUpd() {
		return consigneeMobileUpd;
	}
	public void setConsigneeMobileUpd(String consigneeMobileUpd) {
		this.consigneeMobileUpd = consigneeMobileUpd;
	}
	public String getGoodsNameUpd() {
		return goodsNameUpd;
	}
	public void setGoodsNameUpd(String goodsNameUpd) {
		this.goodsNameUpd = goodsNameUpd;
	}
	public String getGoodsTotalNumUpd() {
		return goodsTotalNumUpd;
	}
	public void setGoodsTotalNumUpd(String goodsTotalNumUpd) {
		this.goodsTotalNumUpd = goodsTotalNumUpd;
	}
	public String getGoodsWeightNumUpd() {
		return goodsWeightNumUpd;
	}
	public void setGoodsWeightNumUpd(String goodsWeightNumUpd) {
		this.goodsWeightNumUpd = goodsWeightNumUpd;
	}
	public String getPamidUpd() {
		return pamidUpd;
	}
	public void setPamidUpd(String pamidUpd) {
		this.pamidUpd = pamidUpd;
	}
	public String getSelectDcbeUpd() {
		return selectDcbeUpd;
	}
	public void setSelectDcbeUpd(String selectDcbeUpd) {
		this.selectDcbeUpd = selectDcbeUpd;
	}
	public String getXishuUpd() {
		return xishuUpd;
	}
	public void setXishuUpd(String xishuUpd) {
		this.xishuUpd = xishuUpd;
	}

	public String getStartDateUpd() {
		return startDateUpd;
	}
	public void setStartDateUpd(String startDateUpd) {
		this.startDateUpd = startDateUpd;
	}
	public String getEndDateUpd() {
		return endDateUpd;
	}
	public void setEndDateUpd(String endDateUpd) {
		this.endDateUpd = endDateUpd;
	}
	public String getFazhanUpd() {
		return fazhanUpd;
	}
	public void setFazhanUpd(String fazhanUpd) {
		this.fazhanUpd = fazhanUpd;
	}
	public String getGoodsToUpd() {
		return goodsToUpd;
	}
	public void setGoodsToUpd(String goodsToUpd) {
		this.goodsToUpd = goodsToUpd;
	}
	public String getDriverUpd() {
		return driverUpd;
	}
	public void setDriverUpd(String driverUpd) {
		this.driverUpd = driverUpd;
	}
	public String getDriverMobileUpd() {
		return driverMobileUpd;
	}
	public void setDriverMobileUpd(String driverMobileUpd) {
		this.driverMobileUpd = driverMobileUpd;
	}
	public String getDriverLicenseUpd() {
		return driverLicenseUpd;
	}
	public void setDriverLicenseUpd(String driverLicenseUpd) {
		this.driverLicenseUpd = driverLicenseUpd;
	}
	public String getGoodsToMobileUpd() {
		return goodsToMobileUpd;
	}
	public void setGoodsToMobileUpd(String goodsToMobileUpd) {
		this.goodsToMobileUpd = goodsToMobileUpd;
	}
	public String getGoodsOwnerUpd() {
		return goodsOwnerUpd;
	}
	public void setGoodsOwnerUpd(String goodsOwnerUpd) {
		this.goodsOwnerUpd = goodsOwnerUpd;
	}
	public String getVehicleNoUpd() {
		return vehicleNoUpd;
	}
	public void setVehicleNoUpd(String vehicleNoUpd) {
		this.vehicleNoUpd = vehicleNoUpd;
	}
	public String getVehicleTypeUpd() {
		return vehicleTypeUpd;
	}
	public void setVehicleTypeUpd(String vehicleTypeUpd) {
		this.vehicleTypeUpd = vehicleTypeUpd;
	}
	public String getTdateUpd() {
		return tdateUpd;
	}
	public void setTdateUpd(String tdateUpd) {
		this.tdateUpd = tdateUpd;
	}
	public String getStateUpd() {
		return stateUpd;
	}
	public void setStateUpd(String stateUpd) {
		this.stateUpd = stateUpd;
	}
	public String getSpaceUpd() {
		return spaceUpd;
	}
	public void setSpaceUpd(String spaceUpd) {
		this.spaceUpd = spaceUpd;
	}
	public Double getSurplusCarNoMoneyUpd() {
		return surplusCarNoMoneyUpd;
	}
	public void setSurplusCarNoMoneyUpd(Double surplusCarNoMoneyUpd) {
		this.surplusCarNoMoneyUpd = surplusCarNoMoneyUpd;
	}
	public Double getPolicyTotalMoneyUpd() {
		return policyTotalMoneyUpd;
	}
	public void setPolicyTotalMoneyUpd(Double policyTotalMoneyUpd) {
		this.policyTotalMoneyUpd = policyTotalMoneyUpd;
	}
	
	

}

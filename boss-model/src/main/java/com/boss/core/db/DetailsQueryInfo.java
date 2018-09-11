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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description 
 * @data 2018年5月16日下午8:47:28
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@ApiModel("起运输明细查询实体类")
public class DetailsQueryInfo extends BaseEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1770936692432532975L;
	/**
	 * userId
	 */
	private String userId;
	private String rowstateQuery;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", dataType = "java.lang.String", required = false)
	private String userNameQuery;
	/**
	 * 保险公司全称
	 */
	@ApiModelProperty(value = "保险公司全称", dataType = "java.lang.String", required = false)
	private String fullNameQuery;
	/**
	 * 保单id
	 */
	@ApiModelProperty(value = "保单Id", dataType = "java.lang.String", required = false)
	private String policyIdQuery;
	/**
	 * 保险公司id
	 */
	@ApiModelProperty(value = "保险公司id", dataType = "java.lang.String", required = false)
	private String insurcompId;
	/**
	 * 保单号
	 */
	@ApiModelProperty(value = "policyNo", dataType = "java.lang.String", required = false)
	private String policyNoQuery;
	/**
	 * 客户名称
	 */
	@ApiModelProperty(value = "custName", dataType = "java.lang.String", required = false)
	private String custNameQuery;
	/**
	 * 协议书编号
	 */
	@ApiModelProperty(value = "transDealNo", dataType = "java.lang.String", required = false)
	private String transDealNoQuery;
	/**
	 * 运单号
	 */
	@ApiModelProperty(value = "caroddnum", dataType = "java.lang.String", required = false)
	@NotNull(message = "运单号不能为空")
	private String caroddnumQuery;
	/**
	 * 发货单位
	 */
	@ApiModelProperty(value = "consignee", dataType = "java.lang.String", required = false)
	@NotNull(message = "发货单位不能为空")
	private String consigneeQuery;
	/**
	 * 发货单位电话
	 */
	@ApiModelProperty(value = "cinsigneeMobile", dataType = "java.lang.String", required = false)
	@NotNull(message = "发货单位电话不能为空")
	private String consigneeMobileQuery;
	/**
	 * 货物名称
	 */
	@ApiModelProperty(value = "goodsName", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物名称不能为空")
	private String goodsNameQuery;
	/**
	 * 货物数量
	 */
	@ApiModelProperty(value = "goodsTotalNum", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物数量不能为空")
	private String goodsTotalNumQuery;
	/**
	 * 货物重量
	 */
	@ApiModelProperty(value = "goodsWeightNum", dataType = "java.lang.String", required = false)
	@NotNull(message = "货物重量不能为空")
	private String goodsWeightNumQuery;
	/**
	 * 参数id
	 */
	@ApiModelProperty(value = "pamid", dataType = "java.lang.String", required = false)
	private String pamidQuery;
	/**
	 * 本次投保使用产品参数名
	 */
	@ApiModelProperty(value = "selectDcbe", dataType = "java.lang.String", required = false)
	private String selectDcbeQuery;
	/**
	 * 系数(车次投保使用)
	 */
	@ApiModelProperty(value = "xishu", dataType = "java.lang.String", required = false)
	private String xishuQuery;
	/**
	 * 本次投保使用额度
	 */
	@ApiModelProperty(value = "selectMoney", dataType = "java.lang.String", required = false)
	private Double selectMoneyQuery;
	/**
	 * 发车日期
	 */
	@ApiModelProperty(value = "startDate", dataType = "java.lang.String", required = false)
	private String startDateQuery;
	/**
	 * 填单终止时间（接参）
	 */
	@ApiModelProperty(value = "endDate", dataType = "java.lang.String", required = false)
	private String endDateQuery;
	/**
	 * 起运地
	 */
	@ApiModelProperty(value = "fazhan", dataType = "java.lang.String", required = false)
	@NotNull(message = "起运地不能为空")
	private String fazhanQuery;
	/**
	 * 目的地
	 */
	@ApiModelProperty(value = "goodsTo", dataType = "java.lang.String", required = false)
	@NotNull(message = "目的地不能为空")
	private String goodsToQuery;
	/**
	 * 驾驶员
	 */
	@ApiModelProperty(value = "driver", dataType = "java.lang.String", required = false)
	@NotNull(message = "驾驶员不能为空")
	private String driverQuery;
	/**
	 * 司机电话
	 */
	@ApiModelProperty(value = "driverMobile", dataType = "java.lang.String", required = false)
	@NotNull(message = "司机电话不能为空")
	private String driverMobileQuery;
	/**
	 * 驾驶证号
	 */
	@ApiModelProperty(value = "driverLicense", dataType = "java.lang.String", required = false)
	@NotNull(message = "驾驶证号不能为空")
	@Size(min = 18 ,max = 18,message="字符长度最短18位最长18位")
	private String driverLicenseQuery;
	/**
	 * 收货单位电话
	 */
	@ApiModelProperty(value = "goodsToMobile", dataType = "java.lang.String", required = false)
	@NotNull(message = "收货单位电话不能为空")
	private String goodsToMobileQuery;
	/**
	 * 收货单位
	 */
	@ApiModelProperty(value = "goodsOwner", dataType = "java.lang.String", required = false)
	@NotNull(message = "收货单位不能为空")
	private String goodsOwnerQuery;
	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "vehicleNo", dataType = "java.lang.String", required = false)
	@NotNull(message = "车牌号不能为空")
	private String vehicleNoQuery;
	/**
	 * 挂车号
	 */
	@ApiModelProperty(value = "vehicleType", dataType = "java.lang.String", required = false)
	@NotNull(message = "挂车号不能为空")
	private String vehicleTypeQuery;
	/**
	 * 凭证填写时间
	 */
	@ApiModelProperty(value = "tdate", dataType = "java.lang.String", required = false)
	private String tDateQuery;
	/**
	 * 投保状态
	 */
	@ApiModelProperty(value = "state", dataType = "java.lang.String", required = false)
	private String stateQuery;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "space", dataType = "java.lang.String", required = false)
	private String spaceQuery;
	/**
	 * 投保保单的余额
	 * 
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.String", required = false)
	@NotNull(message = "投保保单的余额不能为空")
	private Double surplusCarNoMoneyQuery;
	/**
	 * 投保保单总额
	 * 
	 */
	@ApiModelProperty(value = "surplusCarNoMoney", dataType = "java.lang.String", required = false)
	@NotNull(message = "投保保单的总额不能为空")
	private Double policyTotalMoneyQuery;
	
	
	
	public String getRowstateQuery() {
		return rowstateQuery;
	}
	public void setRowstateQuery(String rowstateQuery) {
		this.rowstateQuery = rowstateQuery;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInsurcompId() {
		return insurcompId;
	}
	public void setInsurcompId(String insurcompId) {
		this.insurcompId = insurcompId;
	}
	public String getUserNameQuery() {
		return userNameQuery;
	}
	public void setUserNameQuery(String userNameQuery) {
		this.userNameQuery = userNameQuery;
	}
	public String getFullNameQuery() {
		return fullNameQuery;
	}
	public void setFullNameQuery(String fullNameQuery) {
		this.fullNameQuery = fullNameQuery;
	}
	public String getPolicyIdQuery() {
		return policyIdQuery;
	}
	public void setPolicyIdQuery(String policyIdQuery) {
		this.policyIdQuery = policyIdQuery;
	}
	public String getPolicyNoQuery() {
		return policyNoQuery;
	}
	public void setPolicyNoQuery(String policyNoQuery) {
		this.policyNoQuery = policyNoQuery;
	}
	public String getCustNameQuery() {
		return custNameQuery;
	}
	public void setCustNameQuery(String custNameQuery) {
		this.custNameQuery = custNameQuery;
	}
	public String getTransDealNoQuery() {
		return transDealNoQuery;
	}
	public void setTransDealNoQuery(String transDealNoQuery) {
		this.transDealNoQuery = transDealNoQuery;
	}
	public String getCaroddnumQuery() {
		return caroddnumQuery;
	}
	public void setCaroddnumQuery(String caroddnumQuery) {
		this.caroddnumQuery = caroddnumQuery;
	}
	public String getConsigneeQuery() {
		return consigneeQuery;
	}
	public void setConsigneeQuery(String consigneeQuery) {
		this.consigneeQuery = consigneeQuery;
	}
	public String getConsigneeMobileQuery() {
		return consigneeMobileQuery;
	}
	public void setConsigneeMobileQuery(String consigneeMobileQuery) {
		this.consigneeMobileQuery = consigneeMobileQuery;
	}
	public String getGoodsNameQuery() {
		return goodsNameQuery;
	}
	public void setGoodsNameQuery(String goodsNameQuery) {
		this.goodsNameQuery = goodsNameQuery;
	}
	public String getGoodsTotalNumQuery() {
		return goodsTotalNumQuery;
	}
	public void setGoodsTotalNumQuery(String goodsTotalNumQuery) {
		this.goodsTotalNumQuery = goodsTotalNumQuery;
	}
	public String getGoodsWeightNumQuery() {
		return goodsWeightNumQuery;
	}
	public void setGoodsWeightNumQuery(String goodsWeightNumQuery) {
		this.goodsWeightNumQuery = goodsWeightNumQuery;
	}
	public String getPamidQuery() {
		return pamidQuery;
	}
	public void setPamidQuery(String pamidQuery) {
		this.pamidQuery = pamidQuery;
	}
	public String getSelectDcbeQuery() {
		return selectDcbeQuery;
	}
	public void setSelectDcbeQuery(String selectDcbeQuery) {
		this.selectDcbeQuery = selectDcbeQuery;
	}
	public String getXishuQuery() {
		return xishuQuery;
	}
	public void setXishuQuery(String xishuQuery) {
		this.xishuQuery = xishuQuery;
	}
	public Double getSelectMoneyQuery() {
		return selectMoneyQuery;
	}
	public void setSelectMoneyQuery(Double selectMoneyQuery) {
		this.selectMoneyQuery = selectMoneyQuery;
	}
	public String getStartDateQuery() {
		return startDateQuery;
	}
	public void setStartDateQuery(String startDateQuery) {
		this.startDateQuery = startDateQuery;
	}
	public String getEndDateQuery() {
		return endDateQuery;
	}
	public void setEndDateQuery(String endDateQuery) {
		this.endDateQuery = endDateQuery;
	}
	public String getFazhanQuery() {
		return fazhanQuery;
	}
	public void setFazhanQuery(String fazhanQuery) {
		this.fazhanQuery = fazhanQuery;
	}
	public String getGoodsToQuery() {
		return goodsToQuery;
	}
	public void setGoodsToQuery(String goodsToQuery) {
		this.goodsToQuery = goodsToQuery;
	}
	public String getDriverQuery() {
		return driverQuery;
	}
	public void setDriverQuery(String driverQuery) {
		this.driverQuery = driverQuery;
	}
	public String getDriverMobileQuery() {
		return driverMobileQuery;
	}
	public void setDriverMobileQuery(String driverMobileQuery) {
		this.driverMobileQuery = driverMobileQuery;
	}
	public String getDriverLicenseQuery() {
		return driverLicenseQuery;
	}
	public void setDriverLicenseQuery(String driverLicenseQuery) {
		this.driverLicenseQuery = driverLicenseQuery;
	}
	public String getGoodsToMobileQuery() {
		return goodsToMobileQuery;
	}
	public void setGoodsToMobileQuery(String goodsToMobileQuery) {
		this.goodsToMobileQuery = goodsToMobileQuery;
	}
	public String getGoodsOwnerQuery() {
		return goodsOwnerQuery;
	}
	public void setGoodsOwnerQuery(String goodsOwnerQuery) {
		this.goodsOwnerQuery = goodsOwnerQuery;
	}
	public String getVehicleNoQuery() {
		return vehicleNoQuery;
	}
	public void setVehicleNoQuery(String vehicleNoQuery) {
		this.vehicleNoQuery = vehicleNoQuery;
	}
	public String getVehicleTypeQuery() {
		return vehicleTypeQuery;
	}
	public void setVehicleTypeQuery(String vehicleTypeQuery) {
		this.vehicleTypeQuery = vehicleTypeQuery;
	}
	
	public String gettDateQuery() {
		return tDateQuery;
	}
	public void settDateQuery(String tDateQuery) {
		this.tDateQuery = tDateQuery;
	}
	public String getStateQuery() {
		return stateQuery;
	}
	public void setStateQuery(String stateQuery) {
		this.stateQuery = stateQuery;
	}
	public String getSpaceQuery() {
		return spaceQuery;
	}
	public void setSpaceQuery(String spaceQuery) {
		this.spaceQuery = spaceQuery;
	}
	public Double getSurplusCarNoMoneyQuery() {
		return surplusCarNoMoneyQuery;
	}
	public void setSurplusCarNoMoneyQuery(Double surplusCarNoMoneyQuery) {
		this.surplusCarNoMoneyQuery = surplusCarNoMoneyQuery;
	}
	public Double getPolicyTotalMoneyQuery() {
		return policyTotalMoneyQuery;
	}
	public void setPolicyTotalMoneyQuery(Double policyTotalMoneyQuery) {
		this.policyTotalMoneyQuery = policyTotalMoneyQuery;
	}
	
}

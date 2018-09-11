/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

import com.boss.core.db.Details;
import com.boss.core.db.DetailsQueryInfo;
import com.boss.core.db.DetailsUpdateInfo;
import com.boss.core.db.MailInfo;
import com.boss.core.db.Policy;
import com.boss.core.db.User;
import com.boss.core.pojo.PolicyPojo;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.data.email.MailTools;
import com.boss.data.mapper.DetailsMapper;
import com.boss.data.service.DetailsService;
import com.boss.data.service.PolicyService;
import com.boss.data.util.MathDbTools;
import com.boss.data.util.XmlTools;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.utils.DateFormatUtils;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.ExportExcel;
import com.boss.utils.enums.OperationType;
import com.boss.utils.excel.ExcelUtils;

/**
 * @description
 * @data 2018年4月2日下午7:15:59
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DetailsServiceImpl extends BaseServiceImpl implements DetailsService {

	@Autowired
	private DetailsMapper detailsMapper;
	@Autowired
	private PolicyService policyService;

	@Value("${email.account}")
	private String emailAccount;

	@Value("${email.password}")
	private String emailPassword;

	@Value("${email.smtpHost}")
	private String smtpHost;

	@Value("${email.sendPort}")
	private int sendPort;

	@Value("${email.ifAuth}")
	private boolean isAuth;

	@Value("${email.debug}")
	private boolean debug;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param details
	 * @return
	 */
	public Page<DetailsQueryInfo> findByPage(Page<DetailsQueryInfo> page, DetailsQueryInfo details) {
		switch (getUserRole()) {
		case CommonUtils.ROLE_ADMIN:
			detailsMapper.findByPage(page, details);
			break;
		case CommonUtils.ROLE_INSURANCE_MAN:
			details.setInsurcompId(getInsurcompId());
			detailsMapper.findByInsurcompId(page, details);
			break;
		case CommonUtils.ROLE_PROXY:
			details.setUserId(getUserId());
			detailsMapper.findByPoxy(page, details);
			break;
		case CommonUtils.ROLE_USER:
			details.setUserId(getUserId());
			detailsMapper.findByUsers(page, details);
			break;
		default:
			throw new BizException(BizCode.THERE_IS_NO_SOUECH_TYPE);
		}
		return page;
	}

	/**
	 * 根据id查询起运书明细信息
	 * 
	 * @param id
	 * @reurn
	 */
	public Details findById(String id) {
		Details details = detailsMapper.findById(id);
		return details;
	}

	/**
	 * 新增起运书明细
	 * 
	 * @param details
	 * @return
	 */
	public boolean insertDetails(Details details) {
		details.setStartDate(DateFormatUtils.DateFormate());
		List<User> userList = null;
		Policy policy = policyService.getPolicyByPolicyId(details.getPolicyId());
		if (details.getIsInsured().equals("2")) {
			details.setRbXishu(policy.getRenBaoXiShu());
			PolicyPojo policyPojo = new PolicyPojo();
			policyPojo.setFlag(2);
			policyPojo.setPolicyNo(details.getPolicyNo());
			policyPojo.setSurplusCarNoMoney(policy.getSurplusCarNoMoney());
			policyPojo.setAccrual(details.getSelectMoney());
			policyPojo.setDirectionOf(CommonUtils.DIRECTION_OF_REDUCE);
			policyPojo.setPolicyTotalMoney(policy.getPolicyTotalMoney());
			details.setTdate(DateFormatUtils.DateFormate());
			policyPojo.setLastTdate(details.getTdate());
			policyPojo.setState(policy.getState());
			policyPojo.setRowstate(Integer.parseInt(policy.getState()));
			policyService.addBalance(policyPojo);
			details.changeStatus(CommonUtils.METHOD_ADD, getUserName());

			if (details.getIsCompany().equals("2")) {
				//人保发送xml
				try {
					if (details.getTdate() == null) {
						details.setTdate(DateFormatUtils.DateFormates());
					}
					Document jdtDom = XmlTools.objectToXmlDocument(details);
					String xmlStr = XmlTools.toStringFromXmlDoc(jdtDom, CommonUtils.CODE_GBK);
					InputStreamReader xmlInputStreamReader = XmlTools.xmlStringToInputStreamReader(
							MathDbTools.strConvertEncode(xmlStr, CommonUtils.CODE_GBK), CommonUtils.CODE_GBK);
					String backXml = XmlTools.sendAndReceiveSSL("https://comms.picczj.com/baoshangservlet",
							xmlInputStreamReader, "GBK");
					Document backDom = XmlTools.strToDocument(backXml);
					String backMsg = XmlTools.getTextContentByTagAndIndex(backDom, "ErrorCode", 0) + "_"
							+ XmlTools.getTextContentByTagAndIndex(backDom, "ErrorMsg", 0);
					details.setInsureResult(backMsg);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BizException(BizCode.INSURE_AGAINST_FAILURE_RENBAO_XML);
				}

			} else if (details.getIsCompany().equals("1")) {
				// 中国人寿发送邮件
				userList = getUserMail(details, policy);
				sendMail(userList, policy, details);
			}
			this.num = detailsMapper.insertDetails(details);
			if (num < 1) {
				throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
			}
			// 人保投保发送
			addLog(OperationType.ADD.getOption(), BusinessUtils.DETAILS);
		} else if (details.getIsInsured().equals("1")) {
			PolicyPojo policyPojo = new PolicyPojo();
			policyPojo.setFlag(3);
			policyPojo.setPolicyNo(details.getPolicyNo());
			policyPojo.setSurplusCarNo(policy.getSurplusCarNo());// 剩余车次

			policyPojo.setInTrain(details.getXishu());// 发车系数

			policyPojo.setDirectionOf(CommonUtils.DIRECTION_OF_REDUCE);// 流水方向
			details.setTdate(DateFormatUtils.DateFormate());
			policyPojo.setLastTdate(details.getTdate());
			policyPojo.setState(policy.getState());
			policyPojo.setRowstate(Integer.parseInt(policy.getState()));
			policyService.addBalance(policyPojo);
			details.changeStatus(CommonUtils.METHOD_ADD, getUserName());
			this.num = detailsMapper.insertDetails(details);
			if (num < 1) {
				throw new BizException(BizCode.FAILD_ADD_EXCEPTION);
			}
			addLog(OperationType.ADD.getOption(), BusinessUtils.DETAILS);
			userList = getUserMail(details, policy);
			sendMail(userList, policy, details);
		}
		return true;
	}

	/**
	 * 发送邮件
	 * 
	 * @param details
	 * @
	 */

	private List<User> getUserMail(Details details, Policy policy) {
		List<User> userList = new ArrayList<User>();
		if (!details.getIsCompany().equals("1")) {
			return userList;
		}

		// 管理员
		List<User> adminUser = policyService.findUserByRoleName();
		if (adminUser == null || adminUser.size() == 0) {
			throw new BizException(BizCode.NO_ADMIN_EMAIL);
		}
		userList.addAll(adminUser);
		// 用户
		User user = policyService.findUserByUserId(policy.getUserId());
		if (user == null) {
			throw new BizException(BizCode.NO_USER_EMAIL);
		}
		userList.add(user);
		// 保险员
		List<User> insUser = policyService.findUserByPolicyId(policy.getId());
		if (insUser == null || insUser.size() == 0) {
			throw new BizException(BizCode.NO_INSURCOMP_EMAIL);
		}
		userList.addAll(insUser);

		return userList;
	}

	/**
	 * 更新起运书明细
	 * 
	 * @param details
	 * @return
	 */
	public boolean updateDetails(DetailsUpdateInfo details) {
		details.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		this.num = detailsMapper.updateDetails(details);
		if (num < 1) {
			throw new BizException(BizCode.FAILD_UPDATE_EXCEPTION);
		}
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.DETAILS);
		return true;
	}

	/**
	 * 逻辑删除起运书明细
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteDetails(String id) {
		this.num = detailsMapper.deleteDetails(id);
		if (num < 1) {
			throw new BizException(BizCode.FAILD_DELETE_EXCEPTION);
		}
		addLog(OperationType.DELETE.getOption(), BusinessUtils.DETAILS);
		return true;
	}

	@Override
	public void exportDetails(Page<DetailsQueryInfo> page, DetailsQueryInfo details, HttpServletRequest request,
			HttpServletResponse response) {

		switch (getUserRole()) {
		case CommonUtils.ROLE_ADMIN:
			detailsMapper.findByPage(page, details);
			break;
		case CommonUtils.ROLE_INSURANCE_MAN:
			details.setInsurcompId(getInsurcompId());
			detailsMapper.findByInsurcompId(page, details);
			break;
		case CommonUtils.ROLE_PROXY:
			details.setUserId(getUserId());
			detailsMapper.findByPoxy(page, details);
			break;
		default:
			throw new BizException(BizCode.THERE_IS_NO_SOUECH_TYPE);
		}
		if (page.getResult() == null || page.getResult().size() == 0) {
			throw new BizException(BizCode.EXPORT_FILE_FAILD);
		}
		List<String> title = excelCache.getById(ExportExcel.DETAILS_EXPORT_DETAIL_ID);
		List<List<String>> content = getExcelBody(page.getResult());

		String sheetName = BusinessUtils.DETAILS;// 导出文件名
		try {
			ExcelUtils.exportData(sheetName, title, content, request, response);
		} catch (IOException e) {
			throw new BizException(BizCode.EXPORT_FAILD);
		}

	}

	public List<List<String>> getExcelBody(List<DetailsQueryInfo> List) {
		List<List<String>> excelBody = new ArrayList<>();
		List<String> excelContent = null;
		for (DetailsQueryInfo queryInfo : List) {
			excelContent = new ArrayList<>();
			excelContent.add(queryInfo.getPolicyNoQuery());
			excelContent.add(queryInfo.getTransDealNoQuery());
			excelContent.add(queryInfo.getCustNameQuery());
			excelContent.add(queryInfo.gettDateQuery());
			excelContent.add(queryInfo.getSelectDcbeQuery());
			excelContent.add(queryInfo.getGoodsOwnerQuery());
			excelContent.add(queryInfo.getGoodsToMobileQuery());
			excelContent.add(queryInfo.getGoodsNameQuery());
			excelContent.add(queryInfo.getDriverQuery());
			excelContent.add(queryInfo.getGoodsWeightNumQuery());
			excelContent.add(queryInfo.getGoodsTotalNumQuery());
			excelContent.add(queryInfo.getDriverLicenseQuery());
			excelContent.add(queryInfo.getStartDateQuery());
			excelContent.add(queryInfo.getTransDealNoQuery());
			excelContent.add(queryInfo.getDriverMobileQuery());
			excelContent.add(queryInfo.getFazhanQuery());
			excelContent.add(queryInfo.getGoodsToQuery());
			excelContent.add(queryInfo.getVehicleNoQuery());
			excelContent.add(queryInfo.getVehicleTypeQuery());
			excelBody.add(excelContent);
		}
		return excelBody;
	}

	/**
	 * 邮件
	 * 
	 * @param userList
	 * @param policy
	 * @param details
	 */
	public void sendMail(List<User> userList, Policy policy, Details details) {
		if (userList.size() != 0) {
			String title = "[" + getCodeNameByPidAndValue(CommonUtils.POLICY_TYPE, details.getIsInsured()) + "];"
					+ DateFormatUtils.DateFormate() + ";" + policy.getCustName() + ";" + policy.getPolicyNo() + ";"
					+ details.getGoodsName() + ";" + details.getGoodsTotalNum() + ";" + details.getGoodsWeightNum()
					+ ";" + details.getSelectDcbe() + ";" + DateFormatUtils.DateFormatString(details.getStartDate())
					+ ";" + details.getCaroddnum() + ";" + details.getFazhan() + ";" + details.getGoodsTo() + ";"
					+ details.getVehicleNo() + ";" + details.getVehicleType() + ";" + policy.getRenBaoXiShu();

			MailInfo mail = getMail();
			MailTools.sendMail(userList, title, "起运书明细", true, mail);

		} else {
			throw new BizException(BizCode.INSURE_AGAINST_FAILURE_MAIL);
		}
	}

	public MailInfo getMail() {
		MailInfo mail = new MailInfo();
		mail.setEmailAccount(emailAccount);
		mail.setDebug(debug);
		mail.setSendPort(sendPort);
		mail.setSmtpHost(smtpHost);
		mail.setEmailPassword(emailPassword);
		return mail;
	}
}

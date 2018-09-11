/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.service.imple;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boss.core.db.BalanceInfo;
import com.boss.core.db.Role;
import com.boss.core.db.User;
import com.boss.core.db.UserRateInfo;
import com.boss.core.db.UserRole;
import com.boss.core.struct.ResultPage;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.user.mapper.UserMapper;
import com.boss.user.service.RoleService;
import com.boss.user.service.UserRoleService;
import com.boss.user.service.UserService;
import com.boss.utils.DateFormatUtils;
import com.boss.utils.Md5Util;
import com.boss.utils.StringUtil;
import com.boss.utils.cons.BusinessUtils;
import com.boss.utils.cons.CommonUtils;
import com.boss.utils.enums.OperationType;

/**
 * @description
 * @data 2018年2月3日下午2:06:35
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BalanceServiceImpl balanceService;

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRateServerImpl userRateService;
	@Value("${user.pwdErrors.times}")
	private Integer pwdErrors;

	@Value("${user.reset.password}")
	private String resetPwd;

	/**
	 * 验证登录用户是否删除或冻结
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public User findUserByUserName(String username) {
		User user = new User();
		user.setUsername(username);
		user.setRowstate(CommonUtils.ROWSTATE_FREEZE);
		User findUser = userMapper.findUserByUserNameAndStatus(user);
		if (findUser == null) {
			throw new BizException(BizCode.USER_IS_NOT_EXISTS_ERROR);
		}
		Role listRole = roleService.findRoleByUsername(username);
		if (listRole == null) {
			throw new BizException(BizCode.USER_ROLE_EXCEPTION);
		}
		findUser.setRole(listRole);
		return findUser;
	}

	/**
	 * 验证用户名和密码是否一致
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean validateUserNameAndPassword(User user) {
		return userMapper.validateUserNameAndPassword(user) == 1;
	}

	/**
	 * 登录验证逻辑
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Override
	public ResultPage validateUser(User user) {

		if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
			User findUser = findUserByUserName(user.getUsername());

			if (findUser == null || findUser.getStatus().equals(CommonUtils.USER_STATUS_DELETE)
					|| findUser.getStatus().equals(CommonUtils.USER_STATUS_FREEZE)) {
				logger.error(String.format("%s,用户不存在活已冻结", user.getUsername()));
				return ResultPage.error(String.format("%s,用户不存在活已冻结", user.getUsername()));
			}
			// 验证当前用户是否属于锁定状态
			if (findUser.getStatus().equals(CommonUtils.USER_STATUS_LOCK)) {
				// 若当前用户为锁定状态，但是当前时间距离锁定时间未超过一小时，则不允许登录
				try {
					Date beforAnHour = DateFormatUtils.BeforAnHour();
					Date currentDate = DateFormatUtils.DateFormate(findUser.getUserLockTime(),
							CommonUtils.DATE_FORMATE_YYYY_MM_DD);
					if (beforAnHour.before(currentDate)) {
						logger.error(String.format("%s,用户已锁定", user.getUsername()));
						return ResultPage.error(String.format("%s,用户已锁定,请于%s分钟后重试", user.getUsername(),
								(currentDate.getTime() - beforAnHour.getTime()) / (60 * 1000)));
					} else {
						resetLoginInFailErrors(findUser);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

			if (!validateUserNameAndPassword(user)) {
				// 记录密码错误次数
				User errorUser = recordLoginInFailErros(findUser);
				int sycs = pwdErrors - errorUser.getCountPwdErrors();
				if (sycs <= 0) {
					return ResultPage.error("用户已锁定，请于一小时后再次进行登录");
				} else {
					return ResultPage.error(
							String.format("%s%s%s", "用户名密码错误,还剩", (pwdErrors - errorUser.getCountPwdErrors()), "次机会"));
				}

			}
			// 登录成功锁定次数与锁定时间重新置零
			if (findUser.getCountPwdErrors() > 0) {
				resetLoginInFailErrors(findUser);
			}

			return ResultPage.success(findUser);
		}

		return ResultPage.error("用户名或密码为空");
	}

	/**
	 * 重置密码错误次数
	 */
	@Override
	public User recordLoginInFailErros(User user) {
		User updateUser = new User();
		if (user.getCountPwdErrors() == null) {
			user.setCountPwdErrors(0);
		}
		updateUser.setId(user.getId());
		updateUser.setCountPwdErrors(user.getCountPwdErrors() + 1);

		if (!isHashErros(updateUser.getCountPwdErrors())) {
			updateUser.setStatus(CommonUtils.USER_STATUS_LOCK);
			updateUser.setUserLockTime(new Date());
		}
		updateUser.changeStatus(CommonUtils.METHOD_UPDATE, user.getUsername());
		userMapper.updateUser(updateUser);
		return updateUser;
	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteUser(String id) {
		if (userMapper.validatePolicyByUserId(id) > 0) {
			throw new BizException(BizCode.DELETE_USER_FAILD);
		}
		User users = new User();
		users.setId(id);
		users.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		users.setRowstate(CommonUtils.ROWSTATE_DELETE);
		userMapper.updateUser(users);
		addLog(OperationType.DELETE.getOption(), BusinessUtils.USER);
	}

	/**
	 * 更新用户错误次数及锁定时间 解锁
	 * 
	 * @param account
	 * @return
	 */
	public User resetLoginInFailErrors(User user) {
		User resultUser = findUserByUserName(user.getUsername());
		resultUser.setCountPwdErrors(0);
		resultUser.setUserLockTime(null);
		resultUser.setStatus(CommonUtils.USER_STATUS_NORMAL);
		userMapper.updateUser(resultUser);
		return resultUser;
	}

	public boolean isHashErros(Integer countErros) {

		return countErros < this.pwdErrors;
	}

	@Override
	public void updateStatus(User user) {
		int num = userMapper.updateStatus(user);
		if (num <= 0) {
			throw new BizException(BizCode.UPDATE_IS_FAILED);
		}
	}

	/**
	 * 列表查询用户
	 */
	@Override
	public Page<User> findUserByPage(Page<User> page, User user) {
		switch (getUserRole()) {
		case CommonUtils.ROLE_PROXY:
			userMapper.findUserByPageAndProxy(page, user);
			break;
		case CommonUtils.ROLE_ADMIN:
			userMapper.findUserByPage(page, user);
			break;
		default:
			throw new BizException(BizCode.REQUEST_EXCEPTION);
		}
		List<User> resultList = page.getResult();
		User users = null;
		for (int i = 0; i < resultList.size(); i++) {
			users = resultList.get(i);
			convertParamOut(users);
		}
		return page;
	}

	/**
	 * 查询所有不分页
	 */
	@Override
	public List<User> findUserByPage(User user) {

		List<User> resultList = userMapper.findUserByPage(user);
		User users = null;
		for (int i = 0; i < resultList.size(); i++) {
			users = new User();
			convertParamOut(users);
		}
		return resultList;
	}

	@Override
	public void insertUser(User user) {
		// 验证用户是否存在
		if (userMapper.selectCountByUsername(user.getUsername()) > 0) {
			throw new BizException(BizCode.FAILD_USERNAME_EXISTS_EXCEPTION);
		}
		if (null == user.getPassword()) {
			user.setPassword(Md5Util.getMD5String(resetPwd));
		}

		user.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		user.setIsFirstLogin(CommonUtils.Y);
		userMapper.insertUser(user);
		// 插入用户角色表
		saveOrUpdateUserRole(user);
		// 插入用户费率表
		saveOrUpdateUserRate(user);
		// 生成账户
		saveBalance(user);

		addLog(OperationType.ADD.getOption(), BusinessUtils.USER);
	}

	public void saveOrUpdateUserRole(User user) {
		userRoleService.deleteUserRole(user.getId());
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(user.getRoleId());
		userRole.changeStatus(CommonUtils.METHOD_ADD, getUserName());
		userRoleService.insertUserRole(userRole);
	}

	public void saveBalance(User user) {
		BalanceInfo balance = new BalanceInfo();
		balance.setUserId(user.getId());
		balance.setAccountNum(CommonUtils.USER_ACCOUNT);
		balance.setTotalBalance("0");
		balanceService.insertBalance(balance);
	}

	public void saveOrUpdateUserRate(User user) {
		userRateService.deleteUserRate(user.getId());
		// 一般费率 2
		if (StringUtils.isNotBlank(user.getCommonlyRate())) {
			insertRate(user.getCommonlyRate(), user, "2");
		}
		// 综合费率 1
		if (StringUtils.isNotBlank(user.getSynthesizeRate())) {
			insertRate(user.getSynthesizeRate(), user, "1");
		}
	}

	public void insertRate(String rate, User user, String type) {
		String roleCode = roleService.selectRoleCodeById(user.getRoleId());
		UserRateInfo userRate = null;
		
		// 角色为代理且为二级代理或者y，其上级代理必须为设置费率
		if (CommonUtils.ROLE_PROXY.equals(roleCode) && CommonUtils.AGENT_2.equals(user.getAgentLevel())) {
			userRate = new UserRateInfo();
			userRate.setUserId(user.getParentId());
			userRate.setInsuranceType(type);
			String parentRate = userRateService.findUserRate(userRate);
			if (parentRate == null) {
				throw new BizException(BizCode.USER_RATE_EXCEPTION);
			}
			if ((new BigDecimal(rate)).compareTo(new BigDecimal(parentRate)) < 0) {
				throw new BizException(BizCode.PARENT_USER_RATE_EXCEPTION);
			}
		}else if(CommonUtils.ROLE_USER.equals(roleCode)){
			if(user.getParentId()!=null){
				userRate = new UserRateInfo();
				userRate.setUserId(user.getParentId());
				userRate.setInsuranceType(type);
				String parentRate = userRateService.findUserRate(userRate);
				if (parentRate == null) {
					throw new BizException(BizCode.USER_RATE_EXCEPTION);
				}
				if ((new BigDecimal(rate)).compareTo(new BigDecimal(parentRate)) < 0) {
					throw new BizException(BizCode.PARENT_USER_RATE_EXCEPTION);
				}
			}
		}
		userRate = new UserRateInfo();
		userRate.setUserRate(rate);
		userRate.setUserId(user.getId());
		userRate.setInsuranceType(type);
		userRateService.insertUserRate(userRate);
	}

	public void convertParamOut(User user) {
		// 代理等级
		user.setAgentLevelName(getCodeNameByPidAndValue(CommonUtils.PROXY_LEVEL_PID, user.getAgentLevel()));
		// 是否发送邮件
		user.setIsSendMailName(getCodeNameByPidAndValue(CommonUtils.YES_OR_NOT, user.getIsSendMail()));
		user.setIsAllowEcargoName(getCodeNameByPidAndValue(CommonUtils.YES_OR_NOT, user.getIsAllowEcargo()));
		// 用户状态
		user.setStatusName(getCodeNameByPidAndValue(CommonUtils.USER_STATUS, user.getStatus()));
		user.setPassword(null);
		if (user.getUserRate() != null) {
			List<UserRateInfo> list = user.getUserRate();
			for (UserRateInfo userRate : list) {
				if (userRate.getInsuranceType().equals("1")) {
					user.setSynthesizeRate(StringUtil.convertDecimalZero(userRate.getUserRate()));
				}
				if (userRate.getInsuranceType().equals("2")) {
					user.setCommonlyRate(StringUtil.convertDecimalZero(userRate.getUserRate()));
				}
			}
		}
	}

	@Override
	public void updateUser(User user) {
		if (CommonUtils.ROLE_ADMIN.equals(getUserRole())) {
			updateUserByAdmin(user);
			saveOrUpdateUserRole(user);
		}
		saveOrUpdateUserRate(user);
		addLog(OperationType.UPDATE.getOption(), BusinessUtils.USER);
	}

	/**
	 * 管理员修改：可修改全部信息
	 */

	public void updateUserByAdmin(User user) {
		user.changeStatus(CommonUtils.METHOD_UPDATE, getUserName());
		userMapper.updateUserByAdmin(user);

	}

	/**
	 * 代理修改，只能修改费率
	 * 
	 * @param user
	 */
	public void updateUserByProx(User user) {
		User upd = new User();

		upd.setSynthesizeRate(user.getSynthesizeRate());
		upd.setCommonlyRate(user.getCommonlyRate());
		upd.setId(user.getId());
		userMapper.updateUser(upd);
	}

	@Override
	public List<User> getAllUser(User user) {
		List<User> userList = userMapper.findUser(user);
		for (User ue : userList) {
			if (ue.getParentId() != null && !"".equals(ue.getParentId().trim())) {
				getParentUser(ue.getParentId(), ue);
			}
		}
		return userList;
	}

	public void getParentUser(String parengId, User user) {
		User parentUser = userMapper.findParentUser(parengId);
		if (parentUser != null) {
			if (parentUser.getRoleCode().equals(CommonUtils.ROLE_PROXY)) {
				if (CommonUtils.AGENT_1.equals(parentUser.getAgentLevel())) {
					user.setAgentName1(parentUser.getFullName());
				}
				if (CommonUtils.AGENT_2.equals(parentUser.getAgentLevel())) {
					user.setAgentName2(parentUser.getFullName());
				}
			}
			if (parentUser.getParentId() != null && !"".equals(parentUser.getParentId().trim())) {
				getParentUser(parentUser.getParentId(), user);
			}
		}

	}

	@Override
	public void updPwd(User user) {
		user.changeStatus(CommonUtils.METHOD_UPDATE, user.getUsername());
		if (!user.getPassword().trim().equals(user.getAgainPassword().trim())) {
			throw new BizException(BizCode.EXCEPTION_UPDATE_PWD_NOT_SAME);
		}
		int updCount = userMapper.updateUser(user);
		if (updCount <= 0) {
			throw new BizException(BizCode.EXCEPTION_UPDATE_USER);
		}
	}

	@Override
	public String selectUserIsAllowEcargo(String userId) {
		return userMapper.selectUserIsAllowEcargo(userId);
	}

}

/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.user.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.User;
import com.boss.core.struct.ResultInfo;
import com.boss.core.struct.ResultPage;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;
import com.boss.db.feature.orm.mybatis.Page;
import com.boss.user.service.UserService;
import com.boss.utils.Md5Util;
import com.boss.utils.cons.CommonUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2018年2月3日下午2:03:56
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 用户验证
	 * 
	 * @param validateUser
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/validate")
	@ApiOperation(value = "用户验证", httpMethod = "GET", response = User.class, notes = "用户验证")
	public ResultPage validateUser(
			@ApiParam(required = true, name = "validateUser", value = "用户信息") @RequestBody User validateUser) {
		return userService.validateUser(validateUser);
	}

	@PutMapping("/updPwd")
	@ApiOperation(value = "修改密码", httpMethod = "GET", response = User.class, notes = "修改密码")
	public String updatePwd(
			@ApiParam(required = true, name = "validateUser", value = "修改用户") @RequestBody User validateUser) {
		userService.updPwd(validateUser);
		return ResultInfo.successToJsonString("修改成功");
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping("/")
	@ApiOperation(value = " 修改用户", httpMethod = "PUT", response = String.class, notes = " 修改用户")
	public ResultInfo update(
			@ApiParam(required = true, name = "validateUser", value = "用户信息") @Valid @RequestBody User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.error(result.getFieldErrors().get(0).getDefaultMessage());
		}
		if (StringUtils.isNotBlank(user.getPassword())) {
			user.setPassword(Md5Util.getMD5String(user.getPassword()));
		}
		userService.updateUser(user);
		return ResultInfo.success("修改成功");
	}

	/**
	 * 删除用户
	 * 
	 * @param user
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除用户", httpMethod = "DELETE", response = User.class, notes = "删除用户")
	public ResultInfo delete(@ApiParam(required = true, name = "id", value = "用户id") @PathVariable String id) {
		userService.deleteUser(id);
		return ResultInfo.success("删除成功");
	}

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/username")
	@ApiOperation(value = "根据用户名查找用户信息", httpMethod = "GET", response = User.class, notes = "根据用户名查找用户信息")
	public User getUserbyUsername(
			@ApiParam(required = true, name = "userName", value = "用户名称") @RequestParam("username") String username) {
		return userService.findUserByUserName(username);
	}

	/**
	 * 更新状态
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/updStatus")
	@ApiOperation(value = "更新状态", httpMethod = "POST", response = User.class, notes = "更新状态")
	public ResultInfo updateStatus(
			@ApiParam(required = true, name = "validateUser", value = "用户信息") @RequestBody User user) {
		userService.updateStatus(user);
		return ResultInfo.success();
	}

	/**
	 * 用户列表
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "用户列表分页", httpMethod = "POST", response = User.class, notes = "用户列表分页")
	public String selectUserByPage(
			@ApiParam(required = true, name = "validateUser", value = "用户信息") @RequestBody User user) {
		Page<User> page = new Page<User>(user.getPageNo(), user.getPageSize());
		user.setId(getUserId());
		user.setRowstate(CommonUtils.ROWSTATE_FREEZE);
		userService.findUserByPage(page, user);
		return ResultPage.successToJsonString(page);
	}

	/**
	 * 用户列表
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/all")
	@ApiOperation(value = "用户列表不分页", httpMethod = "GET", response = User.class, notes = "用户列表不分页")
	public String selectAllUser(@ApiParam(required = true, name = "user", value = "用户信息") @RequestBody User user) {
		return ResultInfo.successToJsonString(userService.getAllUser(user));
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/")
	@ApiOperation(value = "新增用户", httpMethod = "POST", response = String.class, notes = "新增用户")
	public String insertUserByPage(
			@ApiParam(required = true, name = "validateUser", value = "用户信息") @RequestBody @Valid User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResultInfo.getValidationMessage(result);
		}
		
		convertParamIn(user);
		userService.insertUser(user);
		return ResultInfo.successToJsonString("新增成功");
	}

	/**
	 * 入参转换
	 * 
	 * @param user
	 */
	public void convertParamIn(User user) {
		user.setRowstate(CommonUtils.ROWSTATE_FREEZE);
		if (CommonUtils.PROXY_ID.equals(user.getRoleId())) {
			if (null == user.getAgentLevel()) {
				throw new BizException(BizCode.INSERT_USER_FAILD);
			}
			if (CommonUtils.AGENT_2.equals(user.getAgentLevel()) && null == user.getParentId()) {
				throw new BizException(BizCode.PARENT_ID_IS_NULL);
			}
		}

	}
}

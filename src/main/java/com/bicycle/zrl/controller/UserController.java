package com.bicycle.zrl.controller;

import com.bicycle.zrl.entity.User;
import com.bicycle.zrl.service.UserService;
import com.bicycle.zrl.util.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 获取验证码接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getCode")
	public boolean getCode(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = HttpHelper.paramRequest(request);
		return userService.sendCode(map);
	}

	/**
	 * 校验验证码
	 * @return
	 */
	@RequestMapping("/checkCode")
	@ResponseBody
	public String checkCode(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = HttpHelper.paramRequest(request);
		return userService.checkCode(map);
	}

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/registerUser")
	@ResponseBody
	public boolean registerUser(@RequestBody User user){
		try {
			userService.registerUser(user);
			return true;
		} catch (Exception e) {
			logger.error("注册失败: " + e.getMessage());
		}
		return false;
	}

	/**
	 * 充值押金
	 */
	@RequestMapping("/deposit")
	@ResponseBody
	public boolean deposit(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = HttpHelper.paramRequest(request);
		try {
			userService.updateUser(map);
			return true;
		} catch (Exception e) {
			logger.error("充值押金失败: " + e.getMessage());
		}
		return false;
	}

	/**
	 * 实名认证
	 */
	@RequestMapping("/identify")
	@ResponseBody
	public boolean identify(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = HttpHelper.paramRequest(request);
		try {
			userService.updateUser(map);
			return true;
		} catch (Exception e) {
			logger.error("实名认证失败: " + e.getMessage());
		}
		return false;
	}

}

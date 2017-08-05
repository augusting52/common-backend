package com.backend.controller.backend;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.backend.common.BackendException;
import com.backend.constants.Constants;
import com.backend.constants.Pages;
import com.backend.constants.UserConstants;
import com.backend.dto.user.UserDto;
import com.backend.service.UserService;

/**
 * 后台前端用户模块接口
 * 
 * @author LiBing
 * 
 */
@Controller
@RequestMapping(value = "/")
public class UserController {
	/**
	 * 用户服务类
	 */
	@Autowired
	private UserService userService;

	/**
	 * 用户登录
	 * @param loginAccount 登录账号
	 * @param loginPwd 登录密码
	 * @param response 接口响应
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ModelAndView login(@RequestParam String loginAccount, @RequestParam String loginPwd, HttpServletResponse response) {
		try {
			String token = userService.login(loginAccount,  loginPwd);
			ModelAndView mav = new ModelAndView();
			mav.setViewName(Pages.PAGE_INDEX);
			mav.addObject(UserConstants.USER, userService.getCurrentUserByToken(token).getName());
			Cookie cookie = new Cookie(UserConstants.TOKEN, token);
			cookie.setPath(Constants.URL_PATH_PREFIX);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			try {
				response.sendRedirect(Pages.URL_PAGE_INDEX);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mav;
		} catch (BackendException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName(Pages.PAGE_LOGIN);
			mav.addObject(UserConstants.USER, loginAccount);
			mav.addObject(UserConstants.PASSWORD, loginPwd);
			switch (e.getErrorCode()) {
			case 100005:
				mav.addObject(Constants.MESSAGE, Constants.USER_NOT_EXIST);
				break;
			}
			return mav;
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param mobile
	 * @param password
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ModelAndView loginPage(@RequestParam() String mobile, @RequestParam() String password,
			HttpServletResponse response) {
		try {
			String token = userService.login(mobile, password);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			mav.addObject("User", userService.getCurrentUserByToken(token).getName());
			// mav.addObject("User", mobile);
			/* mav.addObject("Token", token); */
			Cookie cookie = new Cookie("token", token);
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			try {
				response.sendRedirect("/backend/main");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mav;
		} catch (BackendException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("index");
			mav.addObject("User", mobile);
			mav.addObject("Password", password);
			switch (e.getErrorCode()) {
			case 100005:
				mav.addObject("message", "NotExist");
				break;
			}
			return mav;
		}
	}

	/**
	 * 登录页面, 默认页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ModelAndView showLoginPage() {
		return new ModelAndView("login");
	}

	/**
	 * 获取用户列表.
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param sortProperty
	 * @param sortDirection
	 * @param email
	 * @param gender
	 * @param mobile
	 * @param name
	 * @param point
	 * @param userToken
	 * @return Page
	 */
	@RequestMapping(value = "backend/users", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Page<UserDto> getUserByPage(@RequestParam(defaultValue = "0") int currentPage,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "period") String sortProperty,
			@RequestParam(defaultValue = "DESC") String sortDirection, @RequestParam String email,
			@RequestParam int gender, @RequestParam String mobile, @RequestParam String name, @RequestParam int point,
			@CookieValue(defaultValue = "token", value = "token") String userToken) {
		return userService.listUserByPage(currentPage, pageSize, sortDirection, sortProperty, email, gender, mobile,
				name, point);
	}

}

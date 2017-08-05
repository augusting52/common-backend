package com.backend.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.backend.common.LoginRequired;
import com.backend.constants.Pages;
import com.backend.constants.UserConstants;
import com.backend.service.UserService;

/**
 * 后台主页接口.
 * 
 * @author LiBing
 */
@Controller
@RequestMapping(value = "/backend/")
public class HomeController {
	/**
	 * 用户服务类
	 */
	@Autowired
	private UserService userService;

	/**
	 * 待实现.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}

	/**
	 * 待实现.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView detail() {
		return new ModelAndView("detail");
	}

	/**
	 * 后台主页.
	 * 
	 * @param userToken
	 * @return ModelAndView
	 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@LoginRequired
	public ModelAndView main(
			@CookieValue(value = "token", defaultValue = "token") String userToken) {
		ModelAndView view = new ModelAndView("main");
		view.addObject("User", userService.getCurrentUserByToken(userToken).getName());
		return view;
	}
	
	/**
	 * 后台主页.
	 * 
	 * @param userToken
	 * @return ModelAndView
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@LoginRequired
	public ModelAndView userIndex(
			@CookieValue(value = "token", defaultValue = "token") String userToken) {
		ModelAndView view = new ModelAndView(Pages.PAGE_INDEX);
		view.addObject(UserConstants.USER, userService.getCurrentUserByToken(userToken).getName());
		return view;
	}
	
}

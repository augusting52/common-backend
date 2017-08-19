package com.backend.controller.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.backend.common.BackendException;
import com.backend.constants.ExceptionCode;
import com.backend.constants.UserConstants;
import com.backend.dto.APIResult;
import com.backend.service.UserService;
import com.backend.util.RandomUtil;

/**
 * <p>
 * User API 接口设计说明。
 * 1. 用户注册接口
 * 2. 用户登录接口
 * 3. 刷新Token接口
 * 4. 修改密码接口
 * 5. 上传头像接口
 * 6. 上传用户信息接口
 * 7. 修改用户信息接口
 * </p>
 * 
 * @author libing  @date 2017年8月9日
 */
@Controller
@RequestMapping(value = "/")
public class UserAPIController {
	
	/**
	 * 用户服务类
	 */
	@Autowired
	private UserService userService;

	/**
	 * 客户端注册用户.
	 * <p>
	 * 接口：IPAddress/api/user/
	 * </p>
	 * 
	 * @param parameters contais user account and user password
	 * @return APIResult
	 */
	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<Map<String, String>> createUser(
			@RequestBody Map<String, String> parameters) {
		if (parameters.get(UserConstants.PARAM_KEY_USER_ACCOUNT) == null
				|| parameters.get(UserConstants.PARAM_KEY_USER_PASSWORD) == null) {
			throw new BackendException(ExceptionCode.REQUEST_PARAMS_NULL);
		}
		// TODO 密码非明文传输，用户账号类型判断｛手机号码，邮箱，或者用户名等｝
		String userToken = userService.createUser(parameters.get(UserConstants.PARAM_KEY_USER_ACCOUNT),
				parameters.get(UserConstants.PARAM_KEY_USER_PASSWORD));
		return generateTokenResult(userToken);
	}

	/**
	 * 上传用户头像.
	 * <p>
	 * 接口：IPAddress/api/user/upload
	 * </p>
	 * 
	 * @param userToken
	 * @param file
	 * @return APIResult
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<String> upload(@RequestHeader("token") String userToken,
			@RequestParam("file") MultipartFile file) {
		String filePath = "/static/avatar/" + System.currentTimeMillis() + "_"
				+ RandomUtil.generate6Int() + ".jpg";
		try {
			File f = new File(
					"I:/Android/ProfessorAfternoonTea/backend/ProfessorTea/src/main/webapp"
							+ filePath);
			// File f = new File("C:/virtualhost/professortea/ROOT/" +
			// filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.getCurrentUserByToken(userToken).setAvator(filePath);
		return new APIResult<String>(filePath);
	}

	/**
	 * 获取用户信息.
	 * <p>
	 * 接口：IPAddress/api/user/
	 * </p>
	 * 
	 * @param userToken
	 * @return APIResult
	 */
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<Map<String, Object>> findUserInfo(
			@RequestHeader("token") String userToken) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", userService.getUserInfo(userToken));
		return new APIResult<Map<String, Object>>(map);
	}

	/**
	 * 用户登录接口.
	 * <p>
	 * 接口：IPAddress/api/user/login
	 * </p>
	 * 
	 * @param parameters
	 * @return APIResult
	 */
	@RequestMapping(value = "/login", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<Map<String, String>> login(
			@RequestBody Map<String, String> parameters) {
		if (parameters.get("mobile") == null
				|| parameters.get("password") == null) {
			throw new BackendException(100010);
		}
		return generateTokenResult(userService.login(parameters.get("mobile"),
				parameters.get("password")));
	}

	/**
	 * 更新用户令牌.
	 * <p>
	 * 接口：IPAddress/api/user/refreshToken
	 * </p>
	 * 
	 * @param userToken
	 * @return APIResult
	 */
	@RequestMapping(value = "/refreshToken", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<Map<String, String>> refreshToken(
			@RequestHeader("token") String userToken) {
		return generateTokenResult(userService.reLogin(userToken));
	}

	/**
	 * 更改密码
	 * <p>
	 * 接口：IPAddress/api/user/updatePassword
	 * </p>
	 * 
	 * @param parameters
	 * @return APIResult
	 */
	@RequestMapping(value = "updatePassword", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<Map<String, String>> updatePasword(
			@RequestBody Map<String, String> parameters) {
		if (parameters.get("mobile") == null
				|| parameters.get("password") == null) {
			throw new BackendException(1000010);
		}
		Map<String, String> map = new HashMap<String, String>();

		map.put("userToken", userService.changeUserPassword(
				parameters.get("mobile"), parameters.get("password")));
		return new APIResult<Map<String, String>>(map);
	}

	/**
	 * 更改用户信息.
	 * <p>
	 * 接口：IPAddress/api/user/userInfo
	 * </p>
	 * 
	 * @param parameters
	 * @param userToken
	 * @return APIResult
	 */
	@RequestMapping(value = "/userInfo", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public APIResult<Integer> updateUserInfo(
			@RequestBody Map<String, String> parameters,
			@RequestHeader("token") String userToken) {
		if (parameters != null) {
			userService.updateUserInfo(userToken, parameters);
		} else {
			throw new BackendException(100011);
		}
		return new APIResult<Integer>(0);
	}

	/**
	 * 将用户令牌打包成APIResult对象
	 * 
	 * @param userToken
	 * @return APIResult
	 */
	private APIResult<Map<String, String>> generateTokenResult(String userToken) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("userToken", userToken);
		return new APIResult<Map<String, String>>(result);
	}
}

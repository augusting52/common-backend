package com.backend.controller.backend;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.backend.common.LoginRequired;
import com.backend.util.RandomUtil;

/**
 * 上传文件接口.
 * <p>
 * 根据不同的部署方式设置不同路径
 * </p>
 * 
 * @author LiBing
 * 
 */
@Controller
@RequestMapping(value = "/backend/upload/")
public class UploadController {

	/**
	 * 上传图片.
	 * 
	 * @param file
	 * @param userToken
	 * @return String
	 */
	@RequestMapping(value = "image", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@LoginRequired(authority = "product_edit")
	public String uploadImage(
			@RequestParam MultipartFile file,
			@CookieValue(value = "token", defaultValue = "token") String userToken) {
		String[] arrays = file.getOriginalFilename().split("\\.");
		String filePath = "/static/images/" + System.currentTimeMillis() + "_"
				+ RandomUtil.generate6Int() + "." + arrays[arrays.length - 1];
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
		return filePath;
	}

	/**
	 * 上传视频.
	 * <p>
	 * 接口：IPAddress
	 * </p>
	 * 
	 * @param file
	 * @param userToken
	 * @return String
	 */
	@RequestMapping(value = "video", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@LoginRequired(authority = "product_edit")
	public String uploadVideo(
			@RequestParam MultipartFile file,
			@CookieValue(value = "token", defaultValue = "token") String userToken) {
		String[] arrays = file.getOriginalFilename().split("\\.");
		String filePath = "/static/video/" + System.currentTimeMillis() + "_"
				+ RandomUtil.generate6Int() + "." + arrays[arrays.length - 1];
		try {
			// File f = new File(
			// "I:/Android/ProfessorAfternoonTea/backend/ProfessorTea/src/main/webapp"
			// + filePath);
			File f = new File("C:/virtualhost/professortea/ROOT/" + filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

}

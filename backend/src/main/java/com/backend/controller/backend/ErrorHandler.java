package com.backend.controller.backend;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.backend.common.BackendException;
import com.backend.dto.APIResult;

/**
 * 程序异常处理类.
 * <p>
 * 根据程序中抛出的异常代码进行对应处理。
 * </p>
 * 
 * @author LiBing
 * 
 */
@ControllerAdvice
public class ErrorHandler {

	/**
	 * 后台前端异常响应.
	 * @param exception
	 * @param request
	 * @param response
	 * @return APIResult
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public APIResult<BackendException> errorResponse(Exception exception, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Logger logger = Logger.getLogger(ErrorHandler.class);
		logger.error("Exception:", exception);
		StringWriter stackTraceWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTraceWriter));
		String stackTrace = stackTraceWriter.toString();
//		if (ProfessorTeaException.class.isInstance(exception)) {
//			return new APIResult<ProfessorTeaException>(((ProfessorTeaException) exception).getErrorCode(), "不好意思，出错了！！");
//		} else {
//			return new APIResult<ProfessorTeaException>(ProfessorTeaException.UNKNOWN, stackTrace);
//		}
		if (exception instanceof BackendException) {
			BackendException e = (BackendException) exception;
			String accept = request.getHeader("Accept");
			String xRequestedWithStr = request.getHeader("X-Requested-With");
			if ((xRequestedWithStr != null && xRequestedWithStr.indexOf("XMLHttpRequest") > -1)
					|| (accept != null && accept.indexOf("application/json") > -1)) {
				return new APIResult<BackendException>(e.getErrorCode(), stackTrace);
			} else {
				if (e.getErrorCode() == 100005 || e.getErrorCode() == 100006) {
					response.sendRedirect("/backend/index/login");
				} else {
					request.setAttribute("code", e.getErrorCode());
					request.getRequestDispatcher("/backend/tips").forward(request, response);
				}
				return null;
			}
		} else {
			logger.error("Exception:", exception);
			return new APIResult<BackendException>(BackendException.UNKNOWN, stackTrace);
		}
	}
}
package com.backend.common;

import java.lang.annotation.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;

import com.backend.service.UserService;

/**
 * AspectJ注解配置Spring AOP.
 * <p>
 * 自定义@LoginRequired 注解的执行行为<br>
 * 获取权限 getAuthority()<br>
 * 检查权限 check()<br>
 * 获取用户令牌 getUserToken()
 * </p>
 * 
 * @author 隔壁老王
 * 
 */
@Aspect
public class AuthorizationAspect {
	/**
	 * 用户服务类
	 */
	@Autowired
	private UserService userService;

	/**
	 * 检查用户权限.
	 * <p>
	 * 获取注解打上的权限，更加用户令牌获取用户，检查该用户是否包含该权限
	 * </p>
	 * 
	 * @param joinPoint
	 * @throws Exception
	 */
	@Before(value = "@annotation(com.backend.common.LoginRequired)")
	public void check(JoinPoint joinPoint) throws Exception {
		String userToken = getUserToken(joinPoint);
		userService.getCurrentUserByToken(userToken);
		String authority = getAuthority(joinPoint);
		userService.checkUserAuthority(authority);
	}

	/**
	 * 获取注解权限.
	 * <p>
	 * 获取该方法注解所打上的权限
	 * </p>
	 * 
	 * @param joinPoint
	 * @return authority 用户权限
	 */
	private String getAuthority(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		return signature.getMethod().getAnnotation(LoginRequired.class)
				.authority();
	}

	/**
	 * 获取用户令牌.
	 * <p>
	 * 获取调用该接口请求的用户令牌
	 * </p>
	 * 
	 * @param joinPoint
	 * @return token 用户令牌
	 * @throws Exception
	 */
	private String getUserToken(JoinPoint joinPoint) throws Exception {
		Object[] parameterValues = joinPoint.getArgs();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Annotation[][] annotations = joinPoint
				.getTarget()
				.getClass()
				.getMethod(signature.getMethod().getName(),
						signature.getMethod().getParameterTypes())
				.getParameterAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			for (Annotation parameterAnnotation : annotations[i]) {
				if (parameterAnnotation.annotationType().equals(
						RequestHeader.class)
						|| parameterAnnotation.annotationType().equals(
								CookieValue.class)) {
					if ("token".equals(parameterAnnotation.annotationType()
							.getMethod("value").invoke(parameterAnnotation)
							.toString())) {
						return (String) parameterValues[i];
					}
				}
			}
		}
		return null;
	}
}

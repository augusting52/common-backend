package com.backend.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义登录权限验证注解.
 * <p>
 * 用于用户登录的权限验证，判断用户是否有该权限使用该服务或该接口。<br>
 * 用于为方法METHOD打注解。<br>
 * 默认值为空（值为权限的一种）
 * </p>
 * 
 * @author 隔壁老王
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginRequired {
	String authority() default "";
}

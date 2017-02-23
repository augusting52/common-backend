package com.backend.common;

/**
 * 自定义程序异常.
 * <p>
 * 继承RuntimeException
 * </p>
 * 
 * @author 隔壁老王
 * 
 */
public class BackendException extends RuntimeException {
	/**
	 * 序列号ID
	 */
	private static final long serialVersionUID = 2134223938478410255L;
	/**
	 * 未知异常代码
	 */
	public static int UNKNOWN = 999999;
	/**
	 * 异常代码
	 */
	private int errorCode;

	/**
	 * 构造函数
	 * @param i 抛出异常代码
	 */
	public BackendException(int i) {
		errorCode = i;
	}

	/**
	 * 获取异常代码
	 * @return errorCode 异常代码
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置异常代码
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}

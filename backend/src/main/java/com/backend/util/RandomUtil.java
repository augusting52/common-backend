package com.backend.util;

import java.util.Random;

/**
 * 随机数工具类.
 * 
 * @author 隔壁老王
 *         <p>
 *         用于生成0-999999直接的随机数
 *         </p>
 * 
 */
public class RandomUtil {
	private static Random random = new Random();

	public static String generate6Int() {
		return String.valueOf(random.nextInt(999999));
	}
}

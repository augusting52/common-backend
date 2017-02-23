package com.backend.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 数据类与Json数据格式转换工具类
 * 
 * @author 隔壁老王
 * 
 * @param <T>
 */
public class JsonUtils<T> {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object o) throws JsonParseException,
			JsonMappingException, IOException {
		return mapper.writeValueAsString(o);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObject(String vlaue, Class valueType)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(vlaue, valueType);
	}
}

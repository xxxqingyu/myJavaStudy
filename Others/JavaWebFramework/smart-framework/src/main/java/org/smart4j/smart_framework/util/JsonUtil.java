package org.smart4j.smart_framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
	private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();
	
	public static <T> String toJson(T obj) {
		String json;
		try {
			json=OBJECT_MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return json;
	}
	
	public static <T> T fromJson(String json,Class<T> type) {
		T pojo;
		try {
			pojo=OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return pojo;
	}
}

package org.smart4j.smart_framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class StreamUtil {
	/**
	 * 从输入流中获取字符串
	 * @param inputStream
	 * @return
	 */
	public static String getString(InputStream inputStream) {
		StringBuilder stringBuilder=new StringBuilder();
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line=reader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return stringBuilder.toString();
	}
	
	
}

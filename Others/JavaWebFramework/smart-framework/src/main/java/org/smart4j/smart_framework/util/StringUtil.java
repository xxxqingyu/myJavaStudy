package org.smart4j.smart_framework.util;

public final class StringUtil {
	public static final String SEPARATOR=String.valueOf((char)29); 
	/**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(final String str) {
        return (str == null) || (str.length() == 0);
    }

    /**
     * 判断是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }
    
    /**
     * 分割字符串
     * @param source
     * @param delim
     * @return
     */
    public static String[] splitString(String source, String delim) {
    	if (StringUtil.isEmpty(source)) {
    	    return new String[0];
    	} else {
    	    return source.split(delim);
    	}
     }
}

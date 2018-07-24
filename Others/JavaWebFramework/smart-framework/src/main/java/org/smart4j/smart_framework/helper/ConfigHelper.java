package org.smart4j.smart_framework.helper;

import java.util.Properties;

import org.smart4j.smart_framework.ConfigConstant;
import org.smart4j.smart_framework.util.PropsUtil;

public final class ConfigHelper {
	private static final Properties CONFIG_PROPS= PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	/**
	 * 获取JDBC驱动
	 * @return
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}
	
	/**
	 * 获取JDBC URL
	 * @return
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}
	
	/**
	 * 获取JDBC用户名
	 * @return
	 */
	public static String getJdbcUserName() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}
	
	/**
	 * 获取JDBC密码
	 * @return
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}
	
	/**
	 * 获取应用基础包名
	 * @return 
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	/**
	 * 获取应用JSP路径
	 * @return
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
	}
	
	/**
	 * 获取应用静态资源路径
	 * @return
	 */
	public static String getAppBaseAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH,"/asset/");
	}
}

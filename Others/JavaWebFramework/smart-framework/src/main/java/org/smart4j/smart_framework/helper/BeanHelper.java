package org.smart4j.smart_framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.smart_framework.util.ReflectionUtil;

public final class BeanHelper {
	/**
	 * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
	
	static{
		Set<Class<?>> beanClassSet= ClassHelper.getBeanClassSet();
		for (Class<?> beanClass : beanClassSet) {
			Object object=ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, object);
		}
	}
	
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:"+cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
	
	public static void setBean(Class<?> cls,Object obj) {
		BEAN_MAP.put(cls, obj);
	}
}
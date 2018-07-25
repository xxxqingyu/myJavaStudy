package org.smart4j.smart_framework.helper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.smart_framework.annotation.Inject;
import org.smart4j.smart_framework.util.CollectionUtil;
import org.smart4j.smart_framework.util.ReflectionUtil;

public final class IocHelper {
	static{
		Map<Class<?>, Object> beanMap=BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			//遍历BeanMap
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				//从BeanMap中获取Bean类与Bean实例
				Class<?> beanClass =beanEntry.getKey();
				Object beanInstance=beanEntry.getValue();
				
				// 获取Bean类定义的所有成员变量
				Field[] beanFields=beanClass.getDeclaredFields();
				if (ArrayUtils.isNotEmpty(beanFields)) {
					// 遍历Bean Field
					for (Field field : beanFields) {
						if (field.isAnnotationPresent(Inject.class)) {
							// 在BeanMap中获取Bean Field对应实例
							Class<?> beanFieldClass=field.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}

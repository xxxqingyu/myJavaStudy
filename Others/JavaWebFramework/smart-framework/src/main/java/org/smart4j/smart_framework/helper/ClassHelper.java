package org.smart4j.smart_framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.smart_framework.annotation.Controller;
import org.smart4j.smart_framework.annotation.Service;
import org.smart4j.smart_framework.util.ClassUtil;


public final class ClassHelper {
	private static final Set<Class<?>> CLASS_SET;
	
	static{
		String basePackage=ConfigHelper.getAppBasePackage();
		CLASS_SET=ClassUtil.getClasses(basePackage, true);
	}
	
	/**
	 * @return
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}
	
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>>  classSet=new HashSet<>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>>  classSet=new HashSet<>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>>  classSet=new HashSet<>();
		classSet.addAll(getServiceClassSet());
		classSet.addAll(getControllerClassSet());
		return classSet;
	}
}

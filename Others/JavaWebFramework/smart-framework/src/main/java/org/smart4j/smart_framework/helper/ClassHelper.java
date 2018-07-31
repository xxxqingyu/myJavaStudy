package org.smart4j.smart_framework.helper;

import java.lang.annotation.Annotation;
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
	
	/**
	 * 获取应用包名下某个父类的所有子类
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> cls : CLASS_SET) {
			if (superClass.isAssignableFrom(cls)&& !superClass.equals(cls)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下带有注解的所有类
	 * @param annotationClass
	 * @return
	 */
	public static  Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(annotationClass)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
}

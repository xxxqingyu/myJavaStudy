package org.smart4j.smart_framework;

import org.smart4j.smart_framework.helper.AopHelper;
import org.smart4j.smart_framework.helper.BeanHelper;
import org.smart4j.smart_framework.helper.ClassHelper;
import org.smart4j.smart_framework.helper.ControllerHelper;
import org.smart4j.smart_framework.helper.IocHelper;
import org.smart4j.smart_framework.util.ClassUtil;


public final class HelperLoader {
	public static void init(){
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName(), false);
		}
	}
}

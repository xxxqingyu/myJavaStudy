package org.smart4j.smart_framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogRecord;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.smart_framework.annotation.Action;
import org.smart4j.smart_framework.bean.Handler;
import org.smart4j.smart_framework.bean.Request;


public final class ControllerHelper {
	private static final Map<Request, Handler> ACTION_MAP=new  HashMap<>();
	
	static{
		Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
		if (CollectionUtils.isNotEmpty(controllerClassSet)) {
			//遍历Controller
			for (Class<?> controllerClass : controllerClassSet) {
				//获取Controller类中的方法
				Method[] methods=controllerClass.getDeclaredMethods();
				if (ArrayUtils.isNotEmpty(methods)) {
					//遍历方法
					for (Method method : methods) {
						if (method.isAnnotationPresent(Action.class)) {
							Action action=method.getAnnotation(Action.class);
							String mapping=action.value();
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array=mapping.split(":");
								if (ArrayUtils.isNotEmpty(array)&&array.length==2) {
									//获取请求方法与路径
									String requestMethod=array[0];
									String requestPath=array[1];
									Request request=new Request(requestMethod, requestPath);
									Handler handler=new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static Handler getHandler(String requestMethod,String requestPath) {
		Request request=new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}

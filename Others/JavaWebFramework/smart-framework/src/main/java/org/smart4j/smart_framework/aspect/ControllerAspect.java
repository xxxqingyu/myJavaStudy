package org.smart4j.smart_framework.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smart_framework.annotation.Aspect;
import org.smart4j.smart_framework.annotation.Controller;
import org.smart4j.smart_framework.proxy.AspectProxy;

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
	private static final Logger LOGGER=LoggerFactory.getLogger(ControllerAspect.class);
	
	private long begin;
	
	@Override
	public void before(Class<?> cls,Method method,Object[] params) throws Throwable{
		LOGGER.debug("---------begin------------");
		LOGGER.debug(String.format("class: %s",	 cls.getName()));
		LOGGER.debug(String.format("method: %s", method.getName()));
		begin=System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * @see org.smart4j.smart_framework.proxy.AspectProxy#after(java.lang.Class, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
		LOGGER.debug(String.format("time: %dms",	System.currentTimeMillis()-begin));
		LOGGER.debug("---------end------------");
	}
}

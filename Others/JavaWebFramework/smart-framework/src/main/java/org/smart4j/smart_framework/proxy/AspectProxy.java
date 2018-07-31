package org.smart4j.smart_framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AspectProxy implements Proxy {
	private static final Logger LOGGER=LoggerFactory.getLogger(AspectProxy.class);

	/* (non-Javadoc)
	 * @see org.smart4j.smart_framework.proxy.Proxy#doProxy(org.smart4j.smart_framework.proxy.ProxyChain)
	 */
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		
		Class<?> cls = proxyChain.getTargetClass();
		Method method=proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();
		
		begin();
		
		try{
			if (intercept(cls, method, params)) {
				before(cls, method, params);
				result=proxyChain.doProxyChain();
				after(cls, method, params, result);
			}else{
				result = proxyChain.doProxyChain();
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			end();
		}
		return result;
	}
	
	public void begin() {
		
	}
	
	public boolean intercept(Class<?> cls,Method method,Object[] params) throws Throwable {
		return true;
	}
	
	public void before(Class<?> cls,Method method,Object[] params) throws Throwable {
		
	}
	
	public void after(Class<?> cls,Method method,Object[] params,Object result) throws Throwable {
		
	}
	
	public void error(Class<?> cls,Method method,Object[] params,Throwable e) {
		
	}
	
	public void end() {
		
	}
}

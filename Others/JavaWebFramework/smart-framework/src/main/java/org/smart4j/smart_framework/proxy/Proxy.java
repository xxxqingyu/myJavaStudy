package org.smart4j.smart_framework.proxy;

public interface Proxy {
	Object doProxy(ProxyChain proxyChain) throws Throwable;
}

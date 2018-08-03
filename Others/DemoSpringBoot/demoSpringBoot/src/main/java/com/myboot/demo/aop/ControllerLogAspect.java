package com.myboot.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect 
@Component
public class ControllerLogAspect {
//	//定义切面
	@Pointcut("execution(* com.myboot.demo.controller..*.(..))")
	public void pointcut() {}

	//定义方法拦截的规则
	//@Before("execution(* (@RestController *).*(..))")
	//@Before("execution(* com.myboot.demo.controller.*(..))")
	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
	    MethodSignature signature =  (MethodSignature) joinPoint.getSignature();
	    //拦截了方法
	    Method method = signature.getMethod();
	    //直接获取方法名字
	    System.out.println("before方法规则式拦截" + method.getName());
	}
}

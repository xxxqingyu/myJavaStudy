package demo.spring01.service;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class MyLoggerAspect {
	
    public Object record(ProceedingJoinPoint pjp){
    	 try {
    		 
    		 MethodSignature signature =  (MethodSignature) pjp.getSignature();
    		 Method method = signature.getMethod();
    		 System.out.println("before:" + method.getName());
    		 
	    	 Object obj = pjp.proceed();
	    	 
    		 System.out.println("after:" + method.getName());
    		 
	    	 return obj;
    	 }catch(Throwable e){
    		 
    	 }
    	 return null;
    }
    
}

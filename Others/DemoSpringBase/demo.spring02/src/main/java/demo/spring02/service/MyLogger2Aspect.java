package demo.spring02.service;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;


@Aspect 
@Component
public class MyLogger2Aspect {
	@Around("execution(* demo.spring02.service..*Service.*(..))")
    public Object record(ProceedingJoinPoint pjp){
   	 try {
   		 
   		 MethodSignature signature =  (MethodSignature) pjp.getSignature();
   		 Method method = signature.getMethod();
   		 System.out.println("Logger2 before:" + method.getName());
   		 
	    	 Object obj = pjp.proceed();
	    	 
   		 System.out.println("Logger2 after:" + method.getName());
   		 
	    	 return obj;
   	 }catch(Throwable e){
   		 
   	 }
   	 return null;
   }
}

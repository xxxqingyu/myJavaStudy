package com.myboot.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration 
//Bean扫描器 
@ComponentScan("com.myboot.demo.aop") 
//开启spring对aspectJ的支持 
@EnableAspectJAutoProxy 
public class AopConfig {

}

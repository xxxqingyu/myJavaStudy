package demo.spring02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import demo.spring02.service.HelloService;
import demo.spring02.service.IHelloService;

@Configuration
//@ImportResource({"classpath:applicationContext.xml"})
@ComponentScan(basePackages={"demo.spring02"})
@EnableAspectJAutoProxy 
public class AppConfig {
	@Bean
	public IHelloService  helloService(){
		
		return new 	HelloService("zhangsan");
	}
}

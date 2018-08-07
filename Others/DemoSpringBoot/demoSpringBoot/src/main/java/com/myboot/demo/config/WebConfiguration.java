package com.myboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Bean
	 public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory){
		 MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory);
		 return mongoTemplate;
	}
}

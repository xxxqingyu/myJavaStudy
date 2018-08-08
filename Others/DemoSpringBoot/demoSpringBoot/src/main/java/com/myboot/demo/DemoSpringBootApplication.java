package com.myboot.demo;

import java.net.URL;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import net.bytebuddy.asm.Advice.This;

@SpringBootApplication()
@MapperScan("com.myboot.demo.mapper")
//@ComponentScan(basePackages={"com.myboot.demo"})
public class DemoSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
}

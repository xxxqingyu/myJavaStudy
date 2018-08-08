package com.example.demo;

import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoEurekaApplication {

	public static void main(String[] args) {
		URL name= DemoEurekaApplication.class.getClassLoader().getResource("templates/eureka/lastn.ftl");
		SpringApplication.run(DemoEurekaApplication.class, args);
	}
}

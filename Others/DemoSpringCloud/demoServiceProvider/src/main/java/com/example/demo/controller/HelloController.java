package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Value("${com.neo.title}")
	private String title;
	
    @RequestMapping("/hello")
    public String index(@RequestParam String name) {

        return "hello "+name+"，this is first messge"+this.title;
    }
    
    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    
    @RequestMapping("/user2")
    public Principal user2(){
    	SecurityContext ctx = SecurityContextHolder.getContext();
    	Authentication auth = ctx.getAuthentication();

        return (Principal)auth.getPrincipal();
    }
}
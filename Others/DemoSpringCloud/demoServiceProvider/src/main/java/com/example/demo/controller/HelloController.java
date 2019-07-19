package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
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
    
    public String getUser(Principal principal){
    	//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	//String currentPrincipalName = authentication.getName();
    	return principal.getName();
    }
}
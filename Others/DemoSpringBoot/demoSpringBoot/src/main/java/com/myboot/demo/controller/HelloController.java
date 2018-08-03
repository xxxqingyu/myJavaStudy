package com.myboot.demo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myboot.demo.config.NeoProperties;
import com.myboot.demo.domain.User;
import com.myboot.demo.mapper.UserMapper;

@RestController
public class HelloController {
	

    @Autowired
    private UserMapper UserMapper;
	
	@Resource
	NeoProperties neoProperties;
	
	@RequestMapping("/hello")
	public String index(){
		return "Hello World";
	}
	
	@RequestMapping("/getUser/{id}")
	public User getUser(@PathVariable(value="id") long id){
		User user= UserMapper.get(id);
		return user;
	}
}

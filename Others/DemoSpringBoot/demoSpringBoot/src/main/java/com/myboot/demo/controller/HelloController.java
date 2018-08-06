package com.myboot.demo.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myboot.demo.config.NeoProperties;
import com.myboot.demo.domain.User;
import com.myboot.demo.mapper.UserMapper;

@RestController
public class HelloController {
	

    @Autowired
    private UserMapper userMapper;
	
	@Resource
	NeoProperties neoProperties;
	
	@RequestMapping("/hello")
	@Cacheable(value="user-key")
	public String index(){
		SimpleDateFormat formate = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		return "Hello World" + formate.format(new Date());
	}
	
	@RequestMapping("/getUser/{id}")
	public User getUser(@PathVariable(value="id") long id){
		User user= userMapper.get(id);
		return user;
	}
	
	@RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}

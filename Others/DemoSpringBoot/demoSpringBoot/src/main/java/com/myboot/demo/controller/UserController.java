package com.myboot.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myboot.demo.domain.User;
import com.myboot.demo.mapper.UserMapper;

@Controller
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserMapper userMapper;
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
	@RequestMapping("/users")
	public String viewUser(Model model) {
		List<User> users=userMapper.getAll();
		model.addAttribute("users", users);
		return "/user/list";
	}
	
	
	@RequestMapping("/view")
	public String viewTest(Model model) {
		return "/mview2/NewFile";
	}
}

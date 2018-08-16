package demo.springMvc01.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.springMvc01.dao.domain.User;
import demo.springMvc01.dao.mapper.UserMapper;
import demo.springMvc01.service.UserService;

@RestController
public class HelloController {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/hello")
	//@Cacheable(value="user-key")
	public String index(){
		SimpleDateFormat formate = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		return "Hello World" + formate.format(new Date());
	}
	
	@RequestMapping("/test")
	public String test(){
		userService.Test();
		return "Success";
	}
	
	@RequestMapping("/getuser")
	public List<User> getUsers(){
		return userMapper.getAll();
	}
}

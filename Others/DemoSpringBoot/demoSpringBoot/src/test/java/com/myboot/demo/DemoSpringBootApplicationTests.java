package com.myboot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myboot.demo.daoAbstract.CustomerDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringBootApplicationTests {

    
    @Autowired
    private CustomerDao customerDao;
    
    
	@Test
	public void contextLoads() {
		
	}

}

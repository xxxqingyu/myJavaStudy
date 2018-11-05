package demo.springMvc01.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import demo.springMvc01.dao.domain.User;
import demo.springMvc01.dao.mapper.UserMapper;

@Service
public class UserSerivceImpl implements UserService , ApplicationContextAware {
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional("transactionManager")
	public void Test() {
		// TODO Auto-generated method stub
		User user1=new User();
		user1.setId(1);
		user1.setNickName("Hehe");
		user1.setUserName("6666");
		userMapper.update(user1);
		
		//TransactionAspectSupport.currentTransactionStatus();
		//throw new RuntimeException("sssss");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("setApplicationContext");
	}

}

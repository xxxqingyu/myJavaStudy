package com.myboot.demo.daoAbstract.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.myboot.demo.daoAbstract.CustomerDao;
import com.myboot.demo.domain.Customer;



@Component
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	  /**
     * 创建对象
     * @param user
     */
    @Override
    public void saveCustomer(Customer user) {
       // mongoTemplate.save(user);
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    //@Override
    public Customer findCustomerByName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        Customer user =  mongoTemplate.findOne(query , Customer.class);
        return user;
    }
    
    /**
     * 更新对象
     * @param user
     */
   // @Override
    public void updateCustomer(Customer user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Customer.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    /**
     * 删除对象
     * @param id
     */
    //@Override
    public void deleteCustomerById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Customer.class);
    }
}

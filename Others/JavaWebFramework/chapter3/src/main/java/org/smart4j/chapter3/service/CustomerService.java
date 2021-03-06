package org.smart4j.chapter3.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.smart_framework.helper.DatabaseHelper;


public class CustomerService {
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomerService.class);
	
	
	public List<Customer> getCustomerList(){
		
		String sql="SELECT * FROM customer";
		
		return DatabaseHelper.queryEntityList(Customer.class, sql);	
	}
	
	public Customer getCustomer(long id){
		
		String sql="SELECT * FROM customer where id=?";
		
		return DatabaseHelper.queryEntity(Customer.class, sql, id);	
	}
	
	public boolean createCustomer(Map<String,Object> fieldMap){
		return DatabaseHelper.insertEntity(Customer.class, fieldMap);
	}
	
	public boolean updateCustmer(long id,Map<String,Object> fieldMap){
		return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
	}
	
	public boolean deleteCustomer(long id){
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}

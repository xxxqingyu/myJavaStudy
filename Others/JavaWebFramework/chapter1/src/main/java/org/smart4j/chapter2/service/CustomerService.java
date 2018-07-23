package org.smart4j.chapter2.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

public class CustomerService {
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomerService.class);
	
	
	public List<Customer> getCustomerList(){
		
		String sql="SELECT * FROM customer";
		
		return DatabaseHelper.queryEntityList(Customer.class, sql);
		
	}
	
	public Customer getCustomer(long id){
		return null;
	}
	
	public boolean createCustomer(Map<String,Object> fieldMap){
		return false;
	}
	
	public boolean updateCustmer(long id,Map<String,Object> fieldMap){
		return false;
	}
	
	public boolean deleteCustomer(long id){
		return false;
	}
}

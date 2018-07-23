package org.smart4j.chapter2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	public List<Customer> getCustomerList2(){
		Connection connection=null;
		try {
			List<Customer> customerList=new ArrayList<>();
			String sql="SELECT * FROM customer where id=1;SELECT * FROM customer where id=2;SELECT * FROM customer2 where id=3";
			
			connection= DatabaseHelper.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet resultSet=statement.executeQuery();
			
			while (resultSet.next()) {
				Customer customer=new Customer();
				customer.setId(resultSet.getLong("id"));
				customer.setName(resultSet.getString("name"));
				customer.setContact(resultSet.getString("contact"));
				customer.setTelephone(resultSet.getString("telephone"));
				
				customerList.add(customer);
			}
			
			while (statement.getMoreResults()) {
				resultSet =	statement.getResultSet();
				while (resultSet.next()) {
					Customer customer=new Customer();
					customer.setId(resultSet.getLong("id"));
					customer.setName(resultSet.getString("name"));
					customer.setContact(resultSet.getString("contact"));
					customer.setTelephone(resultSet.getString("telephone"));
					customerList.add(customer);
				}
				
			}
			
			return customerList;
		} catch (Exception e) {
			LOGGER.error("execute sql failure",e);
		}finally {
			DatabaseHelper.closeConnection();
		}
		return null;
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

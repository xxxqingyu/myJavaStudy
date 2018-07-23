package org.smart4j.chapter2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.utils.PropsUtil;

public class CustomerService {
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomerService.class);
	
	
	public List<Customer> getCustomerList(){
		Connection connection=null;
		try {
			List<Customer> customerList=new ArrayList<>();
			String sql="SELECT * FROM customer";
			connection= DatabaseHelper.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				Customer customer=new Customer();
				customer.setId(resultSet.getLong("id"));
				customer.setName(resultSet.getString("name"));
				customer.setName(resultSet.getString("contact"));
				customer.setName(resultSet.getString("telephone"));
				customer.setName(resultSet.getString("email"));
				customer.setName(resultSet.getString("remark"));
				customerList.add(customer);
			}
			return customerList;
		} catch (Exception e) {
			LOGGER.error("execute sql failure",e);
		}finally {
			DatabaseHelper.closeConnection(connection);
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

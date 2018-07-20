package org.smart4j.chapter2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.utils.PropsUtil;

public class CustomerService {
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomerService.class);
	
	private static final String DRIVER;
	private static final String	URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	static{
		Properties conf=PropsUtil.loadProps("config.properties");
		DRIVER=conf.getProperty("jdbc.driver");
		URL=conf.getProperty("jdbc.url");
		USERNAME=conf.getProperty("jdbc.username");
		PASSWORD=conf.getProperty("jdbc.password");
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver",e);
		}
	}
	
	public List<Customer> getCustomerList(){
		Connection connection=null;
		try {
			List<Customer> customerList=new ArrayList<>();
			String sql="SELECT * FROM customer";
			connection= DriverManager.getConnection(URL);
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				Customer customer=new Customer();
				customer.setId(resultSet.getLong("id"));
				customerList.add(customer);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
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

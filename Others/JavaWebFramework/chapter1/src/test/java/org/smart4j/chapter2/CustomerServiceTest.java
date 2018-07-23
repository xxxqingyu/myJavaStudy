package org.smart4j.chapter2;

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CustomerServiceTest {
	private final CustomerService customerService;

	public CustomerServiceTest() {
		super();
		this.customerService = new CustomerService();
	}
	
	@Before
	public void init(){
		// TODO 初始化数据库
	}
	
	@Test
	public void getCustomerListTest() throws Exception{
		List<Customer> customerList=customerService.getCustomerList2();
		Assert.assertEquals(3, customerList.size());
	}
	
	@Test
	public void getCustomerTest() throws Exception {
		long id=1;
		Customer customer=customerService.getCustomer(id);
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void createCustomerTest()  throws Exception{
		Map<String,Object> fieldMap=new  HashMap<>();
		fieldMap.put("name", "customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "13578965485");
		boolean result=customerService.createCustomer(fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void updateCustomerTest() throws Exception {
		long id=1;
		Map<String, Object> fieldMap=new HashMap<>();
		fieldMap.put("contact", "Eric");
		boolean result=customerService.updateCustmer(id, fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		long id=1;
		boolean result=customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
}

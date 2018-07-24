package org.smart4j.chapter2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet(description = "进入 客户列表 界面", urlPatterns = { "/customer" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(){
    	customerService=new CustomerService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Customer> customers=customerService.getCustomerList();
		request.setAttribute("customerList", customers);
		request.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(request, response);
	}

}

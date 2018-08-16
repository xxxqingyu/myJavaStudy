package demo.spring02.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import demo.spring02.service.IHelloService;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class HelleServlet
 */
@WebServlet("/hello")
public class HelleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplicationContext webApplicationContext;

    /**
     * Default constructor. 
     */
    public HelleServlet() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init() throws ServletException {
		 // ªÒ»°WebApplicationContext
        ServletContext application = getServletContext();
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(application);	
		// TODO Auto-generated method stub
		super.init();
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currentTime=dateFormat.format(new Date());
//		req.setAttribute("currentTime", currentTime);
//		req.getRequestDispatcher("/WEB-INF/view/hello.jsp").forward(req,resp);
		// TODO Auto-generated method stub
	    IHelloService service =	webApplicationContext.getBean(IHelloService.class);
	    
	    String msg = service.sayHello();
		
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

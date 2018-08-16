package demo.spring02.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class WebConfigInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
	     // Create the 'root' Spring application context
	     AnnotationConfigWebApplicationContext rootContext =
	       new AnnotationConfigWebApplicationContext();
	     rootContext.register(AppConfig.class);
	     
	     //XmlWebApplicationContext 
	     // Manage the lifecycle of the root application context
	     servletContext.addListener(new ContextLoaderListener(rootContext));
	
	     
	    // Register and map the dispatcher servlet
//	     ServletRegistration.Dynamic dispatcher =
//	    		 servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
//	     dispatcher.setLoadOnStartup(1);
//	     dispatcher.addMapping("/");
	}

}

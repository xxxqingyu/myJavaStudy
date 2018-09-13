package dubbo_demo.dubbo_demo_provider3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App3 
{
    public static void main( String[] args ) throws Exception
    {	 
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboConfiguration.class);
    	
         context.start();
         System.in.read();
    }
}

package dubbo_demo.dubbo_demo_provider2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App2 
{
    public static void main( String[] args ) throws Exception{
    	 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:provider.xml"});
         context.start();
         System.in.read();
    }
}

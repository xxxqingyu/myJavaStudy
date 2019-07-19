package dubbo_demo.dubbo_demo_customer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dubbo_demo.dubbo_demo_api.DemoService;

/**
 * Hello world!
 *
 */
public class Consumer
{
    public static void main( String[] args ) throws Exception
    {
    	 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
         context.start();
         DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
         System.out.println( "-----------------------------------" ); 
         while (true) {
             try {
                 Thread.sleep(1000);
                 String hello = demoService.sayHello("world"); // call remote method
                 System.out.println(hello); // get result
             } catch (Throwable throwable) {
                 throwable.printStackTrace();
             }
         }
    }
}

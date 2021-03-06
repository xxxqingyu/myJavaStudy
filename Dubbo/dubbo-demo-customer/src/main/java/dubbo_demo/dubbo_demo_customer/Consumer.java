package dubbo_demo.dubbo_demo_customer;

import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;

import dubbo_demo.dubbo_demo_api.Demo2Service;
import dubbo_demo.dubbo_demo_api.DemoService;

/**
 * Hello world!
 *
 */
public class Consumer
{
    public static void main( String[] args ) throws Exception
    {
    	 //Future<Consumer> fooFuture = RpcContext.getContext().getFuture();
    	
    	 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
         context.start();
         Demo2Service demo2Service = (Demo2Service)context.getBean("demo2Service"); // 获取远程服务代理
         System.out.println( "-----------------------------------" ); 
         while (true) {
             try {
                 Thread.sleep(1000);
                 String hello = demo2Service.sayHello2("world"); // call remote method
                 System.out.println(hello); // get result
             } catch (Throwable throwable) {
                 throwable.printStackTrace();
             }
         }
    }
}

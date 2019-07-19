package dubbo_demo.dubbo_demo_provider2;

import dubbo_demo.dubbo_demo_api.DemoService;

public class DemoServiceImpl2 implements DemoService {

	public String sayHello(String name) {
		return "Hello2 " + name;
	}

}

package dubbo_demo.dubbo_demo_provider;

import dubbo_demo.dubbo_demo_api.DemoService;

public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		return "Hello " + name;
	}

}

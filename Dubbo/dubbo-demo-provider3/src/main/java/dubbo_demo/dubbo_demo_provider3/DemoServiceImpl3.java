package dubbo_demo.dubbo_demo_provider3;

import com.alibaba.dubbo.config.annotation.Service;

import dubbo_demo.dubbo_demo_api.DemoService;

@Service(timeout = 5000)
public class DemoServiceImpl3 implements DemoService {

	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "Hello3 " + name;
	}

}

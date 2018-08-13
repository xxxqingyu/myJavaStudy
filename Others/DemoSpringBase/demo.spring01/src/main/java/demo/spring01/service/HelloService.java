package demo.spring01.service;

public class HelloService implements IHelloService {

	private String name;
	
	public String sayHello() {
		// TODO Auto-generated method stub
		return "Hello" + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

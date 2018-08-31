package main;

import java.util.Collection;

public class program {

	public static void main(String[] args) {
		System.out.println("dd  World!");
		// TODO Auto-generated method stub
		demo_ArrayList.Test();
		//system.exit(0)
	}
	
	public static void testWildCards(Collection<? extends Base> collection){
		//collection.add(new Sub());
	}
	
	public static void testSuper(Collection<? super Sub> para){
		   para.add(new Sub());//编译通过
		  // para.add(new Base());//编译不通过
	}
	
	static class Base{}

	static class Sub extends Base{}
}

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
		   para.add(new Sub());//����ͨ��
		  // para.add(new Base());//���벻ͨ��
	}
	
	static class Base{}

	static class Sub extends Base{}
}

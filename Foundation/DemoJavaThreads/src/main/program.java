package main;

import java.util.concurrent.CyclicBarrier;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		program.Test();
		program ins=new program();
		ins.Test();

	}
	
	public static void Test(){
		System.out.println("test");
	}
	
}

class CyclicBarrierTest{
	
	public static void Test(){
		CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
	}
}

//CountDownLatch
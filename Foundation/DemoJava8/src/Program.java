import java.util.concurrent.Callable;

import main.demo_ArrayList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Program {

	public static void main(String[] args) throws Exception {
		testCallable();
		
		int s = 10;
		// TODO Auto-generated method stub
		Operator addOperator = (a, b) -> a + b + s;
		
		System.out.println("10 + 5 = " + addOperator.calcuate(10, 5));
		
		Test1 test=new Test1(11,21);
		test.calcuate(AddOperator::calcuate);
	}
	
	public static void testCallable() throws Exception{
		long a=10;
		Callable<Long> call=() -> a + 10;
		FutureTask<Long> futureTask = new FutureTask<Long>(call);
        Executor executor=Executors.newSingleThreadExecutor();
        executor.execute(futureTask);
        
        System.out.println(futureTask.get());
	}
	
}

class Test1{
	private int x;
	private int y;
	
	
	
	public Test1(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public void calcuate(Operator operator){
		System.out.println("Test1.calcuate:"+ x +" + "+y+" = "+ operator.calcuate(x, y));
	}
}

class AddOperator{
	public  static int calcuate(int x, int y){
		return x + y ;
	}
}

interface Operator {
	int calcuate(int x, int y);
}

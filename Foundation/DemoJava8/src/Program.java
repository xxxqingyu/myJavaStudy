import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Program {

	public static void main(String[] args) throws Exception {
		
		Integer  a =1;
		Integer  b =2;
		Integer  c =3;
		Integer  d =4;
		Integer  e =321;
		Integer  f =321;
		Integer  h =320;
		
		Long g= 3L;
		
		System.out.println(c==d);
		System.out.println(e==f);
		System.out.println(e==(h+a));
		System.out.println(c==(a+b));
		System.out.println(c.equals(a+b));
		
		return;
		
		//testCallable();
		
		//int s = 10;
		// TODO Auto-generated method stub
		//Operator addOperator = (a, b) -> a + b + s;
		
		//System.out.println("10 + 5 = " + addOperator.calcuate(10, 5));
		
		//Test1 test=new Test1(11,21);
		//test.calcuate(AddOperator::calcuate);
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

package main;

public class productor extends Thread {
	final int MAX_PRODUCT=1000;
	private int product;
	
	@Override
	public void run(){
		
	}
	
	/**
	   * 生产者生产出来的产品交给店员
	   */
	  public synchronized void produce()
	  {
	      if(this.product >= MAX_PRODUCT)
	      {
	          try
	          {
	              wait();  
	              System.out.println("产品已满,请稍候再生产");
	          }
	          catch(InterruptedException e)
	          {
	              e.printStackTrace();
	          }
	          return;
	      }

	      this.product++;
	      System.out.println("生产者生产第" + this.product + "个产品.");
	      notifyAll();   //通知等待区的消费者可以取出产品了
	  }

}

package main;

public class productor extends Thread {
	final int MAX_PRODUCT=1000;
	private int product;
	
	@Override
	public void run(){
		
	}
	
	/**
	   * ���������������Ĳ�Ʒ������Ա
	   */
	  public synchronized void produce()
	  {
	      if(this.product >= MAX_PRODUCT)
	      {
	          try
	          {
	              wait();  
	              System.out.println("��Ʒ����,���Ժ�������");
	          }
	          catch(InterruptedException e)
	          {
	              e.printStackTrace();
	          }
	          return;
	      }

	      this.product++;
	      System.out.println("������������" + this.product + "����Ʒ.");
	      notifyAll();   //֪ͨ�ȴ����������߿���ȡ����Ʒ��
	  }

}

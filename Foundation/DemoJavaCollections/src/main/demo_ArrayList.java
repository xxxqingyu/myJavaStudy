package main;

import java.util.ArrayList;

/***
 * ArrayList 一中可以动态增长和缩减的索引序列
 * @author Administrator
 *
 */
public class demo_ArrayList {
	public static void Test(){
		ArrayList list=new ArrayList();
		list.add(1);
		list.add("_2");
		
		ArrayList<Integer> c = new ArrayList<Integer>();
		
		c.add(1111);
		
		ArrayList d=c;
		
		d.add("--2222");
		
		print(c);
	}
	
	public static void print(ArrayList list){
		for(Object x: list ){
			System.out.println(x.toString());
		}
	}
}

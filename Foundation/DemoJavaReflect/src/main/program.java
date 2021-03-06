package main;

import java.lang.reflect.*;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 获取class
			Class<?> clazz = Class.forName("main.Person");

			System.out.println("方法信息：");
			Method[] method = clazz.getDeclaredMethods();
			for (Method m : method) {
				System.out.println(m.toString());
			}

			// 获取Person类的所有成员属性信息
			System.out.println("成员属性信息：");
			Field[] field = clazz.getDeclaredFields();
			for (Field f : field) {
				System.out.println(f.toString());
				if (f.isAnnotationPresent(FruitName.class)) {
	                FruitName fruitName = (FruitName) f.getAnnotation(FruitName.class);
	                System.out.println(fruitName);
	            }
			}
			
			

			// 获取Person类的所有构造方法信息
			System.out.println("构造方法信息：");
			Constructor<?>[] constructor = clazz.getDeclaredConstructors();
			for (Constructor c : constructor) {
				System.out.println(c.toString());
			}
			
			 //获得指定方法参数泛型信息
            Method m = clazz.getMethod("GetBook",String.class);
            Type[] t = m.getGenericParameterTypes();
            for (Type paramType : t) {
                System.out.println("#"+paramType);
                if(paramType instanceof ParameterizedType){
                    //获取泛型中的具体信息
                    Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();
                    for (Type genericType : genericTypes) {
                        System.out.println("泛型类型："+genericType);
                    }
                }
            }   
            

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

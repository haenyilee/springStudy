package com.haeni.di4;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		
		// 각 클래스에 @Component붙었는지 확인해서 메모리 할당하기
		try {
			List<String> list= new ArrayList<String>();
			list.add("com.haeni.di4.A");
			list.add("com.haeni.di4.B");
			list.add("com.haeni.di4.C");
			
			for(String s:list)
			{
				Class clsName=Class.forName(s);
				if(clsName.isAnnotationPresent(Component.class)==false)
					continue;
				Object obj=clsName.newInstance();
				System.out.println(obj);
			}
		} catch (Exception e) {}

	}

}

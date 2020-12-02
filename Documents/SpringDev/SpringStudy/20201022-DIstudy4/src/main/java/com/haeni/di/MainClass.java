package com.haeni.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String[] xml= {"app1.xml","app2.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext(xml);
		// new ClassPathXmlApplicationContext({"app1.xml","app2.xml"});로 코딩해도 무관하다
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println("이름:"+sa.getName());
		System.out.println("부서:"+sa.getDept());
		System.out.println("직위:"+sa.getJob());
		Member mem=app.getBean("mem",Member.class); // 데이터형을 통일하는 제네릭스 방식이다.
		//Member mem=(Member)app.getBean("mem",Member.class); // 데이터형을 맞춰주는 또 다른 방식이다.
		System.out.println("이름:"+mem.getName());
		System.out.println("주소:"+mem.getAddr());
		System.out.println("전화:"+mem.getTel());

	}

}

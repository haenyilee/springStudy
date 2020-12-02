package com.sist.di3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app1.xml");
		MemberList ml=(MemberList)app.getBean("ml");
		// MemberList ml=app.getBean("ml",MemberList.class);
		ml.print();
	}

}
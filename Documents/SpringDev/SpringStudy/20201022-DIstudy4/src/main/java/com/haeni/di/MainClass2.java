package com.haeni.di;

import javax.servlet.jsp.jstl.core.Config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass2 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=
				new AnnotationConfigApplicationContext(Config1.class,Config2.class);
		Sawon s=(Sawon)app.getBean("sawon");
		System.out.println();
		Member m=(Member)app.getBean("mem");
	}
	
}

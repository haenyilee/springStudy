package com.haeni.di3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app3.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		mc.dao.
	}

}

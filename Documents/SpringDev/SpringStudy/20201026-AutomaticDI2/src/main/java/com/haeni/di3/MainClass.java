package com.haeni.di3;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("mc")
public class MainClass {
	@Resource(name="sa")
	private Sawon sa;
	
	@Resource(name="mem")
	private Member mem;
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app3.xml");
		MainClass mc=(MainClass)app.getBean("mc");
		mc.sa.print();
		mc.mem.print();
	}
}

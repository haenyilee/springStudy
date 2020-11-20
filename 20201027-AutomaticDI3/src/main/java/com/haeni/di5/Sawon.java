package com.haeni.di5;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Sawon implements InitializingBean,DisposableBean {
	private String name;
	private String dept;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setterDI(setName())");
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		System.out.println("setterDI(setDept())");
		this.dept = dept;
	}

	
	
	public Sawon(String name, String dept)
	{
		System.out.println("생성자 호출(sawon(String name,String dept)");
		this.name=name;
		this.dept=dept;
	}
	public void print()
	{	
		System.out.println("프로그래머가 활용 : print()");
		System.out.println("이름:"+name);
		System.out.println("부소:"+dept);
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("객체 소멸");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("setterDI시작");
	}

}

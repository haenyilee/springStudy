package com.haeni.di3;

import org.springframework.stereotype.Component;

@Component("mem")
public class Member {
	private String name="심청이";
	private String addr="서울시";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public void print()
	{
		System.out.println("이름:"+name);
		System.out.println("주소:"+addr);
	}
}	

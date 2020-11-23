package com.haeni.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config2 {
	@Bean("mem")
	public Member memberInfo()
	{
		/*
		 * map.put("sa",new Sawon())
		 * 
		 * public Object getBean(String id)
		 * {
		 * 	return map.get(id);
		 * }
		 * 
		 * Sawon sa=getBean("sa")
		 * Sawon sa=100
		 * 
		 * int a=10;
		 */
		Member s= new Member();
		
		//DI
		s.setName("춘향이");
		s.setAddr("서울시");
		s.setTel("010-2222-2222");
		
		return s;
	}
}

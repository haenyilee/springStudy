package com.haeni.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {
	@Bean("sawon")
	public Sawon sawonInfo()
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
		Sawon s= new Sawon();
		
		//DI : 기본 데이터값 설정하기
		s.setName("이순신");
		s.setDept("개발부");
		s.setJob("과");
		
		return s;
	}
}

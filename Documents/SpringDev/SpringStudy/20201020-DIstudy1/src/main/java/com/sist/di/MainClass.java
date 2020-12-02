package com.sist.di;

public class MainClass {

	public static void main(String[] args) {
		Sawon s=new Sawon();
		// 스프링이 구현 가능한 부분
		s.setName("홍길동");
		s.setSex("남자");
		s.setAge(20);
		
		s.init();
		// 스프링이 구현 가능한 부분
		
		// 값을 불러오는 것은 개발자가 담당
		s.print();

	}

}

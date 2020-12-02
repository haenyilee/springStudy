package com.haeni.di3;

import org.springframework.stereotype.Repository;

@Repository
public class Mysql implements MyDAO{

	@Override
	public void getConnection() {
		// TODO Auto-generated method stub
		System.out.println("Mysql에 연결");
		
	}

	@Override
	public void disConnection() {
		// TODO Auto-generated method stub
		System.out.println("Mysql에 해제");
		
	}

}

package com.haeni.di3;

import org.springframework.stereotype.Repository;

@Repository
public class Oracle implements MyDAO{

	@Override
	public void getConnection() {
		// TODO Auto-generated method stub
		System.out.println("오라클연결");
		
	}

	@Override
	public void disConnection() {
		// TODO Auto-generated method stub
		System.out.println("오라클 연결 해제");
	}

}

package com.haeni.di2;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
	@Autowired
	private MusicDAO dao;
	
	@Autowired
	private MovieDAO mDao;
	
	public static void main(String[] args) {
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app2.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		
		// 음악 데이터 출력
		List<MusicVO> list=mc.dao.musicListData();
		for(MusicVO vo:list)
		{
			System.out.println("번호:"+vo.getMno());
			System.out.println("노래명:"+vo.getTitle());
			System.out.println("가수명:"+vo.getSinger());
			System.out.println("앨범:"+vo.getAlbum());
			System.out.println("=============================");
		}
		
		// 영화 데이터 출력
		System.out.println("============영화목록=============");
		List<MovieVO> mList=mc.mDao.movieListData();
		for(MovieVO vo:mList)
		{
			System.out.println("번호:"+vo.getNo());
			System.out.println("영화명:"+vo.getTitle());
			System.out.println("출연:"+vo.getActor());
			System.out.println("감독:"+vo.getDirector());
		}

	}

}

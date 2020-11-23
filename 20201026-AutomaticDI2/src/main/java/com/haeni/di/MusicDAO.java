package com.haeni.di;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicDAO extends SqlSessionDaoSupport {
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<MusicVO> musicListData()
	{
		return getSqlSession().selectList("musicListData");
	}
}

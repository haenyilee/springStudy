package com.haeni.di;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;

public class MusicDAO extends SqlSessionDaoSupport{
	public List<MusicVO> musicAllData()
	{
		return getSqlSession().selectList("musicAllData");
		
	}
}

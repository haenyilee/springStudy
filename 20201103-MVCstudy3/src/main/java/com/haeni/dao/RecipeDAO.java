package com.haeni.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

// 스프링에서 메모리 할당 요청 
@Repository
public class RecipeDAO extends SqlSessionDaoSupport {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	// 레시피 전체 목록 데이터
	public List<RecipeVO> recipeListData(Map map) {
		return getSqlSession().selectList("recipeListData", map);
	}

	// 레시피 전체 페이지 데이터
	public int recipeTotalPage() {
		return getSqlSession().selectOne("recipeTotalPage");
	}

	// 쉐프 목록 데이터
	public List<RecipeVO> recipeChefData(Map map) {
		return getSqlSession().selectList("recipeListData", map);
	}

	// 쉐프 전체 페이지 데이터
	public int recipeChefTotalPage() {
		return getSqlSession().selectOne("recipeTotalPage");
	}

}

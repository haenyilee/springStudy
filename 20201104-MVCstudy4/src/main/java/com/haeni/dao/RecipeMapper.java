package com.haeni.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface RecipeMapper {
	
	@Select("SELECT no,title,chef,poster,num "
			+ "FROM (SELECT no,title,chef,poster,rownum as num "
			+ "FROM (SELECT no,title,chef,poster "
			+ "FROM recipe)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	// 구현하는 메소드 지정
	public List<RecipeVO> recipeListData(Map map);
	
/*
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.sist.dao.recipe-mapper">
	
	  <select id="recipeListData" resultType="RecipeVO" parameterType="hashmap">
	    SELECT no,title,chef,poster,num 
	    FROM (SELECT no,title,chef,poster,rownum as num 
	    FROM (SELECT no,title,chef,poster 
	    FROM recipe))
	    WHERE num BETWEEN #{start} AND #{end}
	  </select>
	  
	  <!-- 총페이지  -->
	  <select id="recipeTotalPage" resultType="int">
	    SELECT CEIL(COUNT(*)/20.0) FROM recipe
	  </select>
	  
	  <!-- chef 데이터 -->
	  <select id="recipeChefData" resultType="RecipeVO" parameterType="hashmap">
	    SELECT no,title,chef,poster,num 
	    FROM (SELECT no,title,chef,poster,rownum as num 
	    FROM (SELECT no,title,chef,poster 
	    FROM recipe WHERE chef=#{chef}))
	    WHERE num BETWEEN #{start} AND #{end}
	  </select>
	  
	  <select id="recipeChefTotalPage" resultType="int" parameterType="string">
	    SELECT CEIL(COUNT(*)/20.0) FROM recipe
	    WHERE chef=#{chef}
	  </select>
	</mapper>

 */
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
	public int recipeTotalPage();
}

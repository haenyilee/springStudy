package com.haeni.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map)
	{
		return mapper.recipeListData(map);
	}
	public int recipeTotalpage()
	{
		return mapper.recipeTotalPage();
	}
	

}

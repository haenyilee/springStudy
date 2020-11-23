package com.haeni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.haeni.dao.*;

@Controller
public class RecipeController {
	// 스프링에서 생성된 DAO 객체 얻기
	@Autowired
	private RecipeDAO dao;
	
	// 사용자 요청 처리 
	@RequestMapping("recipe/list.do")
	public String recipe_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 데이터 받기 => 스프링에서 메모리 할당=>한개만 저장 (재사용) = 싱글턴
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		// Map에서 채워서 => DAO로 전송 
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<RecipeVO> list=dao.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>22)
			{
				title=title.substring(0,22);
				title+="...";
			}
			vo.setTitle(title);
		}
		// 총페이지 
		int totalpage=dao.recipeTotalpage();
		
		// 전송 
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		return "recipe/list";// forward => list.jsp=>request를 전송 
	}
	
}

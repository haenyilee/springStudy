package com.haeni.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.ls.LSInput;

import com.sist.dao.ChefVO;
//import com.haeni.dao.RecipeDAO;
//import com.haeni.dao.RecipeVO;
import com.sist.dao.RecipeDAO;
import com.sist.dao.RecipeVO;

@Controller
public class RecipeController {
	@Autowired
	private RecipeDAO dao;
	
	// 사용자 요청과 처리 메소드 연결
	@RequestMapping("recipe/list.do")
	public String recipe_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		// Map에 채워서 DAO로 전송
		Map map= new HashMap();
		map.put("start", start);
		map.put("end",end);
		List<RecipeVO> list=dao.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>25)
			{
				title=title.substring(0,25);
				title+="...";
			}
			vo.setTitle(title);
		}
		// 총 페이지 받기
		int totalpage=dao.recipeTotalPage();
		
		// 전송 시작
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		return "recipe/list"; // forward : list.jsp로 request전송
	}
	
	@GetMapping("recipe/chef.do")
	public String recipe_chef(String page,String chef,Model model)
	{
		// db연동
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage) - (rowSize-1);
		int end =rowSize*curpage;
		Map map= new HashMap();
		map.put("start", start);
		map.put("end",end);
		map.put("chef", chef);
		List<RecipeVO> list=dao.recipeChefData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>25)
			{
				title=title.substring(0,25);
				title+="...";
			}
			vo.setTitle(title);
		}
		
		int totalpage=dao.recipeChefTotalPage(chef);
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("chef",chef);
		model.addAttribute("list",list);
		return "recipe/chef";
	}
	
	
}

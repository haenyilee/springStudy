package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class ReactController {
	@Autowired
	private RecipeDAO dao;
	
	@RequestMapping(value="react_chef/chef_list.do",produces="text/plain;charset=UTF-8") // value는 생략가능함
	public String chef_list(String page)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<ChefVO> list = dao.chefListData(curpage);
		String json="";
		try {
			// [{},{},{}]
			JSONArray arr = new JSONArray();
			for(ChefVO vo:list)
			{
				JSONObject obj = new JSONObject();
				obj.put("poster", vo.getPoster());
				obj.put("chef",vo.getChef() );
				obj.put("mc1", vo.getMc1());
				obj.put("mc2", vo.getMc2());
				obj.put("mc3", vo.getMc3());
				obj.put("mc7", vo.getMc7());
				
				arr.add(obj);
			}
			
			json=arr.toJSONString();
			
		} catch (Exception e) {
		}
		return json;
	}
	@RequestMapping("react_chef/totalpage.do")
	public String chef_total()
	{
		int total=0;
		try{
			total=dao.chefTotalPage();
		}
		catch(Exception ex){
		}
		return String.valueOf(total);
	}
	
	@RequestMapping(value="react_chef/chef_detail.do",produces="text/plain;charset=UTF-8") // value는 생략가능함
	public String chef_detail(String chef,String page)
	{
		String result="";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<RecipeVO> list= dao.chefProductData(chef, curpage);
		try {
			JSONArray arr = new JSONArray();
			// [{},{},{},{}] => 데이터 3개만 출력(poster, chef, title)
			for(RecipeVO vo:list)
			{
				JSONObject obj = new JSONObject();
				obj.put("poster", vo.getPoster());
				obj.put("chef", vo.getChef());
				obj.put("title", vo.getTitle());
				
				arr.add(obj);
			}
			result= arr.toString();
		} catch (Exception e) {
		}
		return result;
	}
	@RequestMapping(value="react_chef/chef_find.do",produces="text/plain;charset=UTF-8") // value는 생략가능함
	public String chef_find(String chef,String fd)
	{
		List<RecipeVO> list = dao.chefProductFindData(chef, fd);
		
		List<RecipeVO> list= dao.chefProductData(chef, curpage);
		try {
			JSONArray arr = new JSONArray();
			// [{},{},{},{}] => 데이터 3개만 출력(poster, chef, title)
			for(RecipeVO vo:list)
			{
				JSONObject obj = new JSONObject();
				obj.put("poster", vo.getPoster());
				obj.put("chef", vo.getChef());
				obj.put("title", vo.getTitle());
				
				arr.add(obj);
			}
			result= arr.toString();
		} catch (Exception e) {
		}
		return json;
	}
}

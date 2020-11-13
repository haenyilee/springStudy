package com.haeni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.haeni.dao.MusicDAO;
import com.haeni.dao.MusicVO;

import java.util.*;

@Controller
public class MusicController {
	@Autowired
	private MusicDAO dao;
	// get방식 => 
	@GetMapping("music/list.do")
	public String music_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize=10;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<MusicVO> list = dao.musicListData(map);
		
		int totalpage= dao.musicTotalPage();
		
		// 전송
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		return "music/list";
	}
}

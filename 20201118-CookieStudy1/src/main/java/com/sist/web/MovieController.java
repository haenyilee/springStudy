package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
@Controller
public class MovieController {
	@Autowired
	private MovieDAO dao;
	@RequestMapping("movie/list.do")
	public String movie_list(Model model, HttpServletRequest request)
	{
		List<MovieVO> list = dao.movieListData();
		model.addAttribute("list",list);
		List<MovieVO> cList=new ArrayList<MovieVO>();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		{
			// 최신 쿠키부터 출력하기 위해 for문을 역순으로 진행함
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("m"))
				{
					MovieVO vo = dao.movieDetailData(Integer.parseInt(cookies[i].getValue()));
					cList.add(vo);
				}
			}
		}
		model.addAttribute("cList",cList);
		return "movie/list";
	}
	@RequestMapping("movie/detail_before.do")
	public String movie_detail_before(int no,RedirectAttributes ra,HttpServletResponse response)
	{
		Cookie cookie = new Cookie("m"+no, String.valueOf(no));
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); // 클라이언트에 저장
		ra.addFlashAttribute("no",no); // POST방식으로 값 전송하기
		return "redirect:detail.do";
	}
	
	@RequestMapping("movie/detail.do")
	public String movie_detail(int no,Model model)
	{
		MovieVO vo= dao.movieDetailData(no);
		model.addAttribute("vo",vo);
		return "movie/detail";
	}
}

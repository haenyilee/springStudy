package com.sist.music;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request)
	{
		request.setAttribute("msg", "Hello Spring");
		return "main/main";
	}

}

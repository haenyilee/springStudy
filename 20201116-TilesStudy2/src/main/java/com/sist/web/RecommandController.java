package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RecommandController {
	@RequestMapping("recommand/list.do")
	public String Recommand_list()
	{
		return "recommand/list";
	}
}

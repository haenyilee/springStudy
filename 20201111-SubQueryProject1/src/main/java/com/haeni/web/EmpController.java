package com.haeni.web;
import com.haeni.dao.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmpController {
	@Autowired
	private EmpDAO dao;
	@RequestMapping("emp/list2.do")
	public String emp_list(Model model)
	{
		List<EmpVO> list=dao.empListData();
		model.addAttribute("list",list); // request.setAttribute("list",list)
		return "list2";
	}
	@RequestMapping("emp/sublist.do")
	public String emp_sublist(String ename,Model model)
	{
		if(ename==null)
			ename="KING";
		List<EmpVO> list = dao.empGroupData(ename);
		model.addAttribute("list",list);
		return "sublist";
	}
}

package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Service => 사용자 요청을 받아서 => 데이터베이스 연동 => JSP로 결과값 전송 (Model)
/*
 *    MVC  ==> struts : Action 
 *    = Model  (java) => Service    (Spring : Controller 요청을 제어하는 프로그램)
 *    = View   (jsp)  => 화면 출력          (Spring : JSP)
 *    = Controller    => Model+JSP  (Spring : DispatcherServlet : Front Controller)
 *                                   요청을 받아서 Model+JSP를 연결해주는 역할)
 */
// Model 처리 
/*
 *      <context:component-scan base-package="com.sist.*"/>
 *                                           =============
 *                                           class를 찾는다 
 *                                           @Controller
 *                                           @Repository
 *                                           @Service
 *                                           @RestController
 *                                           @Component
 */
import java.util.*;
import com.sist.dao.*;
@Controller
public class EmpController {
	 @Autowired
     private EmpDAO dao;
	 @RequestMapping("emp/list2.do")
	 public String emp_list(Model model)
	 {
		 List<EmpVO> list=dao.empListData();
		 model.addAttribute("list", list); // request.setAttribute("list",list)
		 return "list2";
	 }
	 @RequestMapping("emp/sublist.do")
	 public String emp_sublist(String ename,Model model)
	 {
		 if(ename==null)
			 ename="KING";
		 List<EmpVO> list=dao.empGroupData(ename);
		 model.addAttribute("list", list);
		 return "sublist";
	 }
}























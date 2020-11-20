package com.sist.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.board.dao.DBConnection;

@Aspect
//<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
@Component
public class DBAspect {
   @Autowired
   private DBConnection dbCon;
   
   // 연결
   @Before("execution(* com.sist.board.dao.BoardDAO.board*(..))||execution(* com.sist.board.dao.BoardDAO.reply*(..))")
   public void before()
   {
	   // 객체 주소 받기
	   //System.out.println("dbCon:"+dbCon);
	   dbCon.getConnection();
   }
   // 해제
   @After("execution(* com.sist.board.dao.BoardDAO.board*(..))||execution(* com.sist.board.dao.BoardDAO.reply*(..))")
   public void after()
   {
	   dbCon.disConnection();
   }
   // 예외처리
   @AfterThrowing(value="execution(* com.sist.board.dao.BoardDAO.board*(..))||execution(* com.sist.board.dao.BoardDAO.reply*(..))",throwing="e")
   public void afterThrowing(Throwable e)
   {
	   e.printStackTrace();
   }
   // 리턴값
   @AfterReturning(value="execution(* com.sist.board.dao.BoardDAO.board*(..))||execution(* com.sist.board.dao.BoardDAO.reply*(..))",returning="obj")
   public void afterReturning(Object obj)
   {
	   System.out.println("obj="+obj);
   }
}











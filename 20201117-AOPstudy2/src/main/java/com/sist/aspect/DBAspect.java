package com.sist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sist.dao.*;
@Aspect
@Component
public class DBAspect {
	@Autowired
	private DBConnection dbConn;
	
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void before()
	{
		dbConn.getConnection();
	}
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void after()
	{
		dbConn.disConnection();
	}
	
	@Around("execution(* com.sist.web.EmpController.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		Object obj=null;
		System.out.println("사용자 :"+jp.getSignature().getName());
		obj=jp.proceed(); // 메소드 호출 부분
		System.out.println("사용자 호출 후 : "+jp.getSignature().getName());
		return obj;
	}
	
	@AfterReturning(value="execution(* com.sist.web.EmpController.*(..))",returning="val")
	public void afterReturning(JoinPoint jp,Object val)
	{
		System.out.println("리턴값:" +val);
	}
	
	@AfterThrowing(value="execution(* com.sist.web.EmpController.*(..))",throwing="e")
	public void afterThrowing(Throwable e)
	{
		System.out.println(e.getMessage());
	}
	
	
	
}

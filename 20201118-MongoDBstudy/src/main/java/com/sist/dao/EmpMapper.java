package com.sist.dao;

import org.apache.ibatis.annotations.Select;
/*
 *  SubQuery : SQL문장을 하나 묶어서 사용 => JOIN , 복잡한 쿼리 문장이 있는 경우 , SQL문장이 많이 수행..
 *  = 종류 
 *    ===============
 *    1. 단일행 서브쿼리 
 *    2. 다중행 서브쿼리
 *    =============== 조건문 사용 (WHERE ~)
 *    3. 스칼라 서브쿼리
 *    =============== 컬럼 대체
 *    4. 인라인 뷰 (Top-N) 
 *    ================ 테이블 대체
 *   = 실행 
 *     1. 서브쿼리가 실행 ==> 결과값 메인쿼리 전송 
 *        예)  SELECT ~~ 
 *            FROM table_name
 *            WHERE 컬럼명 연산자 (SELECT ~~) ==> 서브쿼리는 먼저 수행 => 반드시 ()
 *                            ==========
 *                            결과 1
 *                            결과 여러개  (IN , ANY , ALL)
 *                            SELECT deptno FROM dept
 *                            deptno>ANY => 최소값 ANY(SELECT deptno FROM dept)
 *                                         ANY(10,20,30,40) => 10
 *                            deptno<ANY => 최대값
 *                                         ANY(10,20,30,40) => 40
 *                            >ALL => 최대값 ALL(SELECT deptno FROM dept)
 *                                      ALL(10,20,30,40) => 40
 *                            <ALL => 최소값
 *                                      ALL(10,20,30,40) => 10
 *                            ===============================================
 *                            SELECT MIN(deptno) FROM dept
 *                            SELECT MAX(deptno) FROM dept
 *                            IN(10,20,30,40)
 */
import java.util.*;
public interface EmpMapper {
  @Select("SELECT empno,ename,job,hiredate,sal,e.deptno,"
		 +"(SELECT dname FROM dept d WHERE e.deptno=d.deptno) as dname,"
		 +"(SELECT loc FROM dept d WHERE e.deptno=d.deptno) as loc "
		 +"FROM emp e")
  public List<EmpVO> empListData();
  @Select("SELECT empno,ename,job,hiredate,sal,deptno,"
		  +"(SELECT dname FROM dept d WHERE e.deptno=d.deptno) as dname,"
		  +"(SELECT loc FROM dept d WHERE e.deptno=d.deptno) as loc "
		  +"FROM emp e "
		  +"WHERE deptno=(SELECT deptno FROM emp WHERE ename=#{ename})")
  public List<EmpVO> empGroupData(String ename);
}






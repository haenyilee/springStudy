package com.haeni.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface EmpMapper {
	@Select("SELECT empno,ename,job,hiredate,sal,deptno,"
			+ "(SELECT dname FROM dept d WHERE e.deptno=d.deptno) as dname,"
			+ "(SELECT loc FROM dept d WHERE e.deptno=d.deptno) as loc "
			+ "FROM emp e")
	public List<EmpVO> empListData();
	
	@Select("SELECT empno,ename,job,hiredate,sal,deptno,"
			+ "(SELECT dname FROM dept d WHERE e.deptno=d.deptno) as dname,"
			+ "(SELECT loc FROM dept d WHERE e.deptno=d.deptno) as loc "
			+ "FROM emp e "
			+ "WHERE deptno=(SELECT deptno FROM emp e WHERE ename=#{ename})")
	public List<EmpVO> empGroupData(String ename);
	
}

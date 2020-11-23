package com.sist.temp;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sist.dao.EmpVO;
import java.util.*;

@Repository
public class MongoDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	public MongoDAO()
	{
		try {
			mc=new MongoClient("localhost",27017);
			db=mc.getDB("mydb");
			dbc=db.getCollection("emp"); // CREATE TABLE emp;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*
	 * {},
	 * {},
	 * {}
	 */
	public void empInsert(EmpVO vo)
	{
		BasicDBObject obj = new BasicDBObject();
		// vo가 아니라 obj를 통해서 값을 집어 넣는다.
		obj.put("empno", vo.getEmpno());
		obj.put("ename", vo.getEname());
		obj.put("job", vo.getJob());
		obj.put("hiredate", vo.getHiredate());
		obj.put("dname", vo.getDname());
		obj.put("loc", vo.getLoc());
		
		dbc.insert(obj);
	}
	public List<EmpVO> empListData(int page)
	{
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		// 페이징
		int rowSize=10;
		int skip=(rowSize*page)-rowSize;
		DBCursor c=dbc.find().skip(skip).limit(rowSize); 
		/* 
		 * CURSOR : SELECT * FROM 의 결과값을 받는 것이 CURSOR
		 * skip : 제한된 갯수만큼만 가져오고 버린다.	
		 */
		while(c.hasNext())
		{
			BasicDBObject obj =(BasicDBObject)c.next();
			EmpVO vo= new EmpVO();
			vo.setEmpno(obj.getInt("empno"));
			vo.setEname(obj.getString("ename"));
			vo.setJob(obj.getString("job"));
			vo.setDname(obj.getString("dname"));
			vo.setLoc(obj.getString("loc"));
			list.add(vo);
		}
		c.close();
		return list;
	}
}

package com.sist.temp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

@Component
public class MainClass {
	@Autowired
	private EmpDAO dao;
	@Autowired
	private MongoDAO mdao;
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("application-context.xml");
		MainClass mc = (MainClass) app.getBean("mainClass");
		
		// emp테이블 값 몽고DB에 넣기
		/*List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list)
		{
			mc.mdao.empInsert(vo);
		}
		System.out.println("완료!!");*/
		
		List<EmpVO> list=mc.mdao.empListData(2);// empListData의 매개변수는 page임
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "
					+vo.getJob()+" "+vo.getDname()+" "+vo.getLoc());
		}
	}

}

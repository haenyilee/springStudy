package com.sist.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import com.sist.dao.*;

@Component
public class RecommendManager {
	/*-------------------맛집이름 받기------------------*/
	@Autowired
	private FoodDAO dao;
	/*---------------------------------------------*/
	
	public List<RecommendVO> recommendData()
	{
		List<RecommendVO> list = new ArrayList<RecommendVO>();
		try {
			
			JAXBContext jb = JAXBContext.newInstance(Rss.class); // @XmlRootElement
			Unmarshaller un = jb.createUnmarshaller();
			Rss rss = (Rss) un.unmarshal(new File("c:\\upload\\recommend.xml"));
			
			List<Item> iList=rss.getChannel().getItem();
			
			/*---------------------패턴 만들기 : 데이터 사전-----------------------*/
			List<String> fList= dao.recipeTitleData();
			
			Pattern[] p = new Pattern[fList.size()];
			Matcher[] m = new Matcher[fList.size()];
			
			for(int i=0;i<p.length;i++)
			{
				p[i] =Pattern.compile(fList.get(i));
			}
			/*----------------------------------------------------*/
			
			int[] count = new int[fList.size()];
			
			/*-------------------------description을 읽어와서 ----------------*/
			for(Item item:iList)
			{
			
				
				for(int i=0;i<m.length;i++)
				{
					m[i] = p[i].matcher(item.getDescription());
					
					/*----------해당 단어를 포함하는지 확인하기(=contains)----------*/
					while(m[i].find())
					{
						String title =m[i].group();
						count[i]++;
					}
				}
			}
			
			/*-----------------------출력------------------------*/
			int j=0;
			for(String title:fList)
			{
				/*-----------------------------------------------*/
				if(count[j]!=0)
				{
					RecommendVO vo = new RecommendVO();
					vo.setCount(count[j]);
					vo.setTitle(title);
					System.out.println(title+":"+count[j]);
				}
				
				j++;
			}
				
			
		} catch (Exception e) {
		}
		return list;
	}
}

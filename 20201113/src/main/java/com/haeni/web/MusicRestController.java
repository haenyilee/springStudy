package com.haeni.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MusicRestController {
	@RequestMapping("movie2/movie.do")
	public String movie2_main(String no)
	{
		String result="{datas:";
		String url="http://www.kobis.or.kr/kobis/business/main/";
		if(no==null)
			no="1";
		int type=Integer.parseInt(no);
	   switch(type)
	   {
	   case 1:
		   url+="searchMainDailyBoxOffice.do";
		   break;
	   case 2:
		   url+="searchMainRealTicket.do";
		   break;
	   case 3:
		   url+="searchMainDailySeatTicket.do";
		   break;
	   case 4:
		   url+="searchMainOnlineDailyBoxOffice.do";
		   break;
	   }
	   try {
		   Document doc= Jsoup.connect(url).get();
		   //System.out.println(doc.toString());
		   String temp=doc.toString();
		   temp=temp.substring(temp.indexOf("["),temp.lastIndexOf("]")+1);
		   //System.out.println(temp);
		   result+=temp+"}";
		   System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	   
		return result;
	}
}

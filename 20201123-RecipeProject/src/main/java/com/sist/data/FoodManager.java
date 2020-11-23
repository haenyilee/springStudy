package com.sist.data;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
			<li>
              <img class="center-croping" alt="부산 광안리 맛집 베스트 25곳 사진"
                   data-lazy="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/la_vlgj7bh5oqani.png?fit=around|600:400&amp;crop=600:400;*,*&amp;output-format=jpg&amp;output-quality=80"/>

              <a href="/top_lists/2720_gwanganri"
                 onclick="trackEvent('CLICK_TOPLIST', {&quot;section_position&quot;:0,&quot;section_title&quot;:&quot;믿고 보는 맛집 리스트&quot;,&quot;position&quot;:0,&quot;link_key&quot;:&quot;FSCPVRH&quot;});">
                <figure class="ls-item">
                  <figcaption class="info">
                    <div class="info_inner_wrap">
                      <span class="title">부산 광안리 맛집 베스트 25곳</span>
                      <p class="desc">"광안리에 이거 먹을 갈 사람?"</p>
                      <p class="hash">
                          <span>#부산시 수영구 </span>
                          <span>#부산 수영구 </span>
                          <span>#수영구 </span>
                          <span>#부산 남구 </span>
                          <span>#부산시 남구 </span>
                          <span>#수영구청 </span>
                          <span>#광안대교 </span>
                          <span>#광안리바닷가 </span>
                          <span>#광안리해변 </span>
                          <span>#광안리 </span>
                          <span>#광안리역 </span>
                          <span>#금련산역 </span>
                          <span>#수영역 </span>
                      </p>
                    </div>
                  </figcaption>
                </figure>
              </a>
            </li>
 */
public class FoodManager {
	public void foodCategoryAllData()
	{
		FoodDAO dao = new FoodDAO();
		try {
			Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
			Elements title = doc.select("div.info_inner_wrap span.title");
			Elements poster = doc.select("ul.list-toplist-slider img.center-croping");
			Elements subject = doc.select("div.info_inner_wrap p.desc");
			Elements link = doc.select("ul.list-toplist-slider a");
			
			for(int i=0;i<title.size();i++)
			{
				System.out.println((i+1)+"번");
				System.out.println("제목:"+title.get(i).text());
				System.out.println("부제목:"+subject.get(i).text());
				System.out.println("링크:"+link.get(i).attr("href"));
				System.out.println("포스터:"+poster.get(i).attr("data-lazy"));
				FoodCategoryVO vo = new FoodCategoryVO();
				vo.setNo(i+1);
				vo.setTitle(title.get(i).text());
				
				String p= poster.get(i).attr("data-lazy");
				p=p.replace("&", "^");
				vo.setPoster(p);
				
				String s= subject.get(i).text();
				s=s.replace("\"", "");
				vo.setSubject(s);
				
				vo.setLink(link.get(i).attr("href"));
				dao.foodCategoryInsert(vo);
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		
		
	}
	
	//https://www.mangoplate.com/
	public FoodVO foodDetailData()
	{
		FoodVO vo = new FoodVO();
		FoodDAO dao = new FoodDAO();
		try {
			try {
				List<FoodCategoryVO> list = dao.foodCategoryData();
				for(FoodCategoryVO fvo:list)
				{
					String url="https://www.mangoplate.com/"+fvo.getLink();
					int cateno= fvo.getNo();
					Document doc= Jsoup.connect(url).get();
					Elements link= doc.select("figure.restaurant-item a");
					System.out.println("카테고리번호:"+cateno);
					
					for(int i=0; i<link.size();i++)
					{
						System.out.println("사이트:"+link.get(i).attr("href"));
						System.out.println("====================================");
						String url2="https://www.mangoplate.com/"+link.get(i).attr("href");
						Document doc2= Jsoup.connect(url2).get();
						Elements poster=doc2.select("img.center-croping");
						String strPoster="";
						try{
						for(int j=0;j<poster.size();j++)
						{
							String s = poster.get(j).attr("src");
							strPoster+=s+",";
						}
						strPoster=strPoster.substring(0,strPoster.lastIndexOf(","));
						}catch (Exception e) {
								strPoster="no";
						}
						Element title=doc2.selectFirst("span.title h1.restaurant_name");
						Element score=doc2.selectFirst("span.title strong.rate-point span");
						Element addr=doc2.select("tr.only-desktop td").get(0);
						Element tel=doc2.select("tr.only-desktop td").get(1);
						Element type=doc2.select("tr td span").get(2);
						
						
						String strPrice="";
						try {
							Element price=doc2.select("tr td").get(3);
							strPrice=price.text();
						} catch (Exception e) {
							strPrice="no";
						}
						String strMenu="";
						
						try {
							Elements menu=doc2.select("td.menu_td li_Restaurant_MenuItem");
							for(int j=0;j<menu.size();j++)
							{
								strMenu+=menu.get(j).text()+",";
							}
							strMenu = strMenu.substring(0,strMenu.lastIndexOf(","));
							
						} catch (Exception e) {
							strMenu="no";
						}
						
						
						
						
						/*
						 <script id="reviewCountInfo" type="application/json">
						 [{"action_value":1,"count":6},
						 {"action_value":2,"count":14},
						 {"action_value":3,"count":50}]</script>
						 */
						
						// 자바스크립트 읽어서 JSON 파싱할 때 함수 : data
						String temp=doc2.selectFirst("#reviewCountInfo").data();
						
						
						try {
							JSONParser jp=new JSONParser();
							JSONArray arr= (JSONArray)jp.parse(temp);
							for(int a=0;a<arr.size();a++)
							{
								JSONObject obj=(JSONObject)arr.get(a);
								if(a==2)
								{
									System.out.println("좋아요:"+obj.get("count"));
								}
								else if (a==1) {
									System.out.println("괜찮다:"+obj.get("count"));
								}
								else
								{
									System.out.println("별로:"+obj.get("count"));
								}
							}
						} catch (Exception e) {
							
						}
						
						System.out.println("제목:"+title.text());	
						System.out.println("주소:"+addr.text());	
						System.out.println("평점:"+score.text());	
						System.out.println("전화:"+tel.text());	
						System.out.println("종류:"+type.text());	
						System.out.println("가격:"+strPrice);	
						System.out.println("메뉴:"+strMenu);	
						System.out.println("리뷰:"+temp);	
						
						FoodVO vo2 = new FoodVO();
						vo2.setCateno(cateno);
						vo.setTitle(title.text());
						vo.setAddr(addr.text());
						vo.setScore(score.text());
						vo.setTel(tel.text());
						vo.setType(type.text());
						vo.setPrice(strPrice);
						vo.setMenu(strMenu);
						
						
					}
					
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}
		return vo;
	}
}

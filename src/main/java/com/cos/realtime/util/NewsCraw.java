package com.cos.realtime.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.realtime.domain.News;

@Component
public class NewsCraw {

	int aidNum = 1;
	
	public List<News> newsCollect() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList =new ArrayList<>();
	
	for (int i = 1; i <6; i++) {
		String aid = String.format("%010d", aidNum);
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid="+aid;
		String html = rt.getForObject(url,String.class);
		
		Document doc = Jsoup.parse(html);
	
		
		Element titleElement = doc.selectFirst("#articleTitle");
		Element createdAtElement = doc.selectFirst(".t11");
			
		String company = doc.selectFirst(".content a img").attr("alt");
		String title = titleElement.text();
		String createdAt = createdAtElement.text();
		

		String date = createdAt.substring(0, 10);
		date = date.replace(".", "-");
		String date2 = date + " 00:00:00";
		Timestamp newsdate = Timestamp.valueOf(date2);

		
		LocalDateTime t = LocalDateTime.now().minusDays(1).plusHours(9);;
		Timestamp ts = Timestamp.valueOf(t);
		
		
		
//		int compare = ts.compareTo(newsdate);
//		
//		if (compare > 0) {
//			System.out.println("ts>newsdate");
//			System.out.println(company);
//			System.out.println(title);
//			System.out.println(newsdate);
//			
//		} 
		
		
		News news = News.builder()
				.company(company)
				.title(title)
				.createdAt(newsdate)
				.build();

		newsList.add(news);
		
		aidNum ++;
	}
	return newsList;
	
	}
}

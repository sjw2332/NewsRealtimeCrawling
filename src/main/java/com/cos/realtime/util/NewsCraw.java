package com.cos.realtime.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.realtime.domain.News;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Component
public class NewsCraw {

	private int aidNum = 277493;
	//https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=0000277493
	public List<News> newsCollect() throws ParseException {
		RestTemplate rt = new RestTemplate();
		List<News> newsList =new ArrayList<>();
		
	try {
	
		for (int i = 1; i <100; i++) {
		String aid = String.format("%010d", aidNum);
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid="+aid;
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
		SimpleDateFormat date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newsDate = date3.parse(date2);

		
//		LocalDateTime t = LocalDateTime.now().minusDays(1).plusHours(9);;
//		Timestamp ts = Timestamp.valueOf(t);
		
		
		Date today = new Date();
		SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crawStart = todayFormat.format(today);
		SimpleDateFormat crawTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = crawTime.parse(crawStart);
		
		
		int compare = startTime.compareTo(newsDate);
		
		if (compare > 0) {
			System.out.println("ts>newsdate");
			System.out.println(company);
			System.out.println(title);
			System.out.println("뉴스시간: "+newsDate);
			System.out.println("현재시간: "+startTime);
			
			
			News news = News.builder()
					.company(company)
					.title(title)
					.createdAt(newsDate)
					.build();
			
			newsList.add(news);
			
			aidNum ++;
		} 
		
	}
	} catch (Exception e) {
		this.aidNum = aidNum;
		
	}
	return newsList;
	
	}
}

package com.cos.realtime.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.realtime.domain.News;
import com.cos.realtime.domain.NewsReactRepository;
import com.cos.realtime.domain.NewsRepository;
import com.cos.realtime.util.NewsCraw;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class NewsBatch {
	
	private final NewsReactRepository newsReactRepository;
	private final NewsRepository newsRepository;
	private final NewsCraw newsCraw;
	
	
	@Scheduled(fixedDelay=1000*60*1)
	public void newsCrawStart() {
		List<News> newsList = newsCraw.newsCollect();
		 
		System.out.println();
		newsRepository.saveAll(newsList);
		System.out.println(newsList);
		System.out.println("실행됨");
		
	}
}

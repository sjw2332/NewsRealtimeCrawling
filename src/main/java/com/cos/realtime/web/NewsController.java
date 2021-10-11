package com.cos.realtime.web;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.realtime.domain.News;
import com.cos.realtime.domain.NewsReactRepository;
import com.cos.realtime.domain.NewsRepository;
import com.cos.realtime.util.NewsCraw;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@RequiredArgsConstructor
@RestController
public class NewsController {
	
	private final NewsReactRepository newsReactRepository;
	private final NewsRepository newsRepository;
	
	@CrossOrigin
	@GetMapping(value = "/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<News> findAll(){

		return newsReactRepository.mFindAll().subscribeOn(Schedulers.boundedElastic()); // 핵심
	}
	
	
	
}

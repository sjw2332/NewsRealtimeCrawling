package com.cos.realtime.domain;

import java.sql.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;

public interface NewsReactRepository extends ReactiveMongoRepository <News, String> {
	@Tailable 
	@Query("{}")
	Flux<News> mFindAll();
	
}

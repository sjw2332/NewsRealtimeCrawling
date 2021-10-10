package com.cos.realtime.domain;


import java.sql.Timestamp;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "naver_realtime")
public class News {

	private String _id;
	
	private String company;
	private String title;
	private Timestamp createdAt;
}

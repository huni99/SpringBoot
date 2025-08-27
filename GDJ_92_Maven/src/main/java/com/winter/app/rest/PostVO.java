package com.winter.app.rest;

import lombok.Data;

@Data
public class PostVO {
	
	private Long id;
	private Long userId;
	private String title;
	private String body;
}

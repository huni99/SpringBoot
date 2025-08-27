package com.winter.app.rest;

import lombok.Data;

@Data
public class PhotoVO {
	private Long albumId;
	private Long Id;
	private String title;
	private String url;
	private String thumbnailUrl;
}

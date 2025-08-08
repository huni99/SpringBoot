package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Fileconfig implements WebMvcConfigurer {
	@Value("${app.upload}")
	private String path; // D:/upload/
	@Value("${app.url}")
	private String url; // /files/**
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(url)
				.addResourceLocations("file:\\"+path);
	}
	

}

package com.winter.app.rest;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(value="/api/*")
@Slf4j
public class RestTestController {
	@GetMapping("list")
	public void m1()throws Exception{
		log.info("api list");
		//RestTemplate 사용 사라질 예정
//		RestTemplate restTemplate = new RestTemplate();
//		
//		HttpEntity<MultiValueMap<String ,String >> request = new HttpEntity<>(null);
//		
//		List<PhotoVO> result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", List.class,request);
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com/photos/2")
										.build();
		Mono<ResponseEntity<PhotoVO>> res =webClient.get()
												 	.retrieve()
												 	.toEntity(PhotoVO.class);
		PhotoVO photoVO = res.block().getBody();	
		
		log.info("{}",photoVO);
		this.m2();
		this.m3();
		
	}
	private void m2()throws Exception{
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com/")
										.build();
		
		Flux<UserVO> res = webClient.get()
				 .uri("users")
				 .retrieve()
				 .bodyToFlux(UserVO.class);
		
		res.subscribe(u->{
			UserVO userVO = u;
			log.info("{}",userVO);
			
		});
		
		
	}
	private void m3()throws Exception{
		PostVO postVO = new PostVO();
		postVO.setTitle("titleTest");
		postVO.setBody("contents");
		postVO.setUserId(1L);
		
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com/")
										.build();
		
		Mono<PostVO> res =webClient.post()
				 .uri("posts")
				 .bodyValue(postVO)
				 .retrieve()
				 .bodyToMono(PostVO.class);
		log.info("{}",res.block());
		
	}
	
	
	
	
	
	
	
	
	
}

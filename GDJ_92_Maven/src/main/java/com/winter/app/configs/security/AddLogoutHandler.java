package com.winter.app.configs.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AddLogoutHandler implements LogoutHandler {
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String uri;
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if(authentication instanceof OAuth2AuthenticationToken) {
			MemberVO memberVO = (MemberVO)authentication.getPrincipal();
			if(memberVO.getSns().toUpperCase().equals("KAKAO")) {
				
				this.useKako(memberVO);
			}
		}
		
	}
	private void useWithKako(MemberVO memberVO) {
		WebClient webClient= WebClient.create();
		Mono<String> result=webClient.get()
				 .uri("https://kauth.kakao.com/oauth/logout?client_id={id}&logout_redirect_uri{uri}",restKey,uri)
				 .retrieve()
				 .bodyToMono(String.class);
	}
	
	
	private void useKako(MemberVO memberVO) {
		
		//파라미터 
		Map<String,Object> param = new HashMap<>();
		param.put("target_id_type", "user_id");
		param.put("target_id", memberVO.getName());
		WebClient webClient= WebClient.create();
		
		Mono<String> result =webClient.post()
				 .uri("https://kapi.kakao.com/v1/user/logout")
				 .header("Authorization","Bearer "+memberVO.getAccessToken())
				 .bodyValue(param)
				 .retrieve()
				 .bodyToMono(String.class);
	}

}

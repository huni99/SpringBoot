package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager manager, JwtTokenManager jwtTokenManager) {
		
		super(manager);
		this.jwtTokenManager=jwtTokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Authorization : Bearer ${ACCESS_TOKEN}
		//Bearer ${ACCESS_TOKEN}
		String header = request.getHeader("Authorization");
		
		if(header !=null && header.startsWith("Bearer") ) {
			header= header.substring(header.indexOf(" ")+1);
			
			//token  검증
			try {
				Authentication authentication = jwtTokenManager.verifyToken(header);
				//성공시 authentication을 session에 넣어주기
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		chain.doFilter(request, response);
		
		
	}
}

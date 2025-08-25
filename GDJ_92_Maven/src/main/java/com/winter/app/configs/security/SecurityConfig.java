package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	//정적자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		
		//web=>WebSecurity
		return web ->{
			web
				.ignoring()
					.requestMatchers("/css/**","/js/**")
					.requestMatchers("/vender/**")
					.requestMatchers("/files/**");
		};
	}
	
	//인증과 권한의 설정
	//Authentication Authorization
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
			.cors(cors-> cors.disable())
			.csrf(csrf -> csrf.disable())
			
			//권한에 관련된 설정
			.authorizeHttpRequests(auth->{
				auth	
					.requestMatchers("/notice/add","/notice/update","/notice/delete").hasRole("ADMIN")
					.requestMatchers("/products/add","/products/update","/products/delete").hasAnyRole("ADMIN","MANAGER")
//					.requestMatchers("/member/member","/member/logout","/member/update","/member/delete").access("hasRole('ROLE_MEMBER)') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')");
					.requestMatchers("/member/detail","/member/logout","/member/update","/member/delete").authenticated()
					.anyRequest().permitAll()
					;
				
			})
			//form 관련 설정
			.formLogin(form->{
				form
					.loginPage("/member/login")
//					.usernameParameter("username")
//					.passwordParameter("password")
					.defaultSuccessUrl("/")
					.failureUrl("/member/login")
					;
			})
			;
		return httpSecurity.build();
	}

}

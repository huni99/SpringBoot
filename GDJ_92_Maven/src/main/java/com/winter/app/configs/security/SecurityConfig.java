package com.winter.app.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	@Autowired
	private AddLogoutSuccessHandler addlogoutSuccessHandler;
	@Autowired
	private AddLogoutHandler addlogoutHandler;
	@Autowired
	private MemberService memberService;
//	
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
					//.requestMatchers("/member/member","/member/logout","/member/update","/member/delete").access("hasRole('ROLE_MEMBER)') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')");
					.requestMatchers("/member/detail","/member/logout","/member/update","/member/delete").authenticated()
					.anyRequest().permitAll()
					;
				
			})
			//form 관련 설정
			.formLogin(form->{
				form
					.loginPage("/member/login")
					//.usernameParameter("username")
					//.passwordParameter("password")
					//.defaultSuccessUrl("/")  //redirect로 보냄
					//.successForwardUrl("/") // forwoard로 보냄
					.successHandler(loginSuccessHandler)
					.failureHandler(loginFailureHandler)
					//.failureUrl("/member/login")
					;
			})
			//logout 설정
			.logout(logout->{
				logout
					.logoutUrl("/member/logout")
					.logoutSuccessHandler(addlogoutSuccessHandler)
					.addLogoutHandler(addlogoutHandler)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
//					.logoutSuccessUrl("/")
					;
			})
			.rememberMe((remember)->{
				remember
					.rememberMeParameter("remember-me")
					.tokenValiditySeconds(60)
					.key("rememberkey")
					.userDetailsService(memberService)
					.authenticationSuccessHandler(loginSuccessHandler)
					.useSecureCookie(false)
					;
			})
			.oauth2Login(o->{
				o.userInfoEndpoint(user->{
					user.userService(memberService);
				});
			})
			;
		return httpSecurity.build();
	}

}

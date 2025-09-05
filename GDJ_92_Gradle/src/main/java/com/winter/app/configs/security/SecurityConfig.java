package com.winter.app.configs.security;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private AddLogoutSuccessHandler addLogoutSucessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
		
		//1. 권한 설정
		httpSecurity
			.cors(cors -> cors.configurationSource(null))
			.csrf(csrf-> csrf.disable())
			.authorizeHttpRequests(
				auth -> {
					auth.requestMatchers("/api/notice/add").hasRole("ROLE_ADMIN")
					.anyRequest().permitAll();
				})
		
		//2. Form Login
			.formLogin(formLogin -> formLogin.disable())
		//3. Logout
			.logout(logout -> {
				logout
				.logoutUrl("/api/member/logout")
				.invalidateHttpSession(true)
				.deleteCookies("access_token","refresh_token")
				.logoutSuccessHandler(addLogoutSucessHandler);
			})
		//4. Session
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		//5. HttpBasic
			.httpBasic(http -> http.disable())
			
		//6. Token에 관련된 필터를 등록
			.addFilter(new JwtAuthenticationFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtLoginFilter(this.authenticationConfiguration.getAuthenticationManager(),jwtTokenManager))
			.build();
			return httpSecurity.build();
		
	}
	
		CorsConfigurationSource	corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of("http://localhost:*"));
		//Method에서 *은 사용 x
		configuration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	
	
	
	
	}
	
}
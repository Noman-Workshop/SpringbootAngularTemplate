package com.eastnetic.todoapp.auth.config;

import com.eastnetic.todoapp.auth.filter.JWTAuthFilter;
import com.eastnetic.todoapp.auth.service.jwt.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class WebSecurityConfig {
	
	private final JWTService jwtService;
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public WebSecurityConfig(JWTService jwtService,
	                         UserDetailsService userDetailsService,
	                         BCryptPasswordEncoder passwordEncoder) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// allow cors on all origins and ports
				registry.addMapping("/**")
				        .allowedMethods("*")
				        .allowedHeaders("*")
				        .allowedOrigins("*");
			}
		};
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	@Bean
	public JWTAuthFilter jwtAuthFilter() {
		return new JWTAuthFilter(jwtService, userDetailsService);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors()
		    .and()
		    .csrf()
		    .disable()
		    .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .authorizeRequests()
		    .antMatchers(
				    "/swagger-ui/**",
				    "/docs/**",
				
				    "/user/signup",
				    "/auth/signin",
				    "/auth/refresh",
					"/todo/create",
					"/role/**"
		    )
		    .permitAll()
		    .anyRequest()
		    .authenticated();
		
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}

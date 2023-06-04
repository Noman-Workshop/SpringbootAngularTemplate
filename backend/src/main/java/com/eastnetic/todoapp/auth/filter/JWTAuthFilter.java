package com.eastnetic.todoapp.auth.filter;

import com.eastnetic.todoapp.auth.service.jwt.JWTService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTAuthFilter extends OncePerRequestFilter {
	
	private final JWTService jwtService;
	private final UserDetailsService userDetailsService;
	
	public JWTAuthFilter(JWTService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {
		
		// Don't check for JWT if the request is for public paths
		List<String> publicPaths = List.of("/auth/signin", "/auth/refresh");
		if (publicPaths.contains(request.getServletPath())) {
			filterChain.doFilter(request, response);
			return;
		}
		var authorization = request.getHeader("Authorization");
		if (authorization == null || !authorization.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		var jwt = authorization.replace("Bearer ", "");
		var claims = jwtService.extractClaims(jwt);
		if (claims == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		var userName = claims.getBody()
		                     .getSubject();
		
		var userDetails = userDetailsService.loadUserByUsername(userName);
		var authToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities()
		);
		
		authToken.setDetails(userDetails);
		SecurityContextHolder.getContext()
		                     .setAuthentication(authToken);
		
		filterChain.doFilter(request, response);
	}
}

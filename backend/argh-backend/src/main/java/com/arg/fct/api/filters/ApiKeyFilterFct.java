package com.arg.fct.api.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilterFct extends OncePerRequestFilter{

		@Value("${user.key}")
		private String userKey;
		
		@Override
		protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
			String requestURI = request.getRequestURI();
		return (requestURI.startsWith("/swagger") || requestURI.startsWith("/docs"));
		}

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			String apiKey = request.getHeader("API-KEY");
			if(userKey.equals(apiKey)) {
				filterChain.doFilter(request, response);
			}else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("UNAUTHORIZED REQUEST");
			}
			
		}
		
		
}

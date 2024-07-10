package com.vyankatesh.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	private UserDetailsService userDetailsService;
	
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	public JWTAuthenticationFilter(UserDetailsService userDetailsService, JwtTokenHelper jwtTokenHelper) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenHelper = jwtTokenHelper;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Get JWT token from request.
		
		String requestToken= request.getHeader("Authorization");
		
		String userName=null;
		
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			
			token = requestToken.substring(7);
			
			try {
				userName = jwtTokenHelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException ex)
			{
				System.out.println("Unable to get JWT token");
			}
			catch(ExpiredJwtException ex)
			{
				System.out.println("JWT token is expired.");
			}
			catch(MalformedJwtException ex)
			{
				System.out.println("Invalid JWT token.");	
			}
			
			
		}
		else {
			System.out.println(requestToken);
			System.out.println("JWT Token does not start with Bearer");
		}
		
		//Once we get the token, now validate. 
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName); 
			
			if(jwtTokenHelper.validateToken(token, userDetails))
			{
				//Do the authentication. 
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				
			}
			else {
				System.out.println("Invalid token");
			}
		}
		
		filterChain.doFilter(request, response);

	}
	
	



}

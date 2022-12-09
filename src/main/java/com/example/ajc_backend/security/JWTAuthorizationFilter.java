package com.example.ajc_backend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.ajc_backend.entites.Candidat;


public class JWTAuthorizationFilter extends OncePerRequestFilter{

		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

	
     String jwt=request.getHeader(SecurityParams.JWT_HEADER_NAME);
     
		

     System.out.println("JWT = "+jwt);
     System.out.println("JWT = 5");
     System.out.println(request.getRequestURI());
     System.out.println(request.getHeaderNames());
  
    
 	response.addHeader("Access-Control-Allow-Origin", "*");
 	response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Request-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Header-authorization");
 	response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credential, authorization");
 		
 	
 	if(request.getMethod().equals("OPTION")) {
 	  response.setStatus(HttpServletResponse.SC_OK);	
 	}else if(request.getRequestURI().equals("/login")){
 		filterChain.doFilter(request, response);
 		// System.out.println("Erreur 1 ");
 		System.out.println("JWT = 8"); 
 		return;
 	}else {
 		 
     if(jwt==null || !jwt.startsWith(SecurityParams.HEADER_PREFIX)) {
    	 filterChain.doFilter(request, response);
    	 //System.out.println("Erreur 2 ");
    	 return;
     }
 	JWTVerifier verifier= JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
 	DecodedJWT decodeJWT=verifier.verify(jwt.substring(SecurityParams.HEADER_PREFIX.length()));
 	String username=decodeJWT.getSubject();
 	List<String> roles=decodeJWT.getClaims().get("roles").asList(String.class);
 	Collection<GrantedAuthority> authorities=new ArrayList<>();
 	roles.forEach(r->{
 		authorities.add(new SimpleGrantedAuthority(r));
 	});
 	UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(username,null,authorities);
 	SecurityContextHolder.getContext().setAuthentication(user);
 	filterChain.doFilter(request, response);
     
	}

  }
	


	
	
}


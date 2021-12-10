package com.example.exibition.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService {

	String generateToken(UserDetails userDetails);

	String getUsernameFromToken(String jwtToken);

	boolean validateToken(String jwtToken, UserDetails userDetails);
}

package com.example.exibition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.exibition.provider.JwtProvider;

@Service
public class JwtServiceImpl implements JwtService {

	@Autowired
	private JwtProvider utility;

	@Override
	public String generateToken(UserDetails userDetails) {
		return utility.generateToken(userDetails);
	}

	@Override
	public String getUsernameFromToken(String jwtToken) {
		return utility.getUsernameFromToken(jwtToken);
	}
	
	@Override
	public boolean validateToken(String jwtToken, UserDetails userDetails) {
		return utility.validateToken(jwtToken, userDetails);
	}
}

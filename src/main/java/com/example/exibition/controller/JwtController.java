package com.example.exibition.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exibition.dto.AuthRequest;
import com.example.exibition.dto.AuthResponse;
import com.example.exibition.service.CustomUserDetailsService;
import com.example.exibition.service.JwtService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
public class JwtController {

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final CustomUserDetailsService userService;
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest auth) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.username(), auth.password()));
			UserDetails userDetails = userService.loadUserByUsername(auth.username());
			return new ResponseEntity<AuthResponse>(new AuthResponse(jwtService.generateToken(userDetails)), HttpStatus.OK);
		} catch (DisabledException e) {
			log.error(e.getMessage());
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error(e.getMessage());
			throw new Exception("INVALID_CREDENTIALS", e);
		}			
	}
	
}

package com.example.exibition.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.exibition.bean.UserPrincipal;
import com.example.exibition.model.LocalUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LocalUser user = userService.findByUsername(username)
						.orElseThrow(()-> new UsernameNotFoundException(username));
		return new UserPrincipal(user);
	}
	
}

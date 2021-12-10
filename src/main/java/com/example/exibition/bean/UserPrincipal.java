package com.example.exibition.bean;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.exibition.model.LocalUser;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 7487328302945478738L;
	
	private LocalUser user;
	
	public UserPrincipal(LocalUser user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(
				role -> new SimpleGrantedAuthority("ROLE_"+role.getName().name()))
		.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isEnabled() {
		return user.getEnable() != null && user.getEnable();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}

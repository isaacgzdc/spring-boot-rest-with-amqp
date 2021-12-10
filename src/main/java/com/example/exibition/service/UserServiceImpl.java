package com.example.exibition.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.exibition.model.LocalUser;
import com.example.exibition.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public Optional<LocalUser> findByUsername(String username) {
		if(username == null) {
			return Optional.empty();
		}
		log.debug("Trying to find the username: {}",username);
		return userRepository.findByUsername(username);
	}
	
	
}

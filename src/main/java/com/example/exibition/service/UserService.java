	package com.example.exibition.service;

import java.util.Optional;

import com.example.exibition.model.LocalUser;

public interface UserService {

	Optional<LocalUser> findByUsername(String username);
}

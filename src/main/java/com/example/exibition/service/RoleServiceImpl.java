package com.example.exibition.service;

import org.springframework.stereotype.Service;

import com.example.exibition.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository userRepository;

}

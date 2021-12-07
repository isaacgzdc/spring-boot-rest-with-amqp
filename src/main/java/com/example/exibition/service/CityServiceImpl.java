package com.example.exibition.service;

import org.springframework.stereotype.Service;

import com.example.exibition.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
}

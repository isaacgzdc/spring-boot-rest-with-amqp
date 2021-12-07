package com.example.exibition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.exibition.dto.FairDTO;
import com.example.exibition.exception.ExibitionException;
import com.example.exibition.model.Fair;
import com.example.exibition.repository.FairRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FairServiceImpl implements FairService {

	private final FairRepository fairRepository;

	@Override
	public List<Fair> findAll() {
		return fairRepository.findAll();
	}

	@Override
	public Optional<Fair> findById(Long id) {
		return fairRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws ExibitionException {
		fairRepository.deleteById(id);
	}

	@Override
	public Fair save(FairDTO dto) throws ExibitionException {
		Fair fair = new Fair();
		fair.setCreatedAt(dto.createdAt());
		fair.setName(dto.name());
		return fairRepository.save(fair);
	}
}

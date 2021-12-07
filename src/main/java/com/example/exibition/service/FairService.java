package com.example.exibition.service;

import java.util.List;
import java.util.Optional;

import com.example.exibition.dto.FairDTO;
import com.example.exibition.exception.ExibitionException;
import com.example.exibition.model.Fair;

public interface FairService {

	List<Fair> findAll();

	Optional<Fair> findById(Long id);

	void deleteById(Long id) throws ExibitionException;

	Fair save(FairDTO dto) throws ExibitionException;

}

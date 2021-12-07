package com.example.exibition.service;

import java.util.List;
import java.util.Optional;

import com.example.exibition.dto.ExibitionDTO;
import com.example.exibition.exception.ExibitionException;
import com.example.exibition.model.Exibition;

public interface ExibitionService {

	List<Exibition> findAll();

	Optional<Exibition> findById(Long id);

	Exibition save(ExibitionDTO dto) throws ExibitionException ;

}

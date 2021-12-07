package com.example.exibition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.exibition.dto.ExibitionDTO;
import com.example.exibition.exception.ExibitionException;
import com.example.exibition.model.Exibition;
import com.example.exibition.repository.ExibitionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExibitionServiceImpl implements ExibitionService {

	private final ExibitionRepository exibitionRespository;

	private final CityService cityService;
	private final FairService fairService;
	
	@Override
	public List<Exibition> findAll() {
		return exibitionRespository.findAll();
	}

	@Override
	public Optional<Exibition> findById(Long id) {
		return exibitionRespository.findById(id);
	}

	@Override
	public Exibition save(ExibitionDTO dto) throws ExibitionException {
		if(dto == null) {
			throw new ExibitionException("Cannot save exitition data from DTO because the dto is null !!!");
		}
		Exibition exibition = Exibition.builder()
				.description(dto.description())
				.edition(dto.edition())
				.fromDate(dto.fromDate())
				.toDate(dto.toDate())
				.fair(null)
				.city(null)
				.build();
		return exibitionRespository.save(exibition);
	}
}

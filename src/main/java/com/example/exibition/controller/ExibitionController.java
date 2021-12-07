package com.example.exibition.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exibition.dto.ExibitionDTO;
import com.example.exibition.exception.ExibitionException;
import com.example.exibition.model.Exibition;
import com.example.exibition.service.ExibitionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/exibitions")
@RequiredArgsConstructor
public class ExibitionController {

	private final ExibitionService exibitionService;

	@GetMapping
	public ResponseEntity<List<Exibition>> getAll() {
		List<Exibition> body = exibitionService.findAll();
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<List<Exibition>>(body, status);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Exibition> getAll(@PathVariable("id") Long id) {

		return exibitionService.findById(id).map(e -> {
			HttpStatus status = HttpStatus.OK;
			return new ResponseEntity<Exibition>(e, status);
		}).orElseGet(() -> {
			HttpStatus status = HttpStatus.NO_CONTENT;
			return new ResponseEntity<Exibition>(new Exibition(), status);
		});

	}

	@PostMapping("/save")
	public ResponseEntity<Exibition> save(@Valid @RequestBody ExibitionDTO dto) {

		log.debug("SAVE dto: {}", dto.toString());

		Exibition body;
		HttpStatus status = HttpStatus.OK;
		try {
			body = exibitionService.save(dto);
		} catch (ExibitionException e) {
			log.error("SAVE ERROR !!!");
			log.error(e.getMessage());
			status = HttpStatus.NO_CONTENT;
			body = new Exibition();
		}

		return new ResponseEntity<Exibition>(body, status);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Exibition> update(@PathVariable("id") Long id, @RequestBody ExibitionDTO dto) {

		log.debug("UPDATE dto: {}", dto.toString());

		Exibition body;
		HttpStatus status = HttpStatus.OK;
		try {
			body = exibitionService.save(dto);
		} catch (ExibitionException e) {
			log.error("UPDATE ERROR !!!");
			log.error(e.getMessage());
			status = HttpStatus.NO_CONTENT;
			body = new Exibition();
		}
		
		return new ResponseEntity<Exibition>(body, status);
	}

}

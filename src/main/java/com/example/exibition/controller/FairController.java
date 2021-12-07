package com.example.exibition.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exibition.dto.FairDTO;
import com.example.exibition.exception.ExibitionException;
import com.example.exibition.model.Fair;
import com.example.exibition.service.FairService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/fairs")
@RequiredArgsConstructor
public class FairController {

	private final FairService fairService;

	@GetMapping
	public ResponseEntity<List<Fair>> getAll() {
		List<Fair> body = fairService.findAll();
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<List<Fair>>(body, status);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fair> getAll(@PathVariable("id") Long id) {

		return fairService.findById(id).map(e -> {
			HttpStatus status = HttpStatus.OK;
			return new ResponseEntity<Fair>(e, status);
		}).orElseGet(() -> {
			HttpStatus status = HttpStatus.NO_CONTENT;
			return new ResponseEntity<Fair>(new Fair(), status);
		});

	}

	@PostMapping("/save")
	public ResponseEntity<Fair> save(@RequestBody FairDTO dto) {

		log.debug("SAVE dto: {}", dto.toString());

		Fair body;
		HttpStatus status = HttpStatus.OK;
		try {
			body = fairService.save(dto);
		} catch (ExibitionException e) {
			log.error("SAVE ERROR !!!");
			log.error(e.getMessage());
			status = HttpStatus.NO_CONTENT;
			body = new Fair();
		}

		return new ResponseEntity<Fair>(body, status);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		log.debug("DELETE fair.id({})", id);
		HttpStatus status = HttpStatus.NO_CONTENT;
		try {
			fairService.deleteById(id);
		} catch (ExibitionException e) {
			log.error("DELETE ERROR !!!");
			log.error(e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(status);
	}

}

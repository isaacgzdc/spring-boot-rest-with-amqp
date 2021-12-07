package com.example.exibition.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error("Rest Api exception due to method argument exception !!!");
		
		List<String> errors = new ArrayList<>();
		for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
			log.error("Field error => {}: {}", fe.getField(), fe.getDefaultMessage());
			errors.add(fe.getField() + ": " + fe.getDefaultMessage());
		}

		ApiError apiError = new ApiError("Error with provide data",
				"Verify the provided data required to call this api", status, errors);

		log.error(apiError.toString());
		
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}
}

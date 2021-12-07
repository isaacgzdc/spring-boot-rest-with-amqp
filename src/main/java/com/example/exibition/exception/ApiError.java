package com.example.exibition.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

	private String detail;
	private String message;
	private HttpStatus status;
	private List<String> errors;
	private LocalDateTime timestamp;

	public ApiError() {
		this.timestamp = LocalDateTime.now();
	}

	public ApiError(String detail, String desc) {
		this();
		this.errors = new ArrayList<>();
	}
	
	public ApiError(String detail, String desc, HttpStatus status) {
		this(detail, desc);
		this.status = status;
	}
	
	public ApiError(String detail, String desc, HttpStatus status, List<String> errors) {
		this(detail, desc, status);
		if(errors != null && !errors.isEmpty()) {
			this.errors.addAll(errors);
		}
	}

}

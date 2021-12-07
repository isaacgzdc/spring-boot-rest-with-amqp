package com.example.exibition.dto;

import java.time.LocalDateTime;

public record FairDTO (
		Long id,
		String name,
		LocalDateTime createdAt){

}

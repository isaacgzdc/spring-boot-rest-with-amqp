package com.example.exibition.dto;

import java.time.LocalDateTime;

import com.example.exibition.validation.DateRange;

@DateRange(fromDate = "fromDate", toDate = "toDate")
public record ExibitionDTO(
		Long id,
		Integer edition,
		String description,
		LocalDateTime fromDate,
		LocalDateTime toDate,
		Long fairId,
		Long cityId) {

	public ExibitionDTO(int edition, String description, LocalDateTime from, LocalDateTime to, long fairId, long cityId) {
		this(null, edition, description, from, to, fairId, cityId);
	}

}

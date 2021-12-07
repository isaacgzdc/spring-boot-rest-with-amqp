package com.example.exibition.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name"}, name = "city_name_unique")
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City implements Serializable {

	private static final long serialVersionUID = 6530574575308952215L;

	private static final String SEQUENCE_NAME = "CITY_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	
	private String name;
	@Enumerated(EnumType.STRING)
	private Continent continent;
	private BigDecimal population;
	private LocalDateTime createdAt;
}

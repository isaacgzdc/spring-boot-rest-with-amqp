package com.example.exibition.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exibition implements Serializable {

	private static final long serialVersionUID = -4966991562791026827L;

	private static final String SEQUENCE_NAME = "EXIBITION_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	@Lob
	private String description;
	@Positive
	private Integer edition;
	@FutureOrPresent(message = "The exibition start date must be in the future")
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	
	@NotNull(message = "Exibition fair cannot be null!")
	@ManyToOne
	@JoinColumn(name = "FAIR_ID")
	private Fair fair;
	
	@NotNull(message = "Exibition city cannot be null!")
	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;
	
}

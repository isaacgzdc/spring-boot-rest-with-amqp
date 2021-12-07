package com.example.exibition.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name"}, name = "fair_name_unique")
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fair implements Serializable {

	private static final long serialVersionUID = 6530574575308952215L;

	private static final String SEQUENCE_NAME = "FAIR_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	
	private String name;

	@NotNull(message = "Fair required a created date")
	private LocalDateTime createdAt;
	
}

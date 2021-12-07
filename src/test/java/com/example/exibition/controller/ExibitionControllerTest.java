package com.example.exibition.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.exibition.dto.ExibitionDTO;
import com.example.exibition.model.City;
import com.example.exibition.model.Continent;
import com.example.exibition.model.Exibition;
import com.example.exibition.model.Fair;
import com.example.exibition.service.ExibitionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ExibitionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ExibitionService exibitionService;

	@Test
	void testFindAll() throws JsonProcessingException, Exception {
		
		List<Exibition> list = exibitions();
		
		when(exibitionService.findAll()).thenReturn(list);
		
		this.mockMvc
		.perform(get("/api/v1/exibitions")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(content().json(objectMapper.writeValueAsString(list)))
		.andExpect(jsonPath("$", Matchers.hasSize(list.size())))
		.andExpect(jsonPath("$[0].id", Matchers.is(list.get(0).getId().intValue())))
		.andExpect(jsonPath("$[1].id", Matchers.is(list.get(1).getId().intValue())))
		.andDo(print());
		
	}

	private List<Exibition> exibitions() {
		List<Exibition> list = new ArrayList<>();
		list.add(Exibition.builder()
				.id(1L)
				.description("Custom serialization with new tools")
				.edition(1)
				.fromDate(LocalDateTime.of(2022, 4, 8, 10, 0))
				.toDate(LocalDateTime.of(2022, 4, 13, 14, 0))
				.city(City.builder()
						.id(1L)
						.name("Luanda")
						.continent(Continent.AFRICA)
						.createdAt(LocalDateTime.now())
						.build())
				.fair(Fair.builder()
						.id(1L)
						.name("DevOps Today")
						.createdAt(LocalDateTime.now())
						.build())
				.build());
		list.add(Exibition.builder()
				.id(1L)
				.description("Manipulating the bytecode with old tools")
				.edition(1)
				.fromDate(LocalDateTime.of(2023, 7, 12, 10, 0))
				.toDate(LocalDateTime.of(2023, 7, 22, 14, 0))
				.city(City.builder()
						.id(1L)
						.name("Delhi")
						.continent(Continent.ASIA)
						.createdAt(LocalDateTime.now())
						.build())
				.fair(Fair.builder()
						.id(1L)
						.name("Spring Boot v6")
						.createdAt(LocalDateTime.now())
						.build())
				.build());
		return list;
	}
	
	@Test
	void testCreateNewExibition() throws Exception {

		ExibitionDTO dto = new ExibitionDTO(
				3,
				"the third edition for the first fair",
				LocalDateTime.of(2022, 4, 8, 14, 0),
				LocalDateTime.of(2022, 4, 10, 21, 45),
				1,
				1
				);
		Exibition exibition = Exibition.builder()
				.id(1L)
				.description(dto.description())
				.edition(dto.edition())
				.fromDate(dto.fromDate())
				.toDate(dto.toDate())
				.city(City.builder().id(1L).build())
				.fair(Fair.builder().id(1L).build())
				.build();
		Mockito.when(exibitionService.save(dto)).thenReturn(exibition);
		
		this.mockMvc
		.perform(post("/api/v1/exibitions/save")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))
				)
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(content().json(objectMapper.writeValueAsString(exibition)))
		.andDo(print());
	}
	
	@Test
	void testCreateNewExibitionWithWrongDates() throws Exception {

		ExibitionDTO dto = new ExibitionDTO(
				3,
				"the from and to dates are wrong",
				LocalDateTime.of(2022, 4, 10, 21, 45),
				LocalDateTime.of(2022, 4, 8, 14, 0),
				1,
				1
				);
		Exibition exibition = Exibition.builder()
				.id(1L)
				.description(dto.description())
				.edition(dto.edition())
				.fromDate(dto.fromDate())
				.toDate(dto.toDate())
				.city(City.builder().id(1L).build())
				.fair(Fair.builder().id(1L).build())
				.build();
		Mockito.when(exibitionService.save(dto)).thenReturn(exibition);
		
		this.mockMvc
		.perform(post("/api/v1/exibitions/save")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))
				)
		.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
		.andDo(print());
	}

}

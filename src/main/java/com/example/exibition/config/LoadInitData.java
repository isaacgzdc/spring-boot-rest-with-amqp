package com.example.exibition.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.exibition.model.City;
import com.example.exibition.model.Continent;
import com.example.exibition.model.Exibition;
import com.example.exibition.model.Fair;
import com.example.exibition.repository.CityRepository;
import com.example.exibition.repository.ExibitionRepository;
import com.example.exibition.repository.FairRepository;
import com.example.exibition.service.RabbitMQService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadInitData implements CommandLineRunner {

	@Autowired
	private ExibitionRepository exibitionRepository;
	@Autowired
	private FairRepository fairRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private RabbitMQService rabbitMQService;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		for (String arg : args) {
			log.debug("ARGUMENT => {}", arg);
		}

		City city1 = new City();
		city1.setName("Lisbon");
		city1.setContinent(Continent.EUROPE);
		city1.setPopulation(new BigDecimal(500000));
		city1.setCreatedAt(LocalDateTime.now().minusDays(4));
		City city2 = new City();
		city2.setName("Montreal");
		city2.setContinent(Continent.NORTH_AMERICA);
		city2.setPopulation(new BigDecimal(1700000));
		city2.setCreatedAt(LocalDateTime.now().minusDays(3));
		City city3 = new City();
		city3.setName("Maputo");
		city3.setContinent(Continent.EUROPE);
		city3.setPopulation(new BigDecimal(1000000));
		city3.setCreatedAt(LocalDateTime.now().minusDays(2));
		City city4 = new City();
		city4.setName("Nagasaki");
		city4.setContinent(Continent.EUROPE);
		city4.setPopulation(new BigDecimal(400000));
		city4.setCreatedAt(LocalDateTime.now().minusDays(1));

		cityRepository.saveAll(Arrays.asList(city1, city2, city3, city4));

		log.debug("Cities saved!");

		LocalDateTime now = LocalDateTime.now();
		Fair fair1 = new Fair();
		fair1.setCreatedAt(now);
		fair1.setName("Java 17 new features");
		Fair fair2 = new Fair();
		fair2.setCreatedAt(now);
		fair2.setName("Devops with Kubernetes and Ansible");
		Fair fair3 = new Fair();
		fair3.setCreatedAt(now);
		fair3.setName("Design patterns and algorithms");
		Fair fair4 = new Fair();
		fair4.setCreatedAt(now);
		fair4.setName("Java clean code");

		fairRepository.saveAll(Arrays.asList(fair1, fair3, fair2, fair4));

		log.debug("Fairs saved!");

		Exibition exibition1 = new Exibition();
		exibition1.setCity(city4);
		exibition1.setFair(fair4);
		exibition1.setDescription("Upgrade clean techniques to improve rust and go interoperability");
		exibition1.setEdition(1);
		exibition1.setFromDate(LocalDateTime.of(2021, 12, 25, 9, 30, 0));
		exibition1.setToDate(LocalDateTime.of(2021, 12, 28, 18, 45, 0));
		exibitionRepository.save(exibition1);
		rabbitMQService.send(exibition1);
		
		Exibition exibition2 = new Exibition();
		exibition2.setCity(city1);
		exibition2.setFair(fair4);
		exibition2.setDescription("Refactoring and code quality with new clean code rules");
		exibition2.setEdition(2);
		exibition2.setFromDate(LocalDateTime.of(2022, 7, 14, 10, 0, 0));
		exibition2.setToDate(LocalDateTime.of(2022, 7, 17, 16, 30, 0));
		exibitionRepository.save(exibition2);
		rabbitMQService.send(exibition2);
		
		Exibition exibition3 = new Exibition();
		exibition3.setCity(city3);
		exibition3.setFair(fair3);
		exibition3.setDescription("Refactoring and code quality with new clean code rules");
		exibition3.setEdition(1);
		exibition3.setFromDate(LocalDateTime.of(2022, 5, 4, 8, 15, 0));
		exibition3.setToDate(LocalDateTime.of(2022, 5, 9, 19, 30, 0));
		exibitionRepository.save(exibition3);
		rabbitMQService.send(exibition3);

		log.debug("Exibitions saved!");
		

	}

}

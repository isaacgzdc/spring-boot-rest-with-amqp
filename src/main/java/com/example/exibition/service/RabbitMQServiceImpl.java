package com.example.exibition.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.example.exibition.model.Exibition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:rabbitmq.properties")
public class RabbitMQServiceImpl implements RabbitMQService{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing-key.dev}")
	private String routingKeyDev;
	
	@Value("${rabbitmq.routing-key.qa}")
	private String routingKeyQa;	
	
	 
	@Override
	public void send(Exibition exibition) {
		
		log.debug("Data to be send to rabbitMQ: {}",exibition.toString());
		rabbitTemplate.convertAndSend(exchange, routingKeyDev, exibition);
		log.debug("Message was send to exchange: {} with routing key ({})",
				exchange, routingKeyDev);
	}
	
}

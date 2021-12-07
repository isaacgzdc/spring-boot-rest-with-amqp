package com.example.exibition.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import com.example.exibition.model.Exibition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("rabbitMQConsumerService")
public class RabbitMQConsumerServiceImpl implements RabbitMQConsumerService, MessageListener {

	@Override
	public void onMessage(Message message) {
		log.debug("==============================================================================");
		log.debug("==============================================================================");
		log.debug(message.toString());
		log.debug("==============================================================================");
		log.debug("==============================================================================");
	}

	@Override
	public void consume(Exibition exibition) {
		log.debug("not working yet !!!");
	}


}

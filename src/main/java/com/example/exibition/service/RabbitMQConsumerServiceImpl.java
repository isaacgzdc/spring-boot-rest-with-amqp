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
		log.debug("[+] CONSUMED MSG: {}",message.toString());
	}

	@Override
	public void consume(Exibition exibition) {
		log.warn("No yet implemented!!!");
		throw new UnsupportedOperationException();
	}


}

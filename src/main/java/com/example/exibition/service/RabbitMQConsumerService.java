package com.example.exibition.service;

import com.example.exibition.model.Exibition;

public interface RabbitMQConsumerService {

	void consume(Exibition exibition);
}

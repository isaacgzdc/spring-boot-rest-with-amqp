package com.example.exibition.service;

import com.example.exibition.model.Exibition;

public interface RabbitMQService {

	public void send(Exibition exibition);
	
}

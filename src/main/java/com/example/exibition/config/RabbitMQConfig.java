package com.example.exibition.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name.dev}")
	private String queueNameDev;
	@Value("${rabbitmq.queue.name.qa}")
	private String queueNameQa;

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing-key.dev}")
	private String routingKeyDev;
	@Value("${rabbitmq.routing-key.qa}")
	private String routingKeyQa;

	@Bean("queueDev")
	public Queue queueDev() {
		return new Queue(queueNameDev, false);
	}

	@Bean("queueQa")
	public Queue queueQa() {
		return new Queue(queueNameQa, false);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	public Binding bindingDev(@Qualifier("queueDev") Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(routingKeyDev);
	}

	@Bean
	public Binding bindingQa(@Qualifier("queueQa") Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(routingKeyQa);
	}

	@Bean
	public MessageConverter jsonMessageConverter(ObjectMapper jsonObjectMapper) {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(jsonObjectMapper);
		return converter;
	}

	@Bean
	public MessageListenerContainer messageListenerContainer(
			ConnectionFactory connectionFactory,
			@Qualifier("rabbitMQConsumerService") MessageListener consumer) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		simpleMessageListenerContainer.setQueues(queueDev(),queueQa());
		simpleMessageListenerContainer.setMessageListener(consumer);
		return simpleMessageListenerContainer;

	}

}

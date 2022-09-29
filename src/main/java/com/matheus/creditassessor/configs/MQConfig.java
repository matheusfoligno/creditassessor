package com.matheus.creditassessor.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Value("${mq.queues.card-issue}")
	private String queue;
	
	@Bean
	public Queue queueCards() {
		return new Queue(queue, true);
	}
}

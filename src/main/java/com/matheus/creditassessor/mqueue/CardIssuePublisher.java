package com.matheus.creditassessor.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheus.creditassessor.dtos.request.CardRequestData;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardIssuePublisher {
	
	private final RabbitTemplate rabbitTemplate;
	private final Queue queue;

	public void requestCard(CardRequestData data) throws JsonProcessingException {
		var json = convertIntoJson(data);
		rabbitTemplate.convertAndSend(queue.getName(), json);
	}
	
	private String convertIntoJson(CardRequestData data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(data);	
	}
}

package com.matheus.creditassessor.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matheus.creditassessor.dtos.response.CardResponse;
import com.matheus.creditassessor.dtos.response.CustomerCardResponse;

@FeignClient(value = "cards", path = "/cards")
public interface CardResourceClient {

	@GetMapping(params = "cpf")
	ResponseEntity<List<CustomerCardResponse>> getCardsByCustomer(@RequestParam("cpf") String cpf);
	
	@GetMapping(params = "income")
	ResponseEntity<List<CardResponse>> getCardsByIncome(@RequestParam("income") Long income);
}

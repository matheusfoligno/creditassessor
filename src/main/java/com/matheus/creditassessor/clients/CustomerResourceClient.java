package com.matheus.creditassessor.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matheus.creditassessor.dtos.response.CustomerDataResponse;

@FeignClient(value = "customers", path = "/customers")
public interface CustomerResourceClient {

	@GetMapping(params = "cpf")
	ResponseEntity<CustomerDataResponse> getCustomerByCpf(@RequestParam("cpf") String cpf);
}

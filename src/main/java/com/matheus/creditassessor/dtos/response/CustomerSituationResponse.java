package com.matheus.creditassessor.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSituationResponse {

	private CustomerDataResponse customer;
	private List<CustomerCardResponse> cards;
}

package com.matheus.creditassessor.dtos.request;

import lombok.Data;

@Data
public class CustomerEvaluateDataRequest {

	private String cpf;
	private Long income;
}

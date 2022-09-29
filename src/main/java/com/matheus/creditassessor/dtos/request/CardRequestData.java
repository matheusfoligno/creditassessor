package com.matheus.creditassessor.dtos.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardRequestData {

	private Long id;
	private String cpf;
	private String address;
	private BigDecimal limitReleased;
}

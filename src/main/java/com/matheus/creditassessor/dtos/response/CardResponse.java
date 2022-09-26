package com.matheus.creditassessor.dtos.response;

import java.math.BigDecimal;

import com.matheus.creditassessor.enums.CardFlag;

import lombok.Data;

@Data
public class CardResponse {

	private Long id;
	private String name;
	private CardFlag cardFlag;
	private BigDecimal basicLimit;
}

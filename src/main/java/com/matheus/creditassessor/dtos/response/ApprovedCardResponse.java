package com.matheus.creditassessor.dtos.response;

import java.math.BigDecimal;

import com.matheus.creditassessor.enums.CardFlag;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApprovedCardResponse {

	private String card;
	private CardFlag cardFlag;
	private BigDecimal limitReleased;
}

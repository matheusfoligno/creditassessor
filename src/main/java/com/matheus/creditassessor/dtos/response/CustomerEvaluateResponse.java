package com.matheus.creditassessor.dtos.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEvaluateResponse {

	private List<ApprovedCardResponse> cards;
}

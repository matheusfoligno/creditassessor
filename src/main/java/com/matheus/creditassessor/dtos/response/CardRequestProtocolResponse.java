package com.matheus.creditassessor.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CardRequestProtocolResponse {

	private String protocol;
}

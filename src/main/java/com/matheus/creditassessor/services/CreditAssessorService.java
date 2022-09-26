package com.matheus.creditassessor.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.matheus.creditassessor.clients.CardResourceClient;
import com.matheus.creditassessor.clients.CustomerResourceClient;
import com.matheus.creditassessor.dtos.response.ApprovedCardResponse;
import com.matheus.creditassessor.dtos.response.CardResponse;
import com.matheus.creditassessor.dtos.response.CustomerCardResponse;
import com.matheus.creditassessor.dtos.response.CustomerDataResponse;
import com.matheus.creditassessor.dtos.response.CustomerEvaluateResponse;
import com.matheus.creditassessor.dtos.response.CustomerSituationResponse;
import com.matheus.creditassessor.exceptions.InternalServerErrorException;
import com.matheus.creditassessor.exceptions.NotFoundException;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

	private static final String CUSTOMER_DATA_NOT_FOUND = "Dados do cliente não encontrados para o CPF informado";
	
	private final CustomerResourceClient customersClient;
	private final CardResourceClient cardsClient;

	public CustomerSituationResponse getCustomerSituation(String cpf)
			throws NotFoundException, InternalServerErrorException {
		try {
			ResponseEntity<CustomerDataResponse> customerResponse = customersClient.getCustomerByCpf(cpf);
			ResponseEntity<List<CustomerCardResponse>> cardsResponse = cardsClient.getCardsByCustomer(cpf);

			return CustomerSituationResponse.builder().customer(customerResponse.getBody())
					.cards(cardsResponse.getBody()).build();
		} catch (FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new NotFoundException(CUSTOMER_DATA_NOT_FOUND);
			}
			throw new InternalServerErrorException(e.getMessage());
		}

	}

	public CustomerEvaluateResponse performCreditAssessment(String cpf, Long income)
			throws NotFoundException, InternalServerErrorException {
		try {
			ResponseEntity<CustomerDataResponse> customerResponse = customersClient.getCustomerByCpf(cpf);
			ResponseEntity<List<CardResponse>> cardsResponse = cardsClient.getCardsByIncome(income);

			List<CardResponse> cards = cardsResponse.getBody();
			var listApprovedCards = cards.stream().map(card -> {
				CustomerDataResponse customerData = customerResponse.getBody();

				BigDecimal age = BigDecimal.valueOf(customerData.getAge());

				var factor = age.divide(BigDecimal.TEN);
				BigDecimal approvedLimit = factor.multiply(card.getBasicLimit());

				return ApprovedCardResponse.builder().card(card.getName()).cardFlag(card.getCardFlag())
						.limitReleased(approvedLimit).build();
			}).collect(Collectors.toList());

			return CustomerEvaluateResponse.builder().cards(listApprovedCards).build();
		} catch (FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new NotFoundException(CUSTOMER_DATA_NOT_FOUND);
			}
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}

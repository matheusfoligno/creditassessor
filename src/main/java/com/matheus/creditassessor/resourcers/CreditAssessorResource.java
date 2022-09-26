package com.matheus.creditassessor.resourcers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.creditassessor.dtos.request.CustomerEvaluateDataRequest;
import com.matheus.creditassessor.dtos.response.CustomerEvaluateResponse;
import com.matheus.creditassessor.dtos.response.CustomerSituationResponse;
import com.matheus.creditassessor.exceptions.InternalServerErrorException;
import com.matheus.creditassessor.exceptions.NotFoundException;
import com.matheus.creditassessor.services.CreditAssessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("credits-assessor")
@RequiredArgsConstructor
public class CreditAssessorResource {

	private final CreditAssessorService creditAssessorService;

	@GetMapping(value = "customer-situation", params = "cpf")
	public ResponseEntity<CustomerSituationResponse> getCustomerSituation(@RequestParam("cpf") String cpf)
			throws NotFoundException, InternalServerErrorException {
		CustomerSituationResponse customerSituation = creditAssessorService.getCustomerSituation(cpf);
		return ResponseEntity.ok(customerSituation);
	}

	@PostMapping
	public ResponseEntity performCreditAssessment(@RequestBody CustomerEvaluateDataRequest request)
			throws NotFoundException, InternalServerErrorException {
		CustomerEvaluateResponse response = creditAssessorService.performCreditAssessment(request.getCpf(),
				request.getIncome());
		return ResponseEntity.ok(response);
	}
}

package com.mert.loanapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.service.LoanApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/loan-applications")
public class LoanApplicationController {

	private final LoanApplicationService loanApplicationService;
	
	public LoanApplicationController(LoanApplicationService loanApplicationService) {
		this.loanApplicationService = loanApplicationService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> create(@Valid @RequestBody CreateLoanApplicationRequest request) {
		loanApplicationService.create(request);
		String response = "Loan application has been created successfully.";
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LoanApplicationDto> getById(@PathVariable("id") String id) {
		LoanApplicationDto response = loanApplicationService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LoanApplicationDto> update(@PathVariable("id") String id, @Valid @RequestBody UpdateLoanApplicationRequest request) {
		LoanApplicationDto response = loanApplicationService.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		loanApplicationService.delete(id);
		String response = "Loan application deleted successfully ";
		return ResponseEntity.ok(response);
	}
}

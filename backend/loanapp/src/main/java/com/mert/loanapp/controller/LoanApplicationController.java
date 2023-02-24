package com.mert.loanapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("api/v1/loan-applications")
@Validated
public class LoanApplicationController {

	private final LoanApplicationService loanApplicationService;
	
	public LoanApplicationController(LoanApplicationService loanApplicationService) {
		this.loanApplicationService = loanApplicationService;
	}
	
	@Operation(summary = "Create a loan application")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> create(@Valid @RequestBody CreateLoanApplicationRequest request) {
		loanApplicationService.create(request);
		String response = "Loan application has been created successfully.";
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Get a loan application by id")
	@GetMapping("/{id}")
	public ResponseEntity<LoanApplicationDto> getById(@PathVariable("id") String id) {
		LoanApplicationDto response = loanApplicationService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Update a loan application by id")
	@PutMapping("/{id}")
	public ResponseEntity<LoanApplicationDto> update(@PathVariable("id") String id, @Valid @RequestBody UpdateLoanApplicationRequest request) {
		LoanApplicationDto response = loanApplicationService.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Delete a loan application by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		loanApplicationService.delete(id);
		String response = "Loan application deleted successfully ";
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Get all loan applications by ID Number and Birth Date")
	@GetMapping("/result")
	public ResponseEntity<List<LoanApplicationDto>> getAllByIdNumberAndBirthDate(@RequestParam("idNumber") @NotBlank(message = "ID Number is required.")
	@Pattern(regexp = "^[0-9]{10}[02468]$", message = "must only be numbers, the length must be 11 and last digit must be even") String idNumber, 
	@RequestParam("birthDate") @NotNull(message = "Birth date is required.") @Past(message = "Birth date cannot be later than today.") LocalDate birthDate) {
		List<LoanApplicationDto> response = loanApplicationService.getAllByIdNumberAndBirthDate(idNumber, birthDate);
		return ResponseEntity.ok(response);
	}
}

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

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> create(@Valid @RequestBody CreateCustomerRequest request) {
		customerService.create(request);
		String response = "Customer has been created successfully.";
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getById(@PathVariable("id") String id) {
		CustomerDto response = customerService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> update(@PathVariable("id") String id, @Valid @RequestBody UpdateCustomerRequest request) {
		CustomerDto response = customerService.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		customerService.delete(id);
		String response = "Customer deleted successfully ";
		return ResponseEntity.ok(response);
	}
}

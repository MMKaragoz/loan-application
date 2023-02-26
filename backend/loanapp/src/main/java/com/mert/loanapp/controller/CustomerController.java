package com.mert.loanapp.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/customers")
@Validated
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	@Operation(summary = "Create a new customer")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, String>> create(@Valid @RequestBody CreateCustomerRequest request) {
		Map<String, String> response = new HashMap<>();
		Customer customer = customerService.create(request);
		response.put("customerId", customer.getId());
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Get a customer by id")
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getById(@PathVariable("id") String id) {
		CustomerDto response = customerService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Update a customer by id")
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> update(@PathVariable("id") String id, @Valid @RequestBody UpdateCustomerRequest request) {
		CustomerDto response = customerService.updateById(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Delete a customer by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		customerService.deleteById(id);
		String response = "Customer deleted successfully ";
		return ResponseEntity.ok(response);
	}
}

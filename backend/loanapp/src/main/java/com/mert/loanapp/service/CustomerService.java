package com.mert.loanapp.service;

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.model.Customer;

import jakarta.validation.Valid;

public interface CustomerService {

	Customer create(@Valid CreateCustomerRequest request);
	
	Customer findById(String id);

	CustomerDto getById(String id);
	
	CustomerDto update(String id, @Valid UpdateCustomerRequest request);

	void delete(String id);

}

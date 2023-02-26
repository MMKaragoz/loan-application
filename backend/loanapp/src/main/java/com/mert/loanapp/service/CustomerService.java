package com.mert.loanapp.service;

import java.time.LocalDate;

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.model.Customer;

import jakarta.validation.Valid;

public interface CustomerService {

	Customer create(@Valid CreateCustomerRequest request);
	
	Customer findById(String id);

	CustomerDto getById(String id);
	
	CustomerDto updateById(String id, @Valid UpdateCustomerRequest request);

	void deleteById(String id);

	CustomerDto getByIdNumberAndBirthDate(String idNumber, LocalDate birthDate);

}

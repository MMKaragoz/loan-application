package com.mert.loanapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.converter.CustomerConverter;
import com.mert.loanapp.entity.Customer;
import com.mert.loanapp.exception.NotFoundException;
import com.mert.loanapp.repository.CustomerRepository;
import com.mert.loanapp.service.CreditScoreService;
import com.mert.loanapp.service.CustomerService;

import jakarta.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerConverter converter;
	private final CustomerRepository customerRepository;
	private final CreditScoreService creditScoreService;
	
	public CustomerServiceImpl(CustomerConverter converter, CustomerRepository customerRepository, CreditScoreService creditScoreService) {
		this.converter = converter;
		this.customerRepository = customerRepository;
		this.creditScoreService = creditScoreService;
	}
	
	@Override
	@Transactional
	public void create(@Valid CreateCustomerRequest request) {
		Customer customer = converter.convertCustomerRequestToCustomer(request);
		customer.setCreditScore(creditScoreService.getRandomCreditScore());
		customerRepository.save(customer);
	}
	
	@Transactional(readOnly = true)
	protected Customer findById(String id) {
		return customerRepository.findById(id)
				.orElseThrow(
						() -> new NotFoundException("Customer could not find by " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerDto getById(String id) {
		Customer customer = findById(id);
		CustomerDto customerDto = converter.convertCustomerToCustomerDto(customer);
		return customerDto;
	}

	@Override
	@Transactional
	public CustomerDto update(String id, @Valid UpdateCustomerRequest request) {
		Customer customer = findById(id);
		customer.setEmail(request.getEmail());
		customer.setMonthlyIncome(request.getMonthlyIncome());
		customer.setPhoneNumber(request.getPhoneNumber());
		
		customer = customerRepository.save(customer);
		CustomerDto updatedCustomerDto = converter.convertCustomerToCustomerDto(customer); 
		return updatedCustomerDto;
	}

	@Override
	@Transactional
	public void delete(String id) {
		findById(id);
		customerRepository.deleteById(id);
	}

}

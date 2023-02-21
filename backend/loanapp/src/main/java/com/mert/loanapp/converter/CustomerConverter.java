package com.mert.loanapp.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;

@Component
public class CustomerConverter {
	
	private final LoanApplicationConverter loanApplicationConverter;
	
	public CustomerConverter(LoanApplicationConverter loanApplicationConverter) {
		this.loanApplicationConverter = loanApplicationConverter;
	}

	
	public Customer convertCustomerRequestToCustomer(CreateCustomerRequest request) {
		Customer customer = new Customer();
		customer.setIdNumber(request.getIdNumber());
		customer.setName(request.getName());
		customer.setSurname(request.getSurname());
		customer.setEmail(request.getEmail());
		customer.setMonthlyIncome(request.getMonthlyIncome());
		customer.setPhoneNumber(request.getPhoneNumber());
		customer.setBirthDate(request.getBirthDate());
		return customer;
	}
	
	public CustomerDto convertCustomerToCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		List<LoanApplication> loanApplications = customer.getLoanApplications();
		List<LoanApplicationDto> loanApplicationsDto = new ArrayList<>();
		
		if (loanApplications != null) {
			loanApplicationsDto = customer.getLoanApplications().stream()
					.map(application -> loanApplicationConverter.convertLoanApplicationToLoanApplicationDto(application)).collect(Collectors.toList());
		} 
		
		customerDto.setCreatedAt(customer.getCreatedAt());
		customerDto.setUpdatedAt(customer.getUpdatedAt());
		customerDto.setIdNumber(customer.getIdNumber());
		customerDto.setName(customer.getName());
		customerDto.setSurname(customer.getSurname());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMonthlyIncome(customer.getMonthlyIncome());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		customerDto.setBirthDate(customer.getBirthDate());
		customerDto.setCreditScore(customer.getCreditScore());
		customerDto.setLoanApplications(loanApplicationsDto); 
		
		return customerDto;
	}
}

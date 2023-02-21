package com.mert.loanapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;

public class UnitTestCustomerSupport {

public static final String USER_API_ENDPOINT = "api/v1/users";
	
	public static Customer generateCustomer(String id) {
		List<LoanApplication> loanApplications = new ArrayList<>();
		
		Customer customer = new Customer();
		customer.setId(id);
		customer.setIdNumber("12345678900");
		customer.setName("name");
		customer.setSurname("surname");
		customer.setEmail("email@email.com");
		customer.setMonthlyIncome(5000);
		customer.setPhoneNumber("1222222221");
		customer.setBirthDate(LocalDate.parse("2023-02-10"));
		customer.setCreditScore(0);
		customer.setLoanApplications(loanApplications);
		
		return customer;		
	}
	
	public static CustomerDto generateUserDto(Customer customer) {
		
		CustomerDto customerDto = new CustomerDto();
				customerDto.setIdNumber(customer.getIdNumber());
				customerDto.setName(customer.getName());
				customerDto.setSurname(customer.getSurname());
				customerDto.setEmail(customer.getEmail());
				customerDto.setMonthlyIncome(customer.getMonthlyIncome());
				customerDto.setPhoneNumber(customer.getPhoneNumber());
				customerDto.setBirthDate(customer.getBirthDate());
				customerDto.setCreditScore(customer.getCreditScore());
				customerDto.setLoanApplications(null);
		
		return customerDto;
	}
	
	public CreateCustomerRequest generateValidCreateCustomerRequest() {
		String idNumber = "12345678900";
		String name = "name";
		String surname = "surname";
		String email = "email@email.com";
		int monthlyIncome = 5000;
		String phoneNumber = "1222222221";
		LocalDate birthDate = LocalDate.parse("2023-02-10");
		
		return new CreateCustomerRequest(idNumber,
				name,
				surname,
				email,
				monthlyIncome,
				phoneNumber,
				birthDate
				);
	}
	
	public UpdateCustomerRequest generateUpdateCustomerRequest() {
		String email = "updated@email.com";
		int monthlyIncome = 9009;
		String phoneNumber = "99999999999";
		
		return new UpdateCustomerRequest(email,
				monthlyIncome, 
				phoneNumber);
		
	}
}

package com.mert.loanapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import com.mert.loanapp.UnitTestCustomerSupport;
import com.mert.loanapp.client.dto.request.CreateCustomerRequest;
import com.mert.loanapp.client.dto.request.UpdateCustomerRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.converter.CustomerConverter;
import com.mert.loanapp.exception.NotFoundException;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.repository.CustomerRepository;
import com.mert.loanapp.service.impl.CustomerServiceImpl;

public class CustomerServiceTest extends UnitTestCustomerSupport{
	
	private CustomerService customerService;
	private CustomerRepository customerRepository;
	private CustomerConverter converter;
	private CreditScoreService creditScoreService;
	
	@BeforeEach
	void setUp() {
		customerRepository = Mockito.mock(CustomerRepository.class);
		converter = Mockito.mock(CustomerConverter.class);
		creditScoreService = Mockito.mock(CreditScoreService.class);
		
		customerService = new CustomerServiceImpl(converter, customerRepository, creditScoreService);
	}
	
	@DisplayName("testCreate should return customer when called.")
	@Test
	void testCreate_shouldReturnCustomer_whenCalled() {
		CreateCustomerRequest request = generateValidCreateCustomerRequest();
		Customer expected = generateCustomer("customer-id");		
		when(converter.convertCustomerRequestToCustomer(request)).thenReturn(expected);
		
		int creditScore = 1000;
		expected.setCreditScore(creditScore);
		when(creditScoreService.getRandomCreditScore()).thenReturn(creditScore);
		when(customerRepository.save(expected)).thenReturn(expected);
		
		Customer actual = customerService.create(request);
		
		assertEquals(expected, actual);
		verify(converter, times(1)).convertCustomerRequestToCustomer(request);
		verify(creditScoreService, times(1)).getRandomCreditScore();
		verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
		
	}
	
	@DisplayName("testFindById should return customer when id exists.")
	@Test
	void testFindById_shouldReturnCustomer_whenIdExists() {
		String customerId = "customer-id";
		Customer expected = generateCustomer(customerId);		
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(expected));
		
		Customer actual = customerService.findById(customerId);
		
		assertEquals(expected, actual);
		verify(customerRepository, times(1)).findById(customerId);
		
	}
	
	@DisplayName("testFindById should throw NotFoundError when id does not exist.")
	@Test
	void testFindById_shouldThrowNotFoundError_whenIdDoesNotExist() {
		String customerId = "customer-id";	
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> customerService.findById(customerId));
		verify(customerRepository, times(1)).findById(Mockito.anyString());
		
	}
	
	@DisplayName("testGetById should return CustomerDto when id exists.")
	@Test
	void testGetById_shouldReturnCustomerDto_WhenIdExists() {
		String customerId = "customer-id";
		Customer customer = generateCustomer(customerId);
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		
		CustomerDto expected = generateCustomerDto(customer);
		when(converter.convertCustomerToCustomerDto(customer)).thenReturn(expected);
		
		CustomerDto actual = customerService.getById(customerId);
		
		assertEquals(expected, actual);
		verify(converter, times(1)).convertCustomerToCustomerDto(customer);
		verify(customerRepository, times(1)).findById(Mockito.anyString());
	}
	
	@DisplayName("testUpdateById should return CustomerDto when id exists.")
	@Test
	void testUpdateById_shouldReturnCustomerDto_whenIdExists() {
		String customerId = "customer-id";
		Customer customer = generateCustomer(customerId);
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		
		UpdateCustomerRequest request = generateUpdateCustomerRequest();
		customer.setEmail(request.getEmail());
		customer.setPhoneNumber(request.getPhoneNumber());
		customer.setMonthlyIncome(request.getMonthlyIncome());
		when(customerRepository.save(customer)).thenReturn(customer);
		
		CustomerDto expected = generateCustomerDto(customer);
		when(converter.convertCustomerToCustomerDto(customer)).thenReturn(expected);
		
		CustomerDto actual = customerService.updateById(customerId, request);
		
		assertEquals(expected, actual);
		verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
		verify(converter, times(1)).convertCustomerToCustomerDto(Mockito.any(Customer.class));
		verify(customerRepository, times(1)).findById(Mockito.anyString());
		
	}
	
	@DisplayName("testDeleteById should return nothing and customer should be deleted when id exists.")
	@Test
	void testDeleteById_shouldReturnNothingAndCustomerShouldBeDeleted_whenIdExists() {
		String customerId = "customer-id";
		Customer customer = generateCustomer(customerId);
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		
		customerService.deleteById(customerId);
		
		verify(customerRepository, times(1)).findById(Mockito.anyString());
		verify(customerRepository, times(1)).deleteById(Mockito.anyString());
	}
	
	@DisplayName("testGetByIdNumberAndBirthDate should return CustomerDto when ID Number and Birth Dat exists")
	@ParameterizedTest
	@CsvSource({"12315070882, 2023-02-12", "12315070880, 2000-02-12", "12315070884, 1993-02-22", "12315070886, 2013-01-12", "12315070888, 2003-02-22"})
	void testGetByIdNumberAndBirthDate_shouldReturnCustomerDto_whenIdNumberAndBirthDateExist(String idNumber, LocalDate birthDate) {
		String customerId = "customer-id";
		Customer customer = generateCustomer(customerId);
		customer.setIdNumber(idNumber);
		customer.setBirthDate(birthDate);
		when(customerRepository.findByIdNumberAndBirthDate(idNumber, birthDate)).thenReturn(Optional.of(customer));
		
		CustomerDto expected = generateCustomerDto(customer);
		when(converter.convertCustomerToCustomerDto(customer)).thenReturn(expected);
		
		CustomerDto actual = customerService.getByIdNumberAndBirthDate(idNumber, birthDate);
		
		assertEquals(expected, actual);
		verify(customerRepository, times(1)).findByIdNumberAndBirthDate(idNumber, birthDate);
		verify(converter, times(1)).convertCustomerToCustomerDto(customer);
	}
	
	@DisplayName("testGetByIdNumberAndBirthDate should return CustomerDto when ID Number and Birth Dat exists")
	@ParameterizedTest
	@CsvSource({"12315070882, 2023-02-12", "12315070880, 2000-02-12", "12315070884, 1993-02-22", "12315070886, 2013-01-12", "12315070888, 2003-02-22"})
	void testGetByIdNumberAndBirthDate_shouldThrowNotFoundException_whenIdNumberAndBirthDateDoNotExist(String idNumber, LocalDate birthDate) {
		String customerId = "customer-id";
		Customer customer = generateCustomer(customerId);

		when(customerRepository.findByIdNumberAndBirthDate(idNumber, birthDate)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> customerService.getByIdNumberAndBirthDate(idNumber, birthDate));
		verify(customerRepository, times(1)).findByIdNumberAndBirthDate(idNumber, birthDate);
		verify(converter, times(0)).convertCustomerToCustomerDto(customer);
	}
	
}

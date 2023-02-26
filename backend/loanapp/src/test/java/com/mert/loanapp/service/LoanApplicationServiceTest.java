package com.mert.loanapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import com.mert.loanapp.UnitTestCustomerSupport;
import com.mert.loanapp.UnitTestLoanApplicationSupport;
import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.converter.LoanApplicationConverter;
import com.mert.loanapp.exception.NotFoundException;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.model.enums.LoanStatus;
import com.mert.loanapp.repository.LoanApplicationRepository;
import com.mert.loanapp.service.impl.LoanApplicationServiceImpl;

public class LoanApplicationServiceTest extends UnitTestLoanApplicationSupport{

	private LoanApplicationRepository loanApplicationRepository;
	private LoanApplicationConverter converter;
	private CustomerService customerService;
	private LoanApplicationEvaluatorService loanApplicationEvaluatorService;
	private SMSService smsService;
	private LoanApplicationService loanApplicationService;
	
	@BeforeEach
	void setUp() {
		loanApplicationRepository = Mockito.mock(LoanApplicationRepository.class);
		converter = Mockito.mock(LoanApplicationConverter.class);
		customerService = Mockito.mock(CustomerService.class);
		loanApplicationEvaluatorService = Mockito.mock(LoanApplicationEvaluatorService.class);
		smsService = Mockito.mock(SMSService.class);
		
		loanApplicationService = new LoanApplicationServiceImpl(loanApplicationRepository, converter, customerService, loanApplicationEvaluatorService, smsService);
	}
	
	@DisplayName("testCreate should return LoanApplication when called.")
	@Test
	void testCreate_shouldReturnLoanApplication_whenCalled() {
		String customerId = "customer-id";
		Customer customer = UnitTestCustomerSupport.generateCustomer(customerId);
		
		CreateLoanApplicationRequest request = generateCreateLoanApplicationRequest(customerId);
		customer.setCreditScore(750);
		customer.setMonthlyIncome(25000);
		when(customerService.findById(customerId)).thenReturn(customer);
		
		String id = "loan-id";
		LoanApplication expected = generateLoanApplication(id, customerId);
		
		expected.setCustomer(customer);
		expected.setCollateral(request.getCollateral());
		expected.setCreditLimitFactor(request.getCreditLimitFactor());
		expected.setDesiredLoanAmount(request.getDesiredLoanAmount());
		
		expected.setStatus(LoanStatus.APPROVED);
		
		when(loanApplicationRepository.save(Mockito.any(LoanApplication.class))).thenReturn(expected);
		
		LoanApplication actual = loanApplicationService.create(request);
		
		assertEquals(expected, actual);
		verify(customerService, times(1)).findById(customerId);
		verify(loanApplicationEvaluatorService, times(1)).evaluateLoanApplication(Mockito.any(LoanApplication.class));
		verify(loanApplicationRepository, times(1)).save(Mockito.any(LoanApplication.class));
		verify(smsService, times(1)).sendResultOfLoanApplication(customer.getFullName(), expected.getStatus(),
				expected.getDesiredLoanAmount(), customer.getPhoneNumber());
	}
	
	@DisplayName("testFindById should return LoanApplication when id exists.")
	@Test
	void testFindById_shouldReturnLoanApplication_whenIdExists() {
		String id = "loan-id";
		String customerId = "customer-id";
		LoanApplication expected = generateLoanApplication(id, customerId);
		when(loanApplicationRepository.findById(id)).thenReturn(Optional.of(expected));
		
		LoanApplication actual = loanApplicationService.findById(id);
		
		assertEquals(expected, actual);
		verify(loanApplicationRepository, times(1)).findById(id);
	}
	
	@DisplayName("testFindById should throw NotFoundError when id does not exist.")
	@Test
	void testFindById_shouldThrowNotFoundError_whenIdDoNotExist() {
		String id = "loan-id";
		when(loanApplicationRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> loanApplicationService.findById(id));
		verify(loanApplicationRepository, times(1)).findById(id);
	}
	
	@DisplayName("testGetById should return LoanApplicationDto when id exists.")
	@Test
	void testGetById_shouldReturnLoanApplicationDto_whenIdExists() {
		String id = "loan-id";
		String customerId = "customer-id";
		LoanApplication loanApplication = generateLoanApplication(id, customerId);
		when(loanApplicationRepository.findById(id)).thenReturn(Optional.of(loanApplication));
		
		LoanApplicationDto expected = generateLoanApplicationDto(loanApplication);
		when(converter.convertLoanApplicationToLoanApplicationDto(loanApplication)).thenReturn(expected);
		
		LoanApplicationDto actual = loanApplicationService.getById(id);
		
		assertEquals(expected, actual);
		verify(converter, times(1)).convertLoanApplicationToLoanApplicationDto(loanApplication);
		verify(loanApplicationRepository, times(1)).findById(Mockito.anyString());
	}
	
	@DisplayName("testUpdateById should return LoanApplicationDto when id exists")
	@Test
	void testUpdateById_shouldReturnLoanApplicationDto_whenIdExists() {
		String id = "loan-id";
		String customerId = "customer-id";
		LoanApplication loanApplication = generateLoanApplication(id, customerId);
		when(loanApplicationRepository.findById(id)).thenReturn(Optional.of(loanApplication));
		
		UpdateLoanApplicationRequest request = generateUpdateLoanApplicationRequest(customerId);
		loanApplication.setCollateral(request.getCollateral());
		loanApplication.setCreditLimitFactor(request.getCreditLimitFactor());
		
		when(loanApplicationRepository.save(loanApplication)).thenReturn(loanApplication);
		
		LoanApplicationDto expected = generateLoanApplicationDto(loanApplication);
		when(converter.convertLoanApplicationToLoanApplicationDto(loanApplication)).thenReturn(expected);
		
		LoanApplicationDto actual = loanApplicationService.updateById(id, request);
		
		assertEquals(expected, actual);
		verify(loanApplicationRepository, times(1)).save(Mockito.any(LoanApplication.class));
		verify(converter, times(1)).convertLoanApplicationToLoanApplicationDto(Mockito.any(LoanApplication.class));
	}
 	
	@DisplayName("testDeleteById should return nothing and loan application should be deleted when id exists")
	@Test
	void testDeleteById_shouldReturnNothingAndLoanApplicationShouldBeDeleted_whenIdExists() {
		String id = "loan-id";
		String customerId = "customer-id";
		LoanApplication loanApplication = generateLoanApplication(id, customerId);
		when(loanApplicationRepository.findById(id)).thenReturn(Optional.of(loanApplication));
		
		loanApplicationService.deleteById(id);
		
		verify(loanApplicationRepository, times(1)).deleteById(Mockito.anyString());
		verify(loanApplicationRepository, times(1)).findById(Mockito.anyString());
	}
	
	@DisplayName("testGetByIdNumberAndBirthDate should return List<LoanApplicationDto> when ID Number and Birth Dat exists")
	@ParameterizedTest
	@CsvSource({"12315070882, 2023-02-12", "12315070880, 2000-02-12", "12315070884, 1993-02-22", "12315070886, 2013-01-12", "12315070888, 2003-02-22"})
	void testGetByIdNumberAndBirthDate_shouldThrowNotFoundException_whenIdNumberAndBirthDateDoNotExist(String idNumber, LocalDate birthDate) {
		String id = "loan-id";
		String id2 = "load-id2";
		String customerId = "customer-id";
		Customer customer = UnitTestCustomerSupport.generateCustomer(customerId);
		customer.setBirthDate(birthDate);
		customer.setIdNumber(idNumber);
		LoanApplication loanApplication = generateLoanApplication(id, customerId);
		LoanApplication loanApplication1 = generateLoanApplication(id2, customerId);
		customer.setLoanApplications(List.of(loanApplication,loanApplication1));
		
		CustomerDto customerDto = UnitTestCustomerSupport.generateCustomerDto(customer);
		when(customerService.getByIdNumberAndBirthDate(idNumber, birthDate)).thenReturn(customerDto);
	
		List<LoanApplicationDto> expected = customerDto.getLoanApplications();
		
		List<LoanApplicationDto> actual = loanApplicationService.getAllByIdNumberAndBirthDate(idNumber, birthDate);
		
		assertEquals(expected, actual);
		verify(customerService, times(1)).getByIdNumberAndBirthDate(idNumber, birthDate);
	}
	
	@AfterEach
	void tearDown() {

	}
}

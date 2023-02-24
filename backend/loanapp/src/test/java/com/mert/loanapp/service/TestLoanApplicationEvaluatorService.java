package com.mert.loanapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.mert.loanapp.UnitTestLoanApplicationSupport;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.model.enums.LoanStatus;
import com.mert.loanapp.service.impl.LoanApplicationEvaluatorServiceImpl;

public class TestLoanApplicationEvaluatorService {

	private LoanApplicationEvaluatorService loanApplicationEvalutorService;
	
	@BeforeEach
	void setUp() {
		loanApplicationEvalutorService = new LoanApplicationEvaluatorServiceImpl();
	}
	
	@DisplayName("testEvaluateLoanApplication should update loan status to DECLINED when credit score less than 500")
	@ParameterizedTest
	@ValueSource(ints = {1, 100, 255, 368, 499})
	void testEvaluateLoanApplication_shouldUpdateLoanStatusToDeclined_whenCreditScoreLessThan500(int creditScore) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		LoanStatus expected = LoanStatus.DECLINED;
		LoanStatus actual = loanApplication.getStatus();
		
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication should update loan status to APPROVED when credit score greater than 500")
	@ParameterizedTest
	@ValueSource(ints = {500, 700, 755, 868, 999})
	void testEvaluateLoanApplication_shouldUpdateLoanStatusToApproved_whenCreditScoreGreaterThan500(int creditScore) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		LoanStatus expected = LoanStatus.APPROVED;
		LoanStatus actual = loanApplication.getStatus();
		
		assertEquals(expected, actual);
		
	}
	
	@DisplayName("testEvaluateLoanApplication should calculate loan amount properly when credit score less than 500")
	@ParameterizedTest
	@ValueSource(ints = {1, 100, 255, 368, 499})
	void testEvaluateLoanApplication_shouldCalculateLoanAmountProperly_whenCreditScoreLessThan500(int creditScore) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		double expected = 0;
		double actual = loanApplication.getMaxLoanAmount();
		
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication should calculate loan amount properly when credit score between 500 and 1000 AND monthly income less than 5000")
	@ParameterizedTest
	@CsvSource({"750, 4000", "600, 4500", "900, 3500", "700, 3800", "500, 4900"})
	void testEvaluateLoanApplication_shouldCalculateLoanAmountProperly_whenCreditScoreBetween500And1000AndMonthlyIncomeLessThan5000(int creditScore, int monthlyIncome) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		customer.setMonthlyIncome(monthlyIncome);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		
		double expected = 10000 + loanApplication.getCollateral() * 0.1;
		double actual = loanApplication.getMaxLoanAmount();
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication should calculate loan amount properly when credit score between 500 and 1000 AND monthly income between 5000 and 10000")
	@ParameterizedTest
	@CsvSource({"750, 5000", "600, 7500", "900, 8500", "700, 7800", "500, 9900"})
	void testEvaluateLoanApplication_shouldCalculateLoanAmountProperly_whenCreditScoreBetween500And1000AndMonthlyIncomeBetween5000And10000(int creditScore, int monthlyIncome) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		customer.setMonthlyIncome(monthlyIncome);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		
		double expected = 20000 + loanApplication.getCollateral() * 0.2;
		double actual = loanApplication.getMaxLoanAmount();
		
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication should calculate loan amount properly when credit score between 500 and 1000 AND monthly income greater than 10000")
	@ParameterizedTest
	@CsvSource({"750, 25000", "600, 37500", "900, 58500", "700, 27800", "500, 199900"})
	void testEvaluateLoanApplication_shouldCalculateLoanAmountProperly_whenCreditScoreBetween500And1000AndMonthlyIncomeGreaterThan10000(int creditScore, int monthlyIncome) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		customer.setMonthlyIncome(monthlyIncome);
		double creditLimitFactor = loanApplication.getCreditLimitFactor();
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		
		double expected = (customer.getMonthlyIncome() * creditLimitFactor / 2) + loanApplication.getCollateral() * 0.25;
		double actual = loanApplication.getMaxLoanAmount();
		
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication should calculate loan amount properly when credit score between 1000 and 1900 AND monthly income does not matter")
	@ParameterizedTest
	@CsvSource({"1750, 25000", "1600, 37500", "1900, 58500", "1700, 27800", "1500, 199900"})
	void testEvaluateLoanApplication_shouldCalculateLoanAmountProperly_whenCreditBetween1000And1900(int creditScore, int monthlyIncome) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		customer.setMonthlyIncome(monthlyIncome);
		double creditLimitFactor = loanApplication.getCreditLimitFactor();
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		
		double expected = (customer.getMonthlyIncome() * creditLimitFactor) + loanApplication.getCollateral() * 0.5;
		double actual = loanApplication.getMaxLoanAmount();
		
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication update loan status DECLINED when maxAmount < desiredLoanAmount")
	@ParameterizedTest
	@CsvSource({"1750, 2500, 100000", "160, 3750, 1000000", "1900, 5850, 1000000", "1700, 2780, 1000000", "1500, 19900, 1000000"})
	void testEvaluateLoanApplication_shouldUpdateLoanStatusDeclined_whenMaxAmountLessThanDesiredLoanAmount(int creditScore, int monthlyIncome, double desiredLoanAmount) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		customer.setMonthlyIncome(monthlyIncome);
		loanApplication.setDesiredLoanAmount(desiredLoanAmount);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		LoanStatus expected = LoanStatus.DECLINED;
		LoanStatus actual = loanApplication.getStatus();
	
		assertEquals(expected, actual);
	}
	
	@DisplayName("testEvaluateLoanApplication update loan status APPROVED when maxAmount >= desiredLoanAmount")
	@ParameterizedTest
	@CsvSource({"1750, 2500, 5000", "1600, 3750, 5000", "1900, 5850, 5000", "1700, 2780, 5000", "1500, 8990, 5000"})
	void testEvaluateLoanApplication_shouldUpdateLoanStatusApproved_whenMaxAmountEqualToOrGreaterThanDesiredLoanAmount(int creditScore, int monthlyIncome, double desiredLoanAmount) {
		LoanApplication loanApplication = UnitTestLoanApplicationSupport.generateLoanApplication("application-id", "user-id");
		Customer customer = loanApplication.getCustomer();
		customer.setCreditScore(creditScore);
		customer.setMonthlyIncome(monthlyIncome);
		
		loanApplicationEvalutorService.evaluateLoanApplication(loanApplication);
		LoanStatus expected = LoanStatus.APPROVED;
		LoanStatus actual = loanApplication.getStatus();
	
		assertEquals(expected, actual);
	}
	
	@AfterEach
	void tearDown() {
		
	}
}

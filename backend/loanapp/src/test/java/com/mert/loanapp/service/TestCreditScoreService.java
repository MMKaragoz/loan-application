package com.mert.loanapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mert.loanapp.service.impl.CreditScoreServiceImpl;

public class TestCreditScoreService {

	private CreditScoreService creditScoreService;
	
	@BeforeEach
	void setUp() {
		creditScoreService = new CreditScoreServiceImpl();
	}
	
	@DisplayName("test generateRandomCreditScore when called should return an integer value which is between 1 and 1900.")
	@Test
	void testGenerateRandomCreditScore_whenCalled_shouldReturnIntegerValueWhichIsBetween1And1900() {
		int creditScore = creditScoreService.getRandomCreditScore();
		assertTrue(creditScore >= 1 && creditScore <= 1900);
		
	}
	
	@DisplayName("test getRandomCreditScore when called should return an integer value which is between 1 and 1900.")
	@Test
	void testGetRandomCreditScore_whenCalled_shouldReturnIntegerValueWhichIsBetween1And1900() {
		int creditScore = creditScoreService.getRandomCreditScore();
		assertTrue(creditScore >= 1 && creditScore <= 1900);
	}
	
	@AfterEach
	void tearDown() {
		
	}
}

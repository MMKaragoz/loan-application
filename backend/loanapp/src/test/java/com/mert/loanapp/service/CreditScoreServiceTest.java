package com.mert.loanapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mert.loanapp.service.impl.CreditScoreServiceImpl;

public class CreditScoreServiceTest {

	private CreditScoreService creditScoreService;
	
	@BeforeEach
	void setUp() {
		creditScoreService = new CreditScoreServiceImpl();
	}
	
	@DisplayName("test generateRandomCreditScore should return an integer value which is between 1 and 1900 when called")
	@Test
	void testGenerateRandomCreditScore_shouldReturnIntegerValueWhichIsBetween1And1900_whenCalled() {
		int creditScore = creditScoreService.getRandomCreditScore();
		assertTrue(creditScore >= 1 && creditScore <= 1900);
		
	}
	
	@DisplayName("test getRandomCreditScore should return an integer value which is between 1 and 1900 when called")
	@Test
	void testGetRandomCreditScore_shouldReturnIntegerValueWhichIsBetween1And1900_whenCalled() {
		int creditScore = creditScoreService.getRandomCreditScore();
		assertTrue(creditScore >= 1 && creditScore <= 1900);
	}
	
	@AfterEach
	void tearDown() {
		
	}
}

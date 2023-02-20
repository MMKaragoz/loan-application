package com.mert.loanapp.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.mert.loanapp.service.CreditScoreService;

@Service
public class CreditScoreServiceImpl implements CreditScoreService {

	@Override
	public int getRandomCreditScore() {
		
		int creditScore = generateRandomCreditScore();
		return creditScore;
	}
	
	protected int generateRandomCreditScore() {
		
		Random random = new Random();
		int creditScore = random.nextInt(1, 1900);
		return creditScore;
	}
}

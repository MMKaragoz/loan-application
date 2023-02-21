package com.mert.loanapp.service.impl;

import org.springframework.stereotype.Service;

import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.model.enums.LoanStatus;
import com.mert.loanapp.service.LoanApplicationEvaluatorService;

@Service
public class LoanApplicationEvaluatorServiceImpl implements LoanApplicationEvaluatorService {

	private static final int CREDIT_LIMIT_FACTOR = 4;
	
	@Override
	public void evaluateLoanApplication(LoanApplication loanApplication) {
		Customer customer = loanApplication.getCustomer();
		int creditScore = customer.getCreditScore();
		double monthlyIncome = customer.getMonthlyIncome();
		double collateral = loanApplication.getCollateral();
		LoanStatus status = loanApplication.getStatus();
		double loanAmount = 0.0;
		
		if (creditScore < 500) {
			status = LoanStatus.DECLINED;
		} else if(creditScore >= 500 && creditScore < 1000) {
			if (monthlyIncome < 5000) {
				loanAmount = 10000 + collateral * 0.1;
			} else if (monthlyIncome >= 5000 && monthlyIncome < 10000) {
				loanAmount = 20000 + collateral * 0.2;
			} else {
				loanAmount = (monthlyIncome * CREDIT_LIMIT_FACTOR / 2) + collateral * 0.25;
			}
			status = LoanStatus.APPROVED;
		} else {
			loanAmount = (monthlyIncome * CREDIT_LIMIT_FACTOR) + collateral * 0.5;
			status = LoanStatus.APPROVED;
		}
		
		loanApplication.setLoanAmount(loanAmount);
		loanApplication.setStatus(status);

	}

}

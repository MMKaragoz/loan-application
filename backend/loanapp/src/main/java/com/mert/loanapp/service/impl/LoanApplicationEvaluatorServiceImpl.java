package com.mert.loanapp.service.impl;

import org.springframework.stereotype.Service;

import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.model.enums.LoanStatus;
import com.mert.loanapp.service.LoanApplicationEvaluatorService;

@Service
public class LoanApplicationEvaluatorServiceImpl implements LoanApplicationEvaluatorService {
	
	@Override
	public void evaluateLoanApplication(LoanApplication loanApplication) {
		calculateLoanAmount(loanApplication);
		updateLoanStatus(loanApplication);
	}

	private void updateLoanStatus(LoanApplication loanApplication) {
		Customer customer = loanApplication.getCustomer();
		int creditScore = customer.getCreditScore();
		LoanStatus status = loanApplication.getStatus();

		if (creditScore < 500) {
			status = LoanStatus.DECLINED;
		} else {
			status = LoanStatus.APPROVED;
		}

		loanApplication.setStatus(status);
	}

	private void calculateLoanAmount(LoanApplication loanApplication) {
		Customer customer = loanApplication.getCustomer();
		int creditScore = customer.getCreditScore();
		double monthlyIncome = customer.getMonthlyIncome();
		double collateral = loanApplication.getCollateral();
		double loanAmount = 0.0;
		double creditLimitFactor = loanApplication.getCreditLimitFactor();

		if (creditScore >= 500 && creditScore < 1000) {
			if (monthlyIncome < 5000) {
				loanAmount = 10000 + collateral * 0.1;
			} else if (monthlyIncome >= 5000 && monthlyIncome < 10000) {
				loanAmount = 20000 + collateral * 0.2;
			} else {
				loanAmount = (monthlyIncome * creditLimitFactor / 2) + collateral * 0.25;
			}
		} else if (creditScore >= 1000 && creditScore <= 1900) {
			loanAmount = (monthlyIncome * creditLimitFactor) + collateral * 0.5;
		}

		loanApplication.setLoanAmount(loanAmount);
	}
	
}

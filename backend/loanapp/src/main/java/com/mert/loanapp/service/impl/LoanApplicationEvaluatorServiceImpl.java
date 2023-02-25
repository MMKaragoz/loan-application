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
		calculateMaxLoanAmount(loanApplication);
		updateLoanStatus(loanApplication);
	}

	private void updateLoanStatus(LoanApplication loanApplication) {
		Customer customer = loanApplication.getCustomer();
		int creditScore = customer.getCreditScore();
		LoanStatus status = loanApplication.getStatus();
		double maxLoanAmount = loanApplication.getMaxLoanAmount();
		double desiredLoanAmount = loanApplication.getDesiredLoanAmount();
		
		if (creditScore < 500 || !isDesiredLoanAmountOK(maxLoanAmount, desiredLoanAmount)) {
			status = LoanStatus.DECLINED;
		} else if(isDesiredLoanAmountOK(maxLoanAmount, desiredLoanAmount)) {
			status = LoanStatus.APPROVED;
		}
		loanApplication.setStatus(status);
	}

	private void calculateMaxLoanAmount(LoanApplication loanApplication) {
		Customer customer = loanApplication.getCustomer();
		int creditScore = customer.getCreditScore();
		double monthlyIncome = customer.getMonthlyIncome();
		double collateral = loanApplication.getCollateral();
		double maxLoanAmount = 0.0;
		double creditLimitFactor = loanApplication.getCreditLimitFactor();

		if (creditScore >= 500 && creditScore < 1000) {
			if (monthlyIncome < 5000) {
				maxLoanAmount = 10000 + collateral * 0.1;
			} else if (monthlyIncome >= 5000 && monthlyIncome < 10000) {
				maxLoanAmount = 20000 + collateral * 0.2;
			} else {
				maxLoanAmount = (monthlyIncome * creditLimitFactor / 2) + collateral * 0.25;
			}
		} else if (creditScore >= 1000 && creditScore <= 1900) {
			maxLoanAmount = (monthlyIncome * creditLimitFactor) + collateral * 0.5;
		}

		loanApplication.setMaxLoanAmount(maxLoanAmount);
	}
	
	private Boolean isDesiredLoanAmountOK(double maxLoanAmount, double desiredLoanAmount) {
		if (maxLoanAmount >= desiredLoanAmount) {
			return true;
		}
		return false;
	}
}

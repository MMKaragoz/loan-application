package com.mert.loanapp;

import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.model.LoanApplication;

public class UnitTestLoanApplicationSupport {

	public static LoanApplication generateLoanApplication(String id, String userId) {
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setId(id);
		loanApplication.setCustomer(UnitTestCustomerSupport.generateCustomer(userId));
		loanApplication.setLoanAmount(0);
		loanApplication.setCollateral(0);
		loanApplication.setStatus(null);
		return loanApplication;
	}
	
	public static LoanApplicationDto generateLoanApplicationDto(LoanApplication loanApplication) {
		LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
		loanApplicationDto.setCreatedAt(loanApplication.getCreatedAt());
		loanApplicationDto.setUpdatedAt(loanApplication.getUpdatedAt());
		loanApplicationDto.setLoanAmount(loanApplication.getLoanAmount());
		loanApplicationDto.setCollateral(loanApplication.getCollateral());
		loanApplicationDto.setStatus(loanApplication.getStatus());
		return loanApplicationDto;
	}
}

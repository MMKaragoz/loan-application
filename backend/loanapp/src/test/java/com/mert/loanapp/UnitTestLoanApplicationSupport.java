package com.mert.loanapp;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.model.enums.LoanStatus;

public class UnitTestLoanApplicationSupport {

	public static LoanApplication generateLoanApplication(String id, String customerId) {
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setId(id);
		loanApplication.setCustomer(UnitTestCustomerSupport.generateCustomer(customerId));
		loanApplication.setDesiredLoanAmount(0);
		loanApplication.setMaxLoanAmount(0);
		loanApplication.setCollateral(0);
		loanApplication.setStatus(LoanStatus.APPROVED);
		return loanApplication;
	}
	
	public static LoanApplicationDto generateLoanApplicationDto(LoanApplication loanApplication) {
		LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
		loanApplicationDto.setCreatedAt(loanApplication.getCreatedAt());
		loanApplicationDto.setUpdatedAt(loanApplication.getUpdatedAt());
		loanApplicationDto.setDesiredLoanAmount(loanApplication.getDesiredLoanAmount());
		loanApplicationDto.setMaxLoanAmount(loanApplication.getMaxLoanAmount());
		loanApplicationDto.setCollateral(loanApplication.getCollateral());
		loanApplicationDto.setStatus(loanApplication.getStatus());
		return loanApplicationDto;
	}
	
	public CreateLoanApplicationRequest generateCreateLoanApplicationRequest(String customerId) {
		double collateral = 0.0;
		double creditLimitFactor = 4;
		double desiredLoanAmount = 10000;
		
		return new CreateLoanApplicationRequest(customerId, collateral, creditLimitFactor, desiredLoanAmount);
	}
	
	public UpdateLoanApplicationRequest generateUpdateLoanApplicationRequest(String customerId) {
		double collateral = 100.0;
		double creditLimitFactor = 5;
		
		return new UpdateLoanApplicationRequest(collateral, creditLimitFactor);
	}
}

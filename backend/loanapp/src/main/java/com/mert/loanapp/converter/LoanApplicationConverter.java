package com.mert.loanapp.converter;

import org.springframework.stereotype.Component;

import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.model.LoanApplication;

@Component
public class LoanApplicationConverter {

	public LoanApplicationDto convertLoanApplicationToLoanApplicationDto(LoanApplication loanApplication) {
		LoanApplicationDto loanApplicationDto = new LoanApplicationDto();
		loanApplicationDto.setCreatedAt(loanApplication.getCreatedAt());
		loanApplicationDto.setUpdatedAt(loanApplication.getUpdatedAt());
		loanApplicationDto.setMaxLoanAmount(loanApplication.getMaxLoanAmount());
		loanApplicationDto.setDesiredLoanAmount(loanApplication.getDesiredLoanAmount());
		loanApplicationDto.setCollateral(loanApplication.getCollateral());
		loanApplicationDto.setStatus(loanApplication.getStatus());
		loanApplicationDto.setCreditLimitFactor(loanApplication.getCreditLimitFactor());
		return loanApplicationDto;
	}
	

}

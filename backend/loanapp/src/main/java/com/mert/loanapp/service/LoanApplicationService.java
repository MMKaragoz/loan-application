package com.mert.loanapp.service;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.model.LoanApplication;

import jakarta.validation.Valid;

public interface LoanApplicationService {

	void create(@Valid CreateLoanApplicationRequest request);

	LoanApplication findById(String id);
	
	LoanApplicationDto getById(String id);

	LoanApplicationDto update(String id, @Valid UpdateLoanApplicationRequest request);

	void delete(String id);
}

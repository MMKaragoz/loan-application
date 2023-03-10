package com.mert.loanapp.service;

import java.time.LocalDate;
import java.util.List;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.model.LoanApplication;

import jakarta.validation.Valid;

public interface LoanApplicationService {

	LoanApplication create(@Valid CreateLoanApplicationRequest request);

	LoanApplication findById(String id);
	
	LoanApplicationDto getById(String id);

	LoanApplicationDto updateById(String id, @Valid UpdateLoanApplicationRequest request);

	void deleteById(String id);

	List<LoanApplicationDto> getAllByIdNumberAndBirthDate(
			String idNumber,
			LocalDate birthDate);
}

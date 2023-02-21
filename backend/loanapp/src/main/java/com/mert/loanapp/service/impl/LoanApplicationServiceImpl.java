package com.mert.loanapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.converter.LoanApplicationConverter;
import com.mert.loanapp.exception.NotFoundException;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.repository.LoanApplicationRepository;
import com.mert.loanapp.service.CustomerService;
import com.mert.loanapp.service.LoanApplicationEvaluatorService;
import com.mert.loanapp.service.LoanApplicationService;

import jakarta.validation.Valid;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private final LoanApplicationRepository loanApplicationRepository;
	private final LoanApplicationConverter converter;
	private final CustomerService customerService;
	private final LoanApplicationEvaluatorService loanApplicationEvaluatorService;
	
	public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository,
			LoanApplicationConverter converter, CustomerService customerService,
			LoanApplicationEvaluatorService loanApplicationEvaluatorService) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.converter = converter;
		this.customerService = customerService;
		this.loanApplicationEvaluatorService = loanApplicationEvaluatorService;
	}

	@Override
	@Transactional
	public void create(@Valid CreateLoanApplicationRequest request) {
		LoanApplication loanApplication = new LoanApplication();
		Customer customer = customerService.findById(request.getCustomerId());
		loanApplication.setCustomer(customer);
		loanApplication.setCollateral(request.getCollateral());
		
		loanApplicationEvaluatorService.evaluateLoanApplication(loanApplication);
		loanApplicationRepository.save(loanApplication);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public LoanApplication findById(String id) {
		return loanApplicationRepository.findById(id)
				.orElseThrow(
						() -> new NotFoundException("Loan application could not find by " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public LoanApplicationDto getById(String id) {
		LoanApplication loanApplication = findById(id);
		LoanApplicationDto loanApplicationDto = converter.convertLoanApplicationToLoanApplicationDto(loanApplication);
		return loanApplicationDto;
	}

	@Override
	@Transactional
	public LoanApplicationDto update(String id, @Valid UpdateLoanApplicationRequest request) {
		LoanApplication loanApplication = findById(id);
		loanApplication.setCollateral(request.getCollateral());
		
		loanApplication = loanApplicationRepository.save(loanApplication);
		LoanApplicationDto loanApplicationDto = converter.convertLoanApplicationToLoanApplicationDto(loanApplication);
		return loanApplicationDto;
	}

	@Override
	@Transactional
	public void delete(String id) {
		findById(id);
		loanApplicationRepository.deleteById(id);
		
	}

}

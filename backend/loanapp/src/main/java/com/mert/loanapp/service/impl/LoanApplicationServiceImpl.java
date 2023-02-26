package com.mert.loanapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mert.loanapp.client.dto.request.CreateLoanApplicationRequest;
import com.mert.loanapp.client.dto.request.UpdateLoanApplicationRequest;
import com.mert.loanapp.client.dto.response.CustomerDto;
import com.mert.loanapp.client.dto.response.LoanApplicationDto;
import com.mert.loanapp.converter.LoanApplicationConverter;
import com.mert.loanapp.exception.NotFoundException;
import com.mert.loanapp.model.Customer;
import com.mert.loanapp.model.LoanApplication;
import com.mert.loanapp.repository.LoanApplicationRepository;
import com.mert.loanapp.service.CustomerService;
import com.mert.loanapp.service.LoanApplicationEvaluatorService;
import com.mert.loanapp.service.LoanApplicationService;
import com.mert.loanapp.service.SMSService;

import jakarta.validation.Valid;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private final LoanApplicationRepository loanApplicationRepository;
	private final LoanApplicationConverter converter;
	private final CustomerService customerService;
	private final LoanApplicationEvaluatorService loanApplicationEvaluatorService;
	private final SMSService smsService;

	public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository,
			LoanApplicationConverter converter, CustomerService customerService,
			LoanApplicationEvaluatorService loanApplicationEvaluatorService,
			SMSService smsService) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.converter = converter;
		this.customerService = customerService;
		this.loanApplicationEvaluatorService = loanApplicationEvaluatorService;
		this.smsService = smsService;
	}

	@Override
	@Transactional
	public LoanApplication create(@Valid CreateLoanApplicationRequest request) {
		Customer customer = customerService.findById(request.getCustomerId());
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setCustomer(customer);
		loanApplication.setCollateral(request.getCollateral());
		loanApplication.setCreditLimitFactor(request.getCreditLimitFactor());
		loanApplication.setDesiredLoanAmount(request.getDesiredLoanAmount());

		loanApplicationEvaluatorService.evaluateLoanApplication(loanApplication);
		loanApplication = loanApplicationRepository.save(loanApplication);
		smsService.sendResultOfLoanApplication(customer.getFullName(), loanApplication.getStatus(),
				loanApplication.getDesiredLoanAmount(), customer.getPhoneNumber());
		return loanApplication;
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
	public LoanApplicationDto updateById(String id, @Valid UpdateLoanApplicationRequest request) {
		LoanApplication loanApplication = findById(id);
		loanApplication.setCollateral(request.getCollateral());
		loanApplication.setCreditLimitFactor(request.getCreditLimitFactor());

		loanApplication = loanApplicationRepository.save(loanApplication);
		LoanApplicationDto loanApplicationDto = converter.convertLoanApplicationToLoanApplicationDto(loanApplication);
		return loanApplicationDto;
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		findById(id);
		loanApplicationRepository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<LoanApplicationDto> getAllByIdNumberAndBirthDate(String idNumber, LocalDate birthDate) {
		CustomerDto customerDto = customerService.getByIdNumberAndBirthDate(idNumber, birthDate);
		List<LoanApplicationDto> loanApplications = customerDto.getLoanApplications();

		return loanApplications;
	}

}

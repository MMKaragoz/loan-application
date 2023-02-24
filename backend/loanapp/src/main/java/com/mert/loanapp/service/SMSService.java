package com.mert.loanapp.service;

import com.mert.loanapp.model.enums.LoanStatus;

public interface SMSService {

	void sendResultOfLoanApplication(String fullName, LoanStatus status, double desiredLoanAmount, String phoneNumber);

}

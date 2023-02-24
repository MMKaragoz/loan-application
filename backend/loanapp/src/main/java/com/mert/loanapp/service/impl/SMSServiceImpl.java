package com.mert.loanapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mert.loanapp.model.enums.LoanStatus;
import com.mert.loanapp.service.SMSService;

@Service
public class SMSServiceImpl implements SMSService {
	private final Logger logger = LoggerFactory.getLogger(SMSService.class);
	@Override
	public void sendResultOfLoanApplication(String fullName, LoanStatus status, double desiredLoanAmount, String phoneNumber) {
		String message = "Dear " + fullName + ", your loan application of " + desiredLoanAmount + " TL has been ";
		
		if (status == LoanStatus.APPROVED) {
			message += "approved.";
		} else {
			message += "declined.";
		}
		
		logger.info("Message was sended to " + phoneNumber + " and the message was " + message);
	}

}

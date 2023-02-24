package com.mert.loanapp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mert.loanapp.model.enums.LoanStatus;
import com.mert.loanapp.service.impl.SMSServiceImpl;


public class TestSMSService {

	private SMSService smsService;
	@BeforeEach
	void setUp() {
		smsService = new SMSServiceImpl();
	}
	
	@DisplayName("test sendResultOfLoanApplication should send an sms about approved when Loan status is approved ")
	@Test
	void testSendResultOfLoanApplication_shouldSendAnSMSAboutApproved_whenLoanStatusIsApproved() {
		String fullName = "Mert Karagöz";
		LoanStatus status = LoanStatus.APPROVED;
		double desiredLoanAmount = 100000.0;
		String phoneNumber = "1234567890";
		
		smsService.sendResultOfLoanApplication(fullName, status, desiredLoanAmount, phoneNumber);
		
		String expected = "Dear " + fullName + ", your loan application of " + desiredLoanAmount + " TL has been approved";
	
	}
	
	@DisplayName("test sendResultOfLoanApplication should send an sms about declined when Loan status is declined.")
	@Test
	void testSendResultOfLoanApplication_shouldSendAnSMSAboutDeclined_whenLoanStatusIsDeclined() {
		String fullName = "Mert Karagöz";
		LoanStatus status = LoanStatus.DECLINED;
		double desiredLoanAmount = 100000.0;
		String phoneNumber = "1234567890";
		
		smsService.sendResultOfLoanApplication(fullName, status, desiredLoanAmount, phoneNumber);
		
		String expected = "Dear " + fullName + ", your loan application of " + desiredLoanAmount + " TL has been declined.";
	
	}
	
	@AfterEach
	void tearDown() {
		
	}
}

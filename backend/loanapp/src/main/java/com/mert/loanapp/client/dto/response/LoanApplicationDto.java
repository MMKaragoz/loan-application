package com.mert.loanapp.client.dto.response;

import java.util.Date;
import java.util.Objects;

import com.mert.loanapp.model.enums.LoanStatus;

public class LoanApplicationDto extends BaseResponseDto {
	
	private double maxLoanAmount;
	
	private double desiredLoanAmount;
	
	private double collateral;
	
	private LoanStatus status;
	
	private double creditLimitFactor;
	
	public LoanApplicationDto() {
		
	}

	public LoanApplicationDto(Date createdAt, Date updatedAt, double maxLoanAmount, double desiredLoanAmount,
			double collateral, LoanStatus status, double creditLimitFactor) {
		super(createdAt, updatedAt);
		this.maxLoanAmount = maxLoanAmount;
		this.desiredLoanAmount = desiredLoanAmount;
		this.collateral = collateral;
		this.status = status;
		this.creditLimitFactor = creditLimitFactor;
	}

	public double getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public double getDesiredLoanAmount() {
		return desiredLoanAmount;
	}

	public void setDesiredLoanAmount(double desiredLoanAmount) {
		this.desiredLoanAmount = desiredLoanAmount;
	}

	public double getCollateral() {
		return collateral;
	}

	public void setCollateral(double collateral) {
		this.collateral = collateral;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}

	public double getCreditLimitFactor() {
		return creditLimitFactor;
	}

	public void setCreditLimitFactor(double creditLimitFactor) {
		this.creditLimitFactor = creditLimitFactor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(collateral, creditLimitFactor, desiredLoanAmount, maxLoanAmount, status);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanApplicationDto other = (LoanApplicationDto) obj;
		return Double.doubleToLongBits(collateral) == Double.doubleToLongBits(other.collateral)
				&& Double.doubleToLongBits(creditLimitFactor) == Double.doubleToLongBits(other.creditLimitFactor)
				&& Double.doubleToLongBits(desiredLoanAmount) == Double.doubleToLongBits(other.desiredLoanAmount)
				&& Double.doubleToLongBits(maxLoanAmount) == Double.doubleToLongBits(other.maxLoanAmount)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "LoanApplicationDto [maxLoanAmount=" + maxLoanAmount + ", desiredLoanAmount=" + desiredLoanAmount
				+ ", collateral=" + collateral + ", status=" + status + ", creditLimitFactor=" + creditLimitFactor
				+ "]";
	}
	
}

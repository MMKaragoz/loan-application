package com.mert.loanapp.client.dto.request;

import java.util.Objects;

import jakarta.validation.constraints.Min;

public class UpdateLoanApplicationRequest {
	
	@Min(value = 0, message = "Loan amount cannot be negative")
	private double collateral;
	
	@Min(value = 0, message = "Credit Limit factor cannot be negative")
	private double creditLimitFactor = 4;
	
	public UpdateLoanApplicationRequest() {}

	public UpdateLoanApplicationRequest(@Min(value = 0, message = "Loan amount cannot be negative") double collateral,
			@Min(value = 0, message = "Credit Limit factor cannot be negative") double creditLimitFactor) {
		this.collateral = collateral;
		this.creditLimitFactor = creditLimitFactor;
	}

	public double getCollateral() {
		return collateral;
	}

	public void setCollateral(double collateral) {
		this.collateral = collateral;
	}

	public double getCreditLimitFactor() {
		return creditLimitFactor;
	}

	public void setCreditLimitFactor(double creditLimitFactor) {
		this.creditLimitFactor = creditLimitFactor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(collateral, creditLimitFactor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateLoanApplicationRequest other = (UpdateLoanApplicationRequest) obj;
		return Double.doubleToLongBits(collateral) == Double.doubleToLongBits(other.collateral)
				&& Double.doubleToLongBits(creditLimitFactor) == Double.doubleToLongBits(other.creditLimitFactor);
	}

	@Override
	public String toString() {
		return "UpdateLoanApplicationRequest [collateral=" + collateral + ", creditLimitFactor=" + creditLimitFactor
				+ "]";
	}
	
	
	
}

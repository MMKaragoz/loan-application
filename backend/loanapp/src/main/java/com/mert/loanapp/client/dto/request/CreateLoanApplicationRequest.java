package com.mert.loanapp.client.dto.request;

import java.util.Objects;

import jakarta.validation.constraints.Min;

public class CreateLoanApplicationRequest {

	private String customerId;
	
	@Min(value = 0, message = "Loan amount cannot be negative")
	private double collateral;

	public CreateLoanApplicationRequest(String customerId,
			@Min(value = 0, message = "Loan amount cannot be negative") double collateral) {
		this.customerId = customerId;
		this.collateral = collateral;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getCollateral() {
		return collateral;
	}

	public void setCollateral(double collateral) {
		this.collateral = collateral;
	}

	@Override
	public int hashCode() {
		return Objects.hash(collateral, customerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateLoanApplicationRequest other = (CreateLoanApplicationRequest) obj;
		return Double.doubleToLongBits(collateral) == Double.doubleToLongBits(other.collateral)
				&& Objects.equals(customerId, other.customerId);
	}

	@Override
	public String toString() {
		return "CreateLoanApplicationRequest [customerId=" + customerId + ", collateral=" + collateral + "]";
	}
	
}

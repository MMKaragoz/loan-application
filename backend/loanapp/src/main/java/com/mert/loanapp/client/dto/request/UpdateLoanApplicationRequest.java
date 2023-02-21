package com.mert.loanapp.client.dto.request;

import java.util.Objects;

import jakarta.validation.constraints.Min;

public class UpdateLoanApplicationRequest {
	
	@Min(value = 0, message = "Loan amount cannot be negative")
	private double collateral;
	
	public UpdateLoanApplicationRequest() {}

	public UpdateLoanApplicationRequest(@Min(value = 0, message = "Loan amount cannot be negative") double collateral) {
		this.collateral = collateral;
	}

	public double getCollateral() {
		return collateral;
	}

	public void setCollateral(double collateral) {
		this.collateral = collateral;
	}

	@Override
	public int hashCode() {
		return Objects.hash(collateral);
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
		return Double.doubleToLongBits(collateral) == Double.doubleToLongBits(other.collateral);
	}

	@Override
	public String toString() {
		return "UpdateLoanApplicationRequest [collateral=" + collateral + "]";
	}
	
}

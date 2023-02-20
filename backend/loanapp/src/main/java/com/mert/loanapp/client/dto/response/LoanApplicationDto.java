package com.mert.loanapp.client.dto.response;

import java.util.Objects;

import com.mert.loanapp.entity.enums.LoanStatus;

public class LoanApplicationDto extends BaseResponseDto {
	
	private double loanAmount;
	
	private double collateral;
	
	private LoanStatus status;

	public LoanApplicationDto() {
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(collateral, loanAmount, status);
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
				&& Double.doubleToLongBits(loanAmount) == Double.doubleToLongBits(other.loanAmount)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "LoanApplicationDto [loanAmount=" + loanAmount + ", collateral=" + collateral + ", status=" + status
				+ "]";
	}
	
}

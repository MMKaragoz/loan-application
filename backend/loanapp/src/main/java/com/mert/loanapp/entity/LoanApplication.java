package com.mert.loanapp.entity;

import org.hibernate.annotations.GenericGenerator;

import com.mert.loanapp.entity.enums.LoanStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class LoanApplication extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1826018437528649484L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Customer customer;
	
	@Min(value = 0, message = "Loan amount cannot be negative")
	@Column(nullable = false)
	private double loanAmount;
	
	@Min(value = 0, message = "Loan amount cannot be negative")
	private double collateral;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private LoanStatus status = LoanStatus.PENDING;
	
	public LoanApplication() {
		
	}
	
	private LoanApplication(LoanApplicationBuilder builder) {
		this.customer = builder.customer;
		this.loanAmount = builder.loanAmount;
		this.collateral = builder.collateral;
		this.status = builder.status;
	}

	public String getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public String toString() {
		return "LoanApplication [id=" + id + ", customer=" + customer + ", loanAmount=" + loanAmount + ", collateral="
				+ collateral + ", status=" + status + "]";
	}
	
	public static class LoanApplicationBuilder{
		
		private Customer customer;
		
		private double loanAmount;
		
		private double collateral;
		
		private LoanStatus status = LoanStatus.PENDING;
		
		public LoanApplicationBuilder() {
			
		}
		
		public LoanApplicationBuilder customer(Customer customer) {
			this.customer = customer;
			return this;
		}
		
		public LoanApplicationBuilder loanAmount(double loanAmount) {
			this.loanAmount = loanAmount;
			return this;
		}
		
		public LoanApplicationBuilder collateral(double collateral) {
			this.collateral = collateral;
			return this;
		}
		
		public LoanApplicationBuilder status(LoanStatus status) {
			this.status = status;
			return this;
		}
		
		public LoanApplication build() {
			return new LoanApplication(this);
		}
	}
	
}

package com.mert.loanapp.model;

import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mert.loanapp.model.enums.LoanStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "loan_application")
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
	@OnDelete(action = OnDeleteAction.CASCADE)
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
	
	@Min(value = 0, message = "Credit Limit factor cannot be negative")
	private double creditLimitFactor = 4;

	public LoanApplication() {
		
	}
	
	public LoanApplication(Date createdAt, Date updatedAt, String id, Customer customer,
			@Min(value = 0, message = "Loan amount cannot be negative") double loanAmount,
			@Min(value = 0, message = "Loan amount cannot be negative") double collateral, LoanStatus status) {
		super(createdAt, updatedAt);
		this.id = id;
		this.customer = customer;
		this.loanAmount = loanAmount;
		this.collateral = collateral;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer user) {
		this.customer = user;
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
		result = prime * result + Objects.hash(collateral, creditLimitFactor, customer, id, loanAmount, status);
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
		LoanApplication other = (LoanApplication) obj;
		return Double.doubleToLongBits(collateral) == Double.doubleToLongBits(other.collateral)
				&& Double.doubleToLongBits(creditLimitFactor) == Double.doubleToLongBits(other.creditLimitFactor)
				&& Objects.equals(customer, other.customer) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(loanAmount) == Double.doubleToLongBits(other.loanAmount)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "LoanApplication [id=" + id + ", customer=" + customer + ", loanAmount=" + loanAmount + ", collateral="
				+ collateral + ", status=" + status + ", creditLimitFactor=" + creditLimitFactor + "]";
	}
	
}

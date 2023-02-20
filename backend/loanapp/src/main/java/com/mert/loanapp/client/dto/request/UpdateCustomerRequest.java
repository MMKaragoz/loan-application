package com.mert.loanapp.client.dto.request;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateCustomerRequest {

	@Column(name = "email", unique = true, nullable = false)
    @Email(message = "Please enter valid email format")
    private String email;
	
	@NotNull(message = "Montly income is required.")
	@Min(value = 0, message = "Monthly income cannot be negative")
	private double monthlyIncome;
	
	@NotBlank(message = "Phone number is required.")
	@Pattern(regexp = "^[0-9]{10}", message = "length must 10 digits")
	@Column(unique = true)
	private String phoneNumber;

	public UpdateCustomerRequest(@Email(message = "Please enter valid email format") String email,
			@NotNull(message = "Montly income is required.") @Min(value = 0, message = "Monthly income cannot be negative") double monthlyIncome,
			@NotBlank(message = "Phone number is required.") @Pattern(regexp = "^[0-9]{10}", message = "length must 10 digits") String phoneNumber) {
		super();
		this.email = email;
		this.monthlyIncome = monthlyIncome;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, monthlyIncome, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateCustomerRequest other = (UpdateCustomerRequest) obj;
		return Objects.equals(email, other.email)
				&& Double.doubleToLongBits(monthlyIncome) == Double.doubleToLongBits(other.monthlyIncome)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "UpdateCustomerRequest [email=" + email + ", monthlyIncome=" + monthlyIncome + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
}

package com.mert.loanapp.client.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerDto extends BaseResponseDto {

	private String idNumber;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private double monthlyIncome;
	
	private String phoneNumber;
	
	private LocalDate birthDate;
	
	private int creditScore;
	
	private List<LoanApplicationDto> loanApplications;

	public CustomerDto() {}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public List<LoanApplicationDto> getLoanApplications() {
		return loanApplications;
	}

	public void setLoanApplications(List<LoanApplicationDto> loanApplications) {
		this.loanApplications = loanApplications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(birthDate, creditScore, email, idNumber, loanApplications, monthlyIncome,
				name, phoneNumber, surname);
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
		CustomerDto other = (CustomerDto) obj;
		return Objects.equals(birthDate, other.birthDate) && creditScore == other.creditScore
				&& Objects.equals(email, other.email) && Objects.equals(idNumber, other.idNumber)
				&& Objects.equals(loanApplications, other.loanApplications)
				&& Double.doubleToLongBits(monthlyIncome) == Double.doubleToLongBits(other.monthlyIncome)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "CustomerDto [idNumber=" + idNumber + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", monthlyIncome=" + monthlyIncome + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate
				+ ", creditScore=" + creditScore + ", loanApplications=" + loanApplications + "]";
	}
	
}

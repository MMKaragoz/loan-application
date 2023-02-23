package com.mert.loanapp.client.dto.request;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateCustomerRequest {
	
	@NotBlank(message = "ID Number is required.")
	@Pattern(regexp = "^[0-9]{10}[02468]$", message = "Id Number must only be numbers, the length must be 11 and last digit must be even")
	@Column(unique = true)
	private String idNumber;
	
	@NotBlank(message = "Name is required.")
	@Size(min = 2, max = 100)
	private String name;
	
	@NotBlank(message = "Surname is required.")
	@Size(min = 2, max = 100)
	private String surname;
	
	@Column(name = "email", unique = true, nullable = false)
    @Email(message = "Please enter valid email format")
    // @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;
	
	@NotNull(message = "Montly income is required.")
	@Min(value = 0, message = "Monthly income cannot be negative")
	private double monthlyIncome;
	
	@NotBlank(message = "Phone number is required.")
	@Pattern(regexp = "^[0-9]{10}", message = "length must 10 digits")
	@Column(unique = true)
	private String phoneNumber;
	
	@NotNull(message = "Birth date is required.")
	@Past(message = "Birth date cannot be later than today.")
	private LocalDate birthDate;

	public CreateCustomerRequest(
			@NotBlank(message = "ID Number is required.") @Pattern(regexp = "^[0-9]{10}[02468]$", message = "must only be numbers, the length must be 11 and last digit must be even") String idNumber,
			@NotBlank(message = "Name is required.") @Size(min = 2, max = 100) String name,
			@NotBlank(message = "Surname is required.") @Size(min = 2, max = 100) String surname,
			@Email(message = "Please enter valid email format") String email,
			@NotNull(message = "Montly income is required.") @Min(value = 0, message = "Monthly income cannot be negative") double monthlyIncome,
			@NotBlank(message = "Phone number is required.") @Pattern(regexp = "^[0-9]{10}", message = "length must 10 digits") String phoneNumber,
			@NotNull(message = "Birth date is required.") @Past(message = "Birth date cannot be later than today.") LocalDate birthDate) {
		super();
		this.idNumber = idNumber;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.monthlyIncome = monthlyIncome;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, email, idNumber, monthlyIncome, name, phoneNumber, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateCustomerRequest other = (CreateCustomerRequest) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
				&& Objects.equals(idNumber, other.idNumber)
				&& Double.doubleToLongBits(monthlyIncome) == Double.doubleToLongBits(other.monthlyIncome)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "CreateCustomerRequest [idNumber=" + idNumber + ", name=" + name + ", surname=" + surname + ", email="
				+ email + ", monthlyIncome=" + monthlyIncome + ", phoneNumber=" + phoneNumber + ", birthDate="
				+ birthDate + "]";
	}

}

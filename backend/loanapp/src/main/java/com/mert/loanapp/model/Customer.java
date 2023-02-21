package com.mert.loanapp.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1587869248109644529L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@NotBlank(message = "ID Number is required.")
	@Pattern(regexp = "^[0-9]{10}[02468]$", message = "Id number must only be numbers, the length must be 11 and last digit must be even")
	@Column(unique = true)
	private String idNumber;
	
	@NotBlank(message = "Name is required.")
	@Size(min = 2, max = 100)
	private String name;
	
	@NotBlank(message = "Surname is required.")
	@Size(min = 2, max = 100)
	private String surname;
	
	@Formula("CONCAT_WS( ' ', name, surname ) " )
    private String fullName;
	
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
	
	@NotNull(message = "Birth date is required.")
	@Past(message = "Birth date cannot be later than today.")
	private LocalDate birthDate;
	
	@Min(value = 0, message = "Credit score cannot be negative.")
	@Max(value = 1900, message = "Credit score cannot be greater than 1900.")
	private int creditScore;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<LoanApplication> loanApplications;
	
	public Customer() {
		
	}

	public Customer(Date createdAt, Date updatedAt,
			@NotBlank(message = "ID Number is required.") @Pattern(regexp = "^[0-9]{10}[02468]$", message = "must only be numbers, the length must be 11 and last digit must be even") String idNumber,
			@NotBlank(message = "Name is required.") @Size(min = 2, max = 100) String name,
			@NotBlank(message = "Surname is required.") @Size(min = 2, max = 100) String surname,
			@Email(message = "Please enter valid email format") String email,
			@NotNull(message = "Montly income is required.") @Min(value = 0, message = "Monthly income cannot be negative") double monthlyIncome,
			@NotBlank(message = "Phone number is required.") @Pattern(regexp = "^[0-9]{10}", message = "length must 10 digits") String phoneNumber,
			@NotNull(message = "Birth date is required.") @Past(message = "Birth date cannot be later than today.") LocalDate birthDate,
			int creditScore, List<LoanApplication> loanApplications) {
		super(createdAt, updatedAt);
		this.idNumber = idNumber;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.monthlyIncome = monthlyIncome;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.creditScore = creditScore;
		this.loanApplications = loanApplications;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public List<LoanApplication> getLoanApplications() {
		return loanApplications;
	}

	public void setLoanApplications(List<LoanApplication> loanApplications) {
		this.loanApplications = loanApplications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(birthDate, creditScore, email, fullName, id, idNumber, loanApplications,
				monthlyIncome, name, phoneNumber, surname);
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
		Customer other = (Customer) obj;
		return Objects.equals(birthDate, other.birthDate) && creditScore == other.creditScore
				&& Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(idNumber, other.idNumber)
				&& Objects.equals(loanApplications, other.loanApplications)
				&& Double.doubleToLongBits(monthlyIncome) == Double.doubleToLongBits(other.monthlyIncome)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", idNumber=" + idNumber + ", name=" + name + ", surname=" + surname
				+ ", fullName=" + fullName + ", email=" + email + ", monthlyIncome=" + monthlyIncome + ", phoneNumber="
				+ phoneNumber + ", birthDate=" + birthDate + ", creditScore=" + creditScore + ", loanApplications="
				+ loanApplications + "]";
	}
	
	
	
}

package com.mert.loanapp.entity;

import java.time.LocalDate;
import java.util.List;

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
	@Pattern(regexp = "^[0-9]{10}[02468]$", message = "must only be numbers, the length must be 11 and last digit must be even")
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
	
	private int creditScore;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<LoanApplication> loanApplications;
	
	public Customer() {
		
	}
	
	private Customer(CustomerBuilder builder) {
		this.idNumber = builder.idNumber;
		this.name = builder.name;
		this.surname = builder.surname;
		this.email = builder.email;
		this.monthlyIncome = builder.monthlyIncome;
		this.phoneNumber = builder.phoneNumber;
		this.birthDate = builder.birthDate;
		this.creditScore = builder.creditScore;
		this.loanApplications = builder.loanApplications;
	}

	public String getId() {
		return id;
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
	public String toString() {
		return "Customer [id=" + id + ", idNumber=" + idNumber + ", name=" + name + ", surname=" + surname
				+ ", fullName=" + fullName + ", email=" + email + ", monthlyIncome=" + monthlyIncome + ", phoneNumber="
				+ phoneNumber + ", birthDate=" + birthDate + ", creditScore=" + creditScore + ", loanApplications="
				+ loanApplications + "]";
	}

	public static class CustomerBuilder{

		private String idNumber;

		private String name;

		private String surname;

	    private String email;
		
		private double monthlyIncome;
		
		private String phoneNumber;
		
		private LocalDate birthDate;
		
		private int creditScore;
		
		private List<LoanApplication> loanApplications;

		public CustomerBuilder() {
			
		}
		
		public CustomerBuilder idNumber(String idNumber) {
			this.idNumber = idNumber;
			return this;
		}
		
		public CustomerBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public CustomerBuilder surname(String surname) {
			this.surname = surname;
			return this;
		}
		
		public CustomerBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public CustomerBuilder monthlyIncome(double monthlyIncome) {
			this.monthlyIncome = monthlyIncome;
			return this;
		}
		
		public CustomerBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public CustomerBuilder birthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}
		
		public CustomerBuilder creditScore(int creditScore) {
			this.creditScore = creditScore;
			return this;
		}
		
		public CustomerBuilder loanApplications(List<LoanApplication> loanApplications) {
			this.loanApplications = loanApplications;
			return this;
		}
		
		public Customer build() {
			return new Customer(this);
		}
	}

	
	
	
}

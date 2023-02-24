package com.mert.loanapp.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.loanapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Optional<Customer> findByIdNumberAndBirthDate(String idNumber, LocalDate birthDate);

}

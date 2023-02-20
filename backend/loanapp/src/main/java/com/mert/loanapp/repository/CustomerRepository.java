package com.mert.loanapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.loanapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}

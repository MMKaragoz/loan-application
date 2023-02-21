package com.mert.loanapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.loanapp.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {

}

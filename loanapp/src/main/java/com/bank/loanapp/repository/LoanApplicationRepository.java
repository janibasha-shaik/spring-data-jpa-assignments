package com.bank.loanapp.repository;

import com.bank.loanapp.entity.LoanApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {
    Optional<LoanApplication> findByAppNumber(String appNumber);
}

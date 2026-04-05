package com.bank.loanapp.repository;

import com.bank.loanapp.entity.LoanDecision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDecisionRepository extends JpaRepository<LoanDecision, Long> {
}

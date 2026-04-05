package com.bank.loanapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="loan_decision")
public class LoanDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long decisionId;
    String LoanDecision;
    @OneToOne
    @JoinColumn(name="app_id",unique = true)
    LoanApplication loanApplication;
    private LocalDateTime decidedAt;

    public LocalDateTime getDecidedAt() {
        return decidedAt;
    }

    public void setDecidedAt(LocalDateTime decidedAt) {
        this.decidedAt = decidedAt;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public String getLoanDecision() {
        return LoanDecision;
    }

    public void setLoanDecision(String loanDecision) {
        LoanDecision = loanDecision;
    }
}
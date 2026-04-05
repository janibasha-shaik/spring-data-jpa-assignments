package com.bank.loanapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="loan_app")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Long getId() {
        return id;
    }



    @Column(unique=true)
    String appNumber;
     String applicantName;
     String productType;

    Double requestedAmount;
    String status;
    LocalDateTime submittedAt;

    @OneToOne(mappedBy = "loanApplication", cascade=CascadeType.ALL)
    LoanDecision loanDecision;

    public void setId(Long id) {
        this.id = id;
    }

    public LoanDecision getLoanDecision() {
        return loanDecision;
    }

    public void setLoanDecision(LoanDecision loanDecision) {
        this.loanDecision = loanDecision;
    }

    public String getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(String appNumber) {
        this.appNumber = appNumber;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
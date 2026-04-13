package com.JpaAssignments115.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txnId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    private String txnType; // CREDIT / DEBIT

    private Double amount;

    private LocalDateTime txnTime;

    private String narration;

    public Long getTxnId() {
        return txnId;
    }

    public void setTxnId(Long txnId) {
        this.txnId = txnId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(LocalDateTime txnTime) {
        this.txnTime = txnTime;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
}

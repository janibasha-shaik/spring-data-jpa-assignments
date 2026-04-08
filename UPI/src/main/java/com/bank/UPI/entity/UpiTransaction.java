package com.bank.UPI.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class UpiTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long txnId;
    @Column(unique = true)
    private String utr ;
   private String payerVpa;
  // private String payeeVpa;
   private BigDecimal amount;
   private String status;
   private LocalDateTime createdAt;

   @ManyToOne
    @JoinColumn(name = "payee_vpa", referencedColumnName = "payeeVpa")
    private UpiMerchant upiMerchant;

    public Long getTxnId() {
        return txnId;
    }

    public void setTxnId(Long txnId) {
        this.txnId = txnId;
    }

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public String getPayerVpa() {
        return payerVpa;
    }

    public void setPayerVpa(String payerVpa) {
        this.payerVpa = payerVpa;
    }

//    public String getPayeeVpa() {
//        return payeeVpa;
//    }

//    public void setPayeeVpa(String payeeVpa) {
//        this.payeeVpa = payeeVpa;
//    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UpiMerchant getUpiMerchant() {
        return upiMerchant;
    }

    public void setUpiMerchant(UpiMerchant upiMerchant) {
        this.upiMerchant = upiMerchant;
    }
}

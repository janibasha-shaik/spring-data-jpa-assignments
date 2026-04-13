package com.DigitalPayment.PaymentAggregator.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long merchantId;

    @Column(unique = true)
    private String merchantCode;

    private String displayName;

    private String status; // ACTIVE / INACTIVE

    // One merchant -> many settlements
    @OneToMany(mappedBy = "merchant")
    private List<Settlement> settlements;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }
}
package com.DigitalPayment.PaymentAggregator.DTO;

import jakarta.persistence.Id;

import java.math.BigDecimal;

public class SettlementDTO {
    String MerchantCode;

    String batchId;
    BigDecimal amount;
    SettlementDTO(String merchantCode, String batchId, BigDecimal amount) {
        this.MerchantCode = merchantCode;
        this.batchId = batchId;
        this.amount = amount;
    }
    public String getMerchantCode() {
        return MerchantCode;
    }

    public String getBatchId() {
        return batchId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

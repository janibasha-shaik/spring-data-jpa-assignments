package com.Payment.CQueriesJPQL.DTO;

import java.math.BigDecimal;

public class TopMerchantDTO {

    private String merchantName;
    private BigDecimal totalAmount;

    public TopMerchantDTO(String merchantName, BigDecimal totalAmount) {
        this.merchantName = merchantName;
        this.totalAmount = totalAmount;
    }

    public String getMerchantName() { return merchantName; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}

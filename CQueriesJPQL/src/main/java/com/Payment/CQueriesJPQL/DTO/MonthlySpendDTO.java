package com.Payment.CQueriesJPQL.DTO;

import java.math.BigDecimal;

public class MonthlySpendDTO {
    private String cardNumber;
    private BigDecimal totalAmount;

    MonthlySpendDTO(String cardNumber, BigDecimal totalAmount) {
        this.cardNumber = cardNumber;
        this.totalAmount = totalAmount;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}

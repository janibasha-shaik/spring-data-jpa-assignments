package com.DigitalPayment.PaymentAggregator.DTO;

import java.math.BigDecimal;

public class MerchantPendingAmountDTO {
    BigDecimal Sum;
    String MerchantCode;
    MerchantPendingAmountDTO(String MerchantCode,BigDecimal Sum) {
        this.Sum = Sum;
        this.MerchantCode = MerchantCode;
    }

    public BigDecimal getSum() {
        return Sum;
    }



    public String getMerchantCode() {
        return MerchantCode;
    }


}

package com.bank.UPI.repository;

import com.bank.UPI.entity.UpiTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface UpiTransactionRepository extends JpaRepository<UpiTransaction, Long> {
      List<UpiTransaction>  findByPayerVpaLikeAndAmountGreaterThanAndStatusInOrderByCreatedAtDesc(String
                                                                                  pattern, BigDecimal minAmount, List<String> statuses);
       long countByStatus(String status);
       boolean existsByUtr(String utr);
}

package com.bank.LoanAppQuery.repository;

import com.bank.LoanAppQuery.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface LoanApplcationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByStatusAndAmountBetweenOrderBySubmittedDateDesc(String status, BigDecimal min, BigDecimal max);
    List<LoanApplication> findByCustomerProfile_CustomerNameLikeOrderBySubmittedDateDesc(String pattern);
    long countByCustomerProfile_CustomerName(String customerName);
    boolean existsByAppNumber(String appNumber);
}

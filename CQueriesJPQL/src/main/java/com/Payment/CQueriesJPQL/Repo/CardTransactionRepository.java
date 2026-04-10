package com.Payment.CQueriesJPQL.Repo;

import com.Payment.CQueriesJPQL.DTO.MonthlySpendDTO;
import com.Payment.CQueriesJPQL.DTO.TopMerchantDTO;
import com.Payment.CQueriesJPQL.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardTransactionRepository
        extends JpaRepository<CardTransaction, Long> {

    // ✅ Monthly Spend
    @Query("""
        SELECT new com.Payment.CQueriesJPQL.DTO.MonthlySpendDTO(
            c.cardNumber,
            SUM(t.amount)
        )
        FROM CardTransaction t
        JOIN t.card c
        WHERE MONTH(t.txnTime) = :month 
          AND YEAR(t.txnTime) = :year
        GROUP BY c.cardNumber
    """)
    List<MonthlySpendDTO> getMonthlySpend(int month, int year);


    // ✅ Top Merchants
//    @Query("""Select t.merchant,sum(t.amount) from cardtrans t, join t.card
//c griupby c.cardNum ordered by sum(t.amount)
    @Query("""
        SELECT new com.Payment.CQueriesJPQL.DTO.TopMerchantDTO(
            t.merchantName,
            SUM(t.amount)
        )
        FROM CardTransaction t
        JOIN t.card c
        WHERE c.cardNumber = :cardNumber
        GROUP BY t.merchantName
        ORDER BY SUM(t.amount) DESC
    """)
    List<TopMerchantDTO> getTopMerchants(String cardNumber);
}

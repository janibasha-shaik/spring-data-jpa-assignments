package com.DigitalPayment.PaymentAggregator.repo;

import com.DigitalPayment.PaymentAggregator.DTO.MerchantPendingAmountDTO;
import com.DigitalPayment.PaymentAggregator.DTO.SettlementDTO;
import com.DigitalPayment.PaymentAggregator.entity.Settlement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    @Query("""
SELECT new com.DigitalPayment.PaymentAggregator.DTO.SettlementDTO(m.merchantCode, s.batchId, s.amount)
FROM Settlement s
JOIN s.merchant m
WHERE m.status = 'ACTIVE'
AND s.settlementStatus = 'PENDING'
""")
    List<SettlementDTO> findPendingSettlements();

//. Add JPQL @Query to compute total pending amount per merchantCode using SUM(amount) GROUP BY merchantCode.

    @Query("""
SELECT new com.DigitalPayment.PaymentAggregator.DTO.MerchantPendingAmountDTO(m.merchantCode, SUM(s.amount))
FROM Settlement s
JOIN s.merchant m
WHERE s.settlementStatus = 'PENDING'
GROUP BY m.merchantCode
""")
    List<MerchantPendingAmountDTO> findPendingTotals();


//5. Add @Modifying JPQL update query to set settlementStatus="COMPLETED" where batchId=?1. 
@Modifying
@Transactional
@Query(""" 
UPDATE Settlement s SET s.settlementStatus='COMPLETED' where
    s.batchId=:batchId 
""")
    int markBatchCompleted(@Param("batchId") String Id);

}

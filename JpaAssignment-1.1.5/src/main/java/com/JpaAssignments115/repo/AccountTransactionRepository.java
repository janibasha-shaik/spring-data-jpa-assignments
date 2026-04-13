package com.JpaAssignments115.repo;

import com.JpaAssignments115.entity.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    Page<AccountTransaction> findByBankAccount_AccountNumber(String accountNumber, Pageable pageable);

    @Query("""
SELECT t FROM AccountTransaction t 
join BankAccount a
WHERE a.accountNumber=:accountNumber AND t.txnTime BETWEEN :start AND :end
""")
    Page<AccountTransaction> findTransactionsInRange(@Param("accountNumber")String AccountNumber, @Param("start") LocalDateTime start, @Param("end")LocalDateTime end, Pageable pageable);
}

package com.JpaAssignments115.runner;


import com.JpaAssignments115.entity.AccountTransaction;
import com.JpaAssignments115.entity.BankAccount;
import com.JpaAssignments115.repo.AccountTransactionRepository;
import com.JpaAssignments115.repo.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Transactional
@Component
public class Runner implements CommandLineRunner {

    BankAccountRepository bankRepo;
    AccountTransactionRepository accountRepo;
    Runner(BankAccountRepository bankRepo, AccountTransactionRepository accountRepo) {
        this.bankRepo = bankRepo;
        this.accountRepo = accountRepo;
    }
    @Override
    public void run(String... args)  {
        BankAccount acc = new BankAccount();
        acc.setAccountNumber("SB-20001");
        acc.setBalance(10000.0);
        bankRepo.save(acc);

// create 12 transactions
        for (int i = 0; i < 12; i++) {
            AccountTransaction t = new AccountTransaction();
            t.setBankAccount(acc);
            t.setTxnType(i % 2 == 0 ? "DEBIT" : "CREDIT");
            t.setAmount(100.0 * (i + 1));
            t.setTxnTime(LocalDateTime.now().minusDays(i));
            t.setNarration("Txn " + i);

           accountRepo.save(t);
        }
       // Pageable page = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC,"txnTime"));
        Pageable page0 = PageRequest.of(
                0, // page number
                5, // page size
                Sort.by(Sort.Direction.DESC, "txnTime")
        );
        Page<AccountTransaction> p = accountRepo.findByBankAccount_AccountNumber("SB-20001",page0);


            System.out.println("Page="+p.getNumber()+"Page Size= "+ p.getSize()+"TotalElements= "+p.getTotalElements()+"TotalPages="+p.getTotalPages());




        for (AccountTransaction t : p.getContent()) {
            System.out.println("txnType="+" "+t.getTxnType()+" "+t.getAmount()+" " +t.getTxnTime());
        }





    }

}

package com.bank.retailbank.runner;




    import com.bank.retailbank.entity.Customer;
    import com.bank.retailbank.entity.SavingsAccount;
    import com.bank.retailbank.repository.CustomerRepository;
    import com.bank.retailbank.repository.SavingsAccountRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.*;

    @Component
    @Transactional
    public class DataRunner implements CommandLineRunner {

        @Autowired
        private CustomerRepository customerRepo;

        @Autowired
        private SavingsAccountRepository accountRepo;

        @Override
        public void run(String... args) {

            // CREATE
            Customer c = new Customer();
            c.setFullName("Riya Nair");
            c.setMobile("9876543210");
            c.setEmail("riya.nair@bank.com");
            c.setKycStatus("VERIFIED");
            c.setCreatedAt(LocalDateTime.now());

            SavingsAccount a1 = new SavingsAccount();
            a1.setAccountNumber("SB-51001");
            a1.setBalance(25000.0);
            a1.setStatus("ACTIVE");
            a1.setOpenedOn(LocalDate.now());
            a1.setCustomer(c);

            SavingsAccount a2 = new SavingsAccount();
            a2.setAccountNumber("SB-51002");
            a2.setBalance(12000.0);
            a2.setStatus("ACTIVE");
            a2.setOpenedOn(LocalDate.now());
            a2.setCustomer(c);

            c.getAccounts().add(a1);
            c.getAccounts().add(a2);

            customerRepo.save(c);

            System.out.println("CREATE CustomerId=" + c.getCustomerId());

            // READ
            Customer fetched = customerRepo.findById(c.getCustomerId()).get();
            System.out.println("READ Accounts=" + fetched.getAccounts().size());

            // UPDATE
            a2.setBalance(15000.0);
            accountRepo.save(a2);
            System.out.println("UPDATE SB-51002 NewBalance=15000");

            // DELETE
            accountRepo.delete(a1);
            System.out.println("DELETE SB-51001 RemainingAccounts=1");
        }
    }


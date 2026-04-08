package com.bank.UPI.runner;

import com.bank.UPI.entity.UpiMerchant;
import com.bank.UPI.entity.UpiTransaction;
import com.bank.UPI.repository.UpiMerchantRepository;
import com.bank.UPI.repository.UpiTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DataRunner implements CommandLineRunner {

    private UpiTransactionRepository txnRepo;
   private UpiMerchantRepository merchantRepo;
    public DataRunner(UpiMerchantRepository merchantRepo,UpiTransactionRepository txnRepo) {
        this.merchantRepo = merchantRepo;
        this.txnRepo = txnRepo;
    }

    @Override
    public void run(String... args)  {
        UpiMerchant m1 = new UpiMerchant();
        m1.setMerchantName("Amazon");
        m1.setPayeeVpa("amazon@upi");
        m1.setCategory("Ecommerce");

        UpiMerchant m2 = new UpiMerchant();
        m2.setMerchantName("Zomato");
        m2.setPayeeVpa("zomato@upi");
        m2.setCategory("Food Delivery");

        merchantRepo.saveAll(List.of(m1,m2));

        txnRepo.saveAll(List.of(createTxn("UTR-0001", "user1@hdfc", 500, "SUCCESS", m1),
                createTxn("UTR-0002", "user2@hdfc", 1200, "SUCCESS", m2),
                createTxn("UTR-0003", "user3@icici", 800, "FAILED", m1),
                createTxn("UTR-0004", "user4@hdfc", 1500, "PENDING", m1),
                createTxn("UTR-0005", "user5@hdfc", 700, "SUCCESS", m2),
                createTxn("UTR-0006", "user6@hdfc", 2200, "SUCCESS", m1),
                createTxn("UTR-0007", "user7@sbi", 3000, "PENDING", m2),
                createTxn("UTR-0008", "user8@hdfc", 400, "FAILED", m1)

        ));

        runQueries();



    }

    private void runQueries() {
        List<UpiTransaction> result = txnRepo.findByPayerVpaLikeAndAmountGreaterThanAndStatusInOrderByCreatedAtDesc("%@hdfc", BigDecimal.valueOf(1000), List.of("SUCCESS","PENDING"));

        System.out.println("Filtered: ");
        for(UpiTransaction upiTransaction : result){
            System.out.println(upiTransaction.getUtr()+" "+upiTransaction.getAmount()+" "+upiTransaction.getStatus());
        }

        System.out.println("Success Count= "+txnRepo.countByStatus("SUCCESS"));
        System.out.println("Exists(UTR-0004)= "+txnRepo.countByStatus("UTR-0004"));

    }

    public UpiTransaction createTxn(String utr,String payerVpa,double amount, String status, UpiMerchant merchant) {

        UpiTransaction txn = new UpiTransaction();

        txn.setUtr(utr);
        txn.setPayerVpa(payerVpa);
        txn.setAmount(BigDecimal.valueOf(amount)); // convert here
        txn.setStatus(status);
        txn.setcreatedAt(LocalDateTime.now());
        txn.setUpiMerchant(merchant);

        return txn;
    }
}

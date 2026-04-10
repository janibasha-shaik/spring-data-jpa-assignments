package com.Payment.CQueriesJPQL.Runner;

import com.Payment.CQueriesJPQL.DTO.MonthlySpendDTO;
import com.Payment.CQueriesJPQL.DTO.TopMerchantDTO;
import com.Payment.CQueriesJPQL.Repo.CardRepository;
import com.Payment.CQueriesJPQL.Repo.CardTransactionRepository;
import com.Payment.CQueriesJPQL.entity.Card;
import com.Payment.CQueriesJPQL.entity.CardTransaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataRunner implements CommandLineRunner {

    private final CardTransactionRepository txnRepo;
    private final CardRepository cardRepo;

    public DataRunner(CardTransactionRepository txnRepo, CardRepository cardRepo) {
        this.txnRepo = txnRepo;
        this.cardRepo = cardRepo;
    }

    @Override
    public void run(String... args) {

        Card c1 = new Card();
        c1.setCardNumber("CC-90001");
        c1.setCustomerName("Neha Sharma");
        c1.setStatus("ACTIVE");

        Card c2 = new Card();
        c2.setCardNumber("CC-90002");
        c2.setCustomerName("Rahul Kumar");
        c2.setStatus("ACTIVE");

        cardRepo.saveAll(List.of(c1, c2));

        txnRepo.saveAll(List.of(
                txn("Amazon", 5000, c1, 1),
                txn("BigBazaar", 4200, c1, 1),
                txn("IRCTC", 3300, c1, 1),
                txn("Amazon", 2000, c1, 2),

                txn("Flipkart", 3000, c2, 1),
                txn("Zomato", 1400, c2, 1),
                txn("Swiggy", 2000, c2, 2)
        ));

        runQueries();
    }

    private CardTransaction txn(String merchant, double amt, Card card, int month) {
        CardTransaction t = new CardTransaction();
        t.setMerchantName(merchant);
        t.setAmount(BigDecimal.valueOf(amt));
        t.setTxnTime(LocalDateTime.of(2026, month, 10, 10, 0));
        t.setCard(card);
        return t;
    }

    private void runQueries() {

        List<MonthlySpendDTO> monthly =
                txnRepo.getMonthlySpend(1, 2026);

        System.out.println("MonthlySpend:");
        monthly.forEach(m ->
                System.out.println(m.getCardNumber() + ":" + m.getTotalAmount())
        );

        List<TopMerchantDTO> top =
                txnRepo.getTopMerchants("CC-90001");

        System.out.println("TopMerchants:");
        top.stream().limit(3).forEach(t ->
                System.out.println(t.getMerchantName() + ":" + t.getTotalAmount())
        );
    }
}
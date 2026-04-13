package com.DigitalPayment.PaymentAggregator.runner;

import com.DigitalPayment.PaymentAggregator.DTO.MerchantPendingAmountDTO;
import com.DigitalPayment.PaymentAggregator.DTO.SettlementDTO;
import com.DigitalPayment.PaymentAggregator.entity.Merchant;
import com.DigitalPayment.PaymentAggregator.entity.Settlement;
import com.DigitalPayment.PaymentAggregator.repo.MerchantRepository;
import com.DigitalPayment.PaymentAggregator.repo.SettlementRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class DataRunner implements CommandLineRunner {
    MerchantRepository merchantRepo;
    SettlementRepository settlementRepo;
    public DataRunner(MerchantRepository merchantRepo, SettlementRepository settlementRepo) {
        this.merchantRepo = merchantRepo;
        this.settlementRepo = settlementRepo;
    }
    @Override
    public void run(String... args) {
        // --- Create Merchants ---
        Merchant m1 = new Merchant();
        m1.setMerchantCode("MRC-01");
        m1.setDisplayName("Amazon");
        m1.setStatus("ACTIVE");

        Merchant m2 = new Merchant();
        m2.setMerchantCode("MRC-02");
        m2.setDisplayName("Flipkart");
        m2.setStatus("ACTIVE");

        merchantRepo.saveAll(List.of(m1,m2));

        settlementRepo.saveAll(List.of(createSettlements(m1,"BATCH-20260222-01",BigDecimal.valueOf(1800.0),"PENDING",LocalDateTime.now()),
                createSettlements(m2,"BATCH-20260222-02",BigDecimal.valueOf(900),"PENDING",LocalDateTime.now()),
                  createSettlements(m1,"BATCH-OLD-01",BigDecimal.valueOf(500),"COMPLETED",LocalDateTime.now())));

        runQueries();

    }
    public static Settlement createSettlements(Merchant m, String batchId, BigDecimal Amount, String SettlementStatus, LocalDateTime CreatedAt){
        Settlement s = new Settlement();
        s.setMerchant(m);
        s.setBatchId(batchId);
        s.setAmount(Amount);
        s.setSettlementStatus(SettlementStatus);
        s.setCreatedAt(CreatedAt);
        return s;
    }
    public  void runQueries(){
        System.out.println("Pending List:");
        List<SettlementDTO> p = settlementRepo.findPendingSettlements();
        p.forEach(r ->
                System.out.println(r.getMerchantCode()+" "+r.getBatchId()+" "+r.getAmount()));
        System.out.println("\nPending Totals:");
        List<MerchantPendingAmountDTO> result = settlementRepo.findPendingTotals();
        result.forEach(r-> System.out.println(r.getMerchantCode()+" "+r.getSum()));

        // --- UPDATE ---
        settlementRepo.markBatchCompleted("BATCH-20260222-01");

        // --- AFTER UPDATE ---
        System.out.println("\nAfter Update:");
        settlementRepo.findPendingSettlements()
                .forEach(r -> System.out.println(r.getMerchantCode()+" "+r.getBatchId()+" "+r.getAmount()));;
    }
}

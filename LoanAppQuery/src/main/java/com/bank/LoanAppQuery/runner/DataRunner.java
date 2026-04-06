package com.bank.LoanAppQuery.runner;

import com.bank.LoanAppQuery.entity.CustomerProfile;
import com.bank.LoanAppQuery.entity.LoanApplication;
import com.bank.LoanAppQuery.repository.CustomerProfileRepository;
import com.bank.LoanAppQuery.repository.LoanApplcationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Component
@Transactional
public class DataRunner implements CommandLineRunner {

    @Autowired
    LoanApplcationRepository repo;
    @Autowired
    CustomerProfileRepository customerRepo;

    @Override
    public void run(String... args){
        CustomerProfile c1 = new CustomerProfile();
        c1.setCustomerName("Neha Sharma");
        c1.setMobile("9999999991");
        c1.setSegment("GOLD");

        CustomerProfile c2 = new CustomerProfile();
        c2.setCustomerName("Rahul Kumar");
        c2.setMobile("9999999992");
        c2.setSegment("SILVER");

        customerRepo.saveAll(List.of(c1, c2));

        repo.saveAll(List.of(
                createApp("LA-1001", "HOME", 60000, "APPROVED", c1),
                createApp("LA-1002", "CAR", 30000, "PENDING", c1),
                createApp("LA-1003", "HOME", 80000, "APPROVED", c1),
                createApp("LA-1004", "PERSONAL", 250000, "REJECTED", c2),
                createApp("LA-1005", "CAR", 150000, "APPROVED", c1),
                createApp("LA-1006", "HOME", 90000, "APPROVED", c2)
        ));

        runQueries();
    }

    private LoanApplication createApp(String appNo, String type, double amt,
                                      String status, CustomerProfile customer) {
        LoanApplication app = new LoanApplication();
        app.setAppNumber(appNo);
        app.setLoanType(type);
        app.setAmount(BigDecimal.valueOf(amt));
        app.setStatus(status);
        app.setSubmittedDate(LocalDate.now());
        app.setCustomerProfile(customer);
        return app;
    }

    private void runQueries() {

        List<LoanApplication> result =
                repo.findByStatusAndAmountBetweenOrderBySubmittedDateDesc(
                        "APPROVED",
                        BigDecimal.valueOf(50000),
                        BigDecimal.valueOf(200000)
                );

        System.out.println("ApprovedInRange:");
        result.forEach(a ->
                System.out.println(a.getAppNumber() + " " +
                        a.getCustomerProfile().getCustomerName() + " " +
                        a.getAmount())
        );

        List<LoanApplication> likeResult =
                repo.findByCustomerProfile_CustomerNameLikeOrderBySubmittedDateDesc("%Neha%");

        System.out.println("NameLikeResults:");
        likeResult.forEach(a ->
                System.out.println(a.getAppNumber())
        );

        long count =
                repo.countByCustomerProfile_CustomerName("Neha Sharma");

        System.out.println("CountForNehaSharma=" + count);

        boolean exists =
                repo.existsByAppNumber("LA-1003");

        System.out.println("Exists(LA-1003)=" + exists);
    }
    }



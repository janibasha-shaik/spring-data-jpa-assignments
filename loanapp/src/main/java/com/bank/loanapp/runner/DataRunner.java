package com.bank.loanapp.runner;




    import com.bank.loanapp.entity.LoanApplication;
    import com.bank.loanapp.entity.LoanDecision;
    import com.bank.loanapp.repository.LoanApplicationRepository;
    import com.bank.loanapp.repository.LoanDecisionRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.*;

    @Component
    @Transactional
    public class DataRunner implements CommandLineRunner {

        @Autowired
        private LoanApplicationRepository loanAppRepo;

        @Autowired
        private LoanDecisionRepository loanDecisionRepo;

        @Override
        public void run(String... args) {

            // CREATE
            LoanApplication lp = new LoanApplication();
            lp.setAppNumber("LA-3001");
            lp.setApplicantName("Vikram Singh");
            lp.setRequestedAmount(350000.00);
            lp.setStatus("SUBMITTED"); //Remarks=Eligible based on income and score
            lp.setProductType("HOME");
            lp.setSubmittedAt(LocalDateTime.now());
            loanAppRepo.save(lp);

            System.out.println("CREATE LoanId=" + lp.getId()+ "AppNumber="+ lp.getAppNumber() + "Status="+lp.getStatus());


            // READ
            LoanApplication fetched = loanAppRepo.findByAppNumber(lp.getAppNumber()).get();
            System.out.println("AppNumber="+ fetched.getAppNumber()+"Applicant="+fetched.getApplicantName());

            // UPDATE

            fetched.setStatus("UNDER_REVIEW");
            loanAppRepo.save(fetched);

            System.out.println("UPDATE AppNumber="+fetched.getAppNumber()+ " Status="+ fetched.getStatus());

            LoanDecision ld = new LoanDecision();
            ld.setLoanApplication(fetched);
            ld.setLoanDecision("APPROVED");
            ld.setDecidedAt(LocalDateTime.now());
            loanDecisionRepo.save(ld);

            System.out.println("CREATE Decision=" + ld.getDecidedAt() +
                    " DecidedAt=" + ld.getDecidedAt());

            // 🔹 FINAL OUTPUT
            System.out.println("FINAL " + fetched.getAppNumber() +
                    " Status=" + fetched.getStatus() +
                    " Decision=" + ld.getLoanDecision());

        }
    }


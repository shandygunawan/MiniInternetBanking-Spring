package com.minibank.service;

import com.minibank.entity.CreditPayment;
import com.minibank.repository.CreditPaymentRepository;
import com.minibank.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditPaymentService {

    @Autowired
    private CreditPaymentRepository paymentRepo;

    @Autowired
    private UserAccountRepository accountRepo;

    @Transactional
    public void createCreditPayment(CreditPayment creditPayment) {
        paymentRepo.save(creditPayment);
        accountRepo.decreaseBalanceByAccountNumber(creditPayment.getAccountNumber(), creditPayment.getAmount());
    }

    public List<CreditPayment> getCreditPaymentsCurrentMonthDate(String username, String accountNumber) {
        return paymentRepo.getCreditPaymentsCurrentMonthDate(username, accountNumber);
    }
}

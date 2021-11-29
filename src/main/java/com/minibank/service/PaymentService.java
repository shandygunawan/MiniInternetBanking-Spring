package com.minibank.service;

import com.minibank.entity.PaymentPhone;
import com.minibank.entity.User;
import com.minibank.repository.PaymentPhoneRepository;
import com.minibank.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    PaymentPhoneRepository paymentPhoneRepo;

    @Autowired
    UserAccountRepository accountRepo;

    @Transactional
    public void createPaymentPhone(PaymentPhone paymentPhone) {
        paymentPhoneRepo.save(paymentPhone);
        accountRepo.decreaseBalanceByAccountNumber(paymentPhone.getAccountNumber(), paymentPhone.getAmount());
    }
}

package com.minibank.service;

import com.minibank.entity.PurchaseVoucher;
import com.minibank.repository.PurchaseVoucherRepository;
import com.minibank.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseVoucherRepository voucherRepo;

    @Autowired
    UserAccountRepository accountRepo;

    @Transactional
    public void createPurchaseVoucher(PurchaseVoucher purchaseVoucher) {
        voucherRepo.save(purchaseVoucher);
        accountRepo.decreaseBalanceByAccountNumber(purchaseVoucher.getAccountNumber(), PurchaseVoucher.AMOUNT);
    }
}

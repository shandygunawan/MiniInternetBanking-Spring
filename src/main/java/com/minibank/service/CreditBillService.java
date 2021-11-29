package com.minibank.service;

import com.minibank.entity.CreditBill;
import com.minibank.entity.User;
import com.minibank.repository.CreditBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditBillService {

    @Autowired
    CreditBillRepository creditBillRepo;

    public List<CreditBill> getCreditBillsCurrentMonthDate(String username, String useraccount) {
        return creditBillRepo.getCreditBillsCurrentMonthDate(username, useraccount);
    }

    public List<CreditBill> getCreditBillsBeforeCurrentMonthDate(String username, String useraccount) {
        return creditBillRepo.getUserCreditBillsBeforeCurrentMonthDate(username, useraccount);
    }

    public void createCreditBill(CreditBill creditBill) {
        creditBillRepo.save(creditBill);
    }

}

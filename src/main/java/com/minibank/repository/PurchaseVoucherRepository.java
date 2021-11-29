package com.minibank.repository;

import com.minibank.entity.PurchaseVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseVoucherRepository extends JpaRepository<PurchaseVoucher, Integer> {
}

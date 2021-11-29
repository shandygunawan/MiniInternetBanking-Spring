package com.minibank.repository;

import com.minibank.entity.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditPaymentRepository extends JpaRepository<CreditPayment, Integer> {

    @Query("SELECT cp FROM CreditPayment AS cp WHERE username = :username AND account_number = :useraccount AND " +
            "EXTRACT(YEAR FROM cp.date) = EXTRACT(YEAR FROM CURRENT_DATE) AND " +
            "EXTRACT(MONTH FROM cp.date) = EXTRACT(MONTH FROM CURRENT_DATE)")
    public List<CreditPayment> getCreditPaymentsCurrentMonthDate(@Param("username") String username, @Param("useraccount") String useraccount);
}

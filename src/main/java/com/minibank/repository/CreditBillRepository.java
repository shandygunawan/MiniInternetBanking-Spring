package com.minibank.repository;

import com.minibank.entity.CreditBill;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditBillRepository extends JpaRepository<CreditBill, Integer> {

    @Query("SELECT cb FROM CreditBill AS cb WHERE username = :username AND account_number = :useraccount AND " +
            "EXTRACT(YEAR FROM cb.date) = EXTRACT(YEAR FROM CURRENT_DATE) AND " +
            "EXTRACT(MONTH FROM cb.date) = EXTRACT(MONTH FROM CURRENT_DATE)")
    public List<CreditBill> getCreditBillsCurrentMonthDate(@Param("username") String username, @Param("useraccount") String useraccount);

    @Query("SELECT cb FROM CreditBill AS cb WHERE username = :username AND account_number = :useraccount AND ( " +
            "(EXTRACT(YEAR FROM cb.date) < EXTRACT(YEAR FROM CURRENT_DATE)) OR " +
            "(EXTRACT(YEAR FROM cb.date) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(MONTH FROM cb.date) < EXTRACT(MONTH FROM CURRENT_DATE))" +
            ")")
    public List<CreditBill> getUserCreditBillsBeforeCurrentMonthDate(@Param("username") String username, @Param("useraccount") String useraccount);
}

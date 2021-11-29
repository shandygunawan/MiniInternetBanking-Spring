package com.minibank.repository;

import com.minibank.entity.User;
import com.minibank.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    public UserAccount findUserAccountByAccountNumber(String accountNumber);

    public List<UserAccount> findUserAccountsByUserOrderByAccountNumber(User user);

    @Transactional
    @Modifying
    @Query("UPDATE UserAccount SET balance = balance - :amount WHERE accountNumber = :account_number")
    public void decreaseBalanceByAccountNumber(@Param("account_number") String accountNumber, @Param("amount") Integer amount);
}

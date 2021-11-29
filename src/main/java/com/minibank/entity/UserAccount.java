package com.minibank.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = UserAccount.TABLE_NAME)
public class UserAccount {
    public static final String TABLE_NAME = "user_account";

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Integer balance;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "username")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}

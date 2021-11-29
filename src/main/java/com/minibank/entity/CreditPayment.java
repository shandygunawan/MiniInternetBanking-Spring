package com.minibank.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = CreditPayment.TABLE_NAME)
public class CreditPayment {

    public static final String TABLE_NAME = "credit_payment";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "account_number")
    private String accountNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "CreditPayment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", user=" + user +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}

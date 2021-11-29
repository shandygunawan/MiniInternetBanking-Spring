package com.minibank.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = PaymentPhone.TABLE_NAME)
public class PaymentPhone {

    public static final String TABLE_NAME = "payment_phone";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;


    public Integer getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return "PaymentPhone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}

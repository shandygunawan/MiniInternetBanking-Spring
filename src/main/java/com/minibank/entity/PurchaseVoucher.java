package com.minibank.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = PurchaseVoucher.TABLE_NAME)
public class PurchaseVoucher {

    public static final String TABLE_NAME = "purchase_voucher";

    public static final Integer AMOUNT = 50000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "provider")
    private String provider;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "token")
    private String token;

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

    public String getProvider() {
        return provider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getToken() {
        return token;
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

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setToken(String token) {
        this.token = token;
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
        return "PurchaseVoucher{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", user=" + user +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}

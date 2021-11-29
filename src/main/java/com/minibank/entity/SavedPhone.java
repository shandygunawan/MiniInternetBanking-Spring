package com.minibank.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = SavedPhone.TABLE_NAME)
public class SavedPhone {
    public static final String TABLE_NAME = "saved_phone";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "provider")
    private String provider;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
    @JoinColumn(name = "username")
    private User user;

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

    public User getUser() {
        return user;
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
        return "SavedPhone{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", user=" + user +
                '}';
    }
}

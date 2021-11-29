package com.minibank.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = User.TABLE_NAME, schema = "public")
public class User {

    public static final String TABLE_NAME = "users";

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "credit_due_date")
    private Integer creditDueDate;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String role, Integer creditDueDate, Integer enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.creditDueDate = creditDueDate;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public Integer getCreditDueDate() {
        return creditDueDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreditDueDate(Integer creditDueDate) {
        this.creditDueDate = creditDueDate;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

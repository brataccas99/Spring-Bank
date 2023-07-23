package com.springBank.code.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String iban;
    @Column
    private String balance;
    @Column
    private String type;
    @Column
    private String status;
    @Column
    private String creationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<UserTransactions> transactions;

    public Account(Long id, String iban, String balance, String type, String status, String creationDate, User owner, List<UserTransactions> transactions) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.creationDate = creationDate;
        this.owner = owner;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<UserTransactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<UserTransactions> transactions) {
        this.transactions = transactions;
    }
}

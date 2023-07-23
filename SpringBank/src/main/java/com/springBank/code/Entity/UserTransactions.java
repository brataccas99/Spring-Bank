package com.springBank.code.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class UserTransactions {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String date;
    @Column
    private String amount;
    @Column
    private String description;
    @Column
    private String result;
    @Column
    private String user;
    @Column
    private String receiver;
    @Column
    private String sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public UserTransactions(Long id, String date, String amount, String description, String result, String user, String receiver, String sender, Account account) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.result = result;
        this.user = user;
        this.receiver = receiver;
        this.sender = sender;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

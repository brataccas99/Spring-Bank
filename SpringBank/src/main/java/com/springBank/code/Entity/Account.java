package com.springBank.code.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private Users owner;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<UserTransactions> transactions;

}

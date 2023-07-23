package com.springBank.code.Entity;

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
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String email;
    @Column
    private String telefono;
    @Column
    private String taxCode;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Account> accounts;


}

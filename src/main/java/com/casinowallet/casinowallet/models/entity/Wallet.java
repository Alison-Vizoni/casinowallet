package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long balance;

    @ManyToOne
    private Currency baseCurrency;

    @OneToOne
    private Player player;

    @OneToMany(mappedBy = "wallet")
    private Set<Transaction> transactions;
}

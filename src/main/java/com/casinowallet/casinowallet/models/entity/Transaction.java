package com.casinowallet.casinowallet.models.entity;

import com.casinowallet.casinowallet.models.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long usdAmount;

    @Column(nullable = false)
    private Boolean isFree;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private Long gameId;

    @ManyToOne(optional = false)
    private Provider provider;

    @ManyToOne(optional = false)
    private Wallet wallet;
}

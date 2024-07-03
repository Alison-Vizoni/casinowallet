package com.casinowallet.casinowallet.models.entity;

import com.casinowallet.casinowallet.models.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(optional = false)
    private Access access;
}

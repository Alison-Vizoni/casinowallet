package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Double currencyRate;

    @Column(nullable = false)
    private Double currencyRateUsd;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Game game;
}

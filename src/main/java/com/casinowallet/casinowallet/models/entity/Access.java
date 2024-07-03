package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
public class Access implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Double currencyRate;

    @Column(nullable = false)
    private Double currencyRateUsd;

    @Column(nullable = false)
    private String currencyCode;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Game game;

    @OneToMany(mappedBy = "access")
    private List<Transaction> transactions;
}

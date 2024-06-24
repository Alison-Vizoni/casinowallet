package com.casinowallet.casinowallet.models.entity;

import com.casinowallet.casinowallet.models.entity.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private CurrencyType type;
}

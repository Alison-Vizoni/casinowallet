package com.casinowallet.casinowallet.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private String baseCurrency;

    @JsonIgnore
    @OneToOne
    private Player player;

    @JsonIgnore
    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;
}

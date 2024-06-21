package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String gameStrId;

    @Column
    private Provider provider;

    @OneToMany(mappedBy = "game")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "game")
    private List<Access> accesses;
}

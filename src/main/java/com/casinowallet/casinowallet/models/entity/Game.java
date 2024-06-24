package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

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
}

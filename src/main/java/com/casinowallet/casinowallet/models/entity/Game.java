package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String gameStrId;

    @ManyToOne
    private Provider provider;
}

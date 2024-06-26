package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @OneToOne(mappedBy = "player")
    private Wallet wallet;
}

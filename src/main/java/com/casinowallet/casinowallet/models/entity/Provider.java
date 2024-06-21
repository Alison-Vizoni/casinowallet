package com.casinowallet.casinowallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table
@Data
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String strId;

    @Column
    private String name;

    @OneToMany(mappedBy = "provider")
    private Set<Game> games;
}

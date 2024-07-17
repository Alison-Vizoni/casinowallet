package com.casinowallet.casinowallet.models.entity;

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
public class Match implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String externalId;

    @Column(nullable = false)
    private Boolean ended;

    @OneToMany(mappedBy = "match")
    private List<Transaction> transactions;

    @ManyToOne(optional = false)
    private Game game;

    @ManyToOne(optional = false)
    private Access access;
}

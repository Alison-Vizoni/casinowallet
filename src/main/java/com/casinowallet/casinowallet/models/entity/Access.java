package com.casinowallet.casinowallet.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Access implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private Double currencyRate;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private Boolean expired;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Game game;

    @JsonIgnore
    @OneToMany(mappedBy = "access")
    private List<Match> matches;
}

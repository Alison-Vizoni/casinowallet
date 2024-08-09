package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.LastMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LastMatchRepository extends JpaRepository<LastMatch, Long> {
    Optional<LastMatch> findByGameIdCurrencyCodePlayerId(Long gameId, String currencyCode, Long playerId);
}

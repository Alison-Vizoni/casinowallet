package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByExternalId(String externalId);
}

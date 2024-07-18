package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByExternalId(String externalId);

    @Transactional
    @Modifying
    @Query("update Match m set m.ended = true where m.id = ?1")
    void finishMatch(Long id);
}

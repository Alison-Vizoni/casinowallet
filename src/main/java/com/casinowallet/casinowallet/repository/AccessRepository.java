package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    @Transactional
    @Modifying
    @Query("update Access a set a.expired = true where a.player.id = ?1 and a.expired = false and a.expiresAt > ?2")
    void expirePlayerAccess(Long playerId, Instant date);
}

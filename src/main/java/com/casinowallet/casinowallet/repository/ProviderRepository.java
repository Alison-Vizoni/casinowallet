package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByStrId(String strId);
}

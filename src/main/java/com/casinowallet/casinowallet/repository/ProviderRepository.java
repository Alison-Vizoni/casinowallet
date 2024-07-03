package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByStrId(String strId);
}

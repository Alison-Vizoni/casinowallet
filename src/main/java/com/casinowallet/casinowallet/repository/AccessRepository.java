package com.casinowallet.casinowallet.repository;

import com.casinowallet.casinowallet.models.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
}

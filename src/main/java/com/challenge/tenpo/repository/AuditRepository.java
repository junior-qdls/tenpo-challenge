package com.challenge.tenpo.repository;

import com.challenge.tenpo.entity.Audit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditRepository extends JpaRepository<Audit, String> {

    Optional<Audit> findOneByRequestId(String requestId);

    Page<Audit> findAll(Pageable pageable);
}

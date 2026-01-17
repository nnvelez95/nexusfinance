package com.nexusfinance.domain.repository;

import com.nexusfinance.domain.model.Investment;
import com.nexusfinance.domain.model.InvestmentStatus;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio del dominio para Investment.
 */
public interface InvestmentRepository {
    Optional<Investment> findById(Long id);
    List<Investment> findAll();
    List<Investment> findByUserId(Long userId);
    List<Investment> findByProductId(Long productId);
    List<Investment> findByStatus(InvestmentStatus status);
    List<Investment> findActiveByUserId(Long userId);
    Investment save(Investment investment);
    void deleteById(Long id);
    boolean existsById(Long id);
}

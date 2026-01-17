package com.nexusfinance.domain.repository;

import com.nexusfinance.domain.model.InvestmentProduct;
import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio del dominio para InvestmentProduct.
 */
public interface InvestmentProductRepository {
    Optional<InvestmentProduct> findById(Long id);
    List<InvestmentProduct> findAll();
    List<InvestmentProduct> findByStatus(ProductStatus status);
    List<InvestmentProduct> findByRiskLevel(RiskLevel riskLevel);
    List<InvestmentProduct> findAvailableProducts();
    InvestmentProduct save(InvestmentProduct product);
    void deleteById(Long id);
    boolean existsById(Long id);
}

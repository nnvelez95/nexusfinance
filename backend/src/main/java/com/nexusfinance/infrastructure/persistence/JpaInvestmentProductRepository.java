package com.nexusfinance.infrastructure.persistence;

import com.nexusfinance.domain.model.InvestmentProduct;
import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaInvestmentProductRepository extends JpaRepository<InvestmentProduct, Long> {
    
    List<InvestmentProduct> findByStatus(ProductStatus status);
    
    List<InvestmentProduct> findByRiskLevel(RiskLevel riskLevel);
    
    @Query("SELECT p FROM InvestmentProduct p WHERE p.status = 'ACTIVE' " +
           "AND p.currentAmount < p.targetAmount " +
           "AND p.closingDate > :now " +
           "AND p.isActive = true")
    List<InvestmentProduct> findAvailableProducts(LocalDate now);
}

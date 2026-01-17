package com.nexusfinance.infrastructure.persistence;

import com.nexusfinance.domain.model.InvestmentProduct;
import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;
import com.nexusfinance.domain.repository.InvestmentProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class InvestmentProductRepositoryAdapter implements InvestmentProductRepository {

    private final JpaInvestmentProductRepository jpaRepository;

    public InvestmentProductRepositoryAdapter(JpaInvestmentProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<InvestmentProduct> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<InvestmentProduct> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<InvestmentProduct> findByStatus(ProductStatus status) {
        return jpaRepository.findByStatus(status);
    }

    @Override
    public List<InvestmentProduct> findByRiskLevel(RiskLevel riskLevel) {
        return jpaRepository.findByRiskLevel(riskLevel);
    }

    @Override
    public List<InvestmentProduct> findAvailableProducts() {
        return jpaRepository.findAvailableProducts(LocalDate.now());
    }

    @Override
    public InvestmentProduct save(InvestmentProduct product) {
        return jpaRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}

package com.nexusfinance.infrastructure.persistence;

import com.nexusfinance.domain.model.Investment;
import com.nexusfinance.domain.model.InvestmentStatus;
import com.nexusfinance.domain.repository.InvestmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InvestmentRepositoryAdapter implements InvestmentRepository {

    private final JpaInvestmentRepository jpaRepository;

    public InvestmentRepositoryAdapter(JpaInvestmentRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Investment> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Investment> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Investment> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public List<Investment> findByProductId(Long productId) {
        return jpaRepository.findByProductId(productId);
    }

    @Override
    public List<Investment> findByStatus(InvestmentStatus status) {
        return jpaRepository.findByStatus(status);
    }

    @Override
    public List<Investment> findActiveByUserId(Long userId) {
        return jpaRepository.findActiveByUserId(userId);
    }

    @Override
    public Investment save(Investment investment) {
        return jpaRepository.save(investment);
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

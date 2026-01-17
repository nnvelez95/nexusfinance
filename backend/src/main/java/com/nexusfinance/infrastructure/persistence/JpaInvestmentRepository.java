package com.nexusfinance.infrastructure.persistence;

import com.nexusfinance.domain.model.Investment;
import com.nexusfinance.domain.model.InvestmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaInvestmentRepository extends JpaRepository<Investment, Long> {
    
    List<Investment> findByUserId(Long userId);
    
    List<Investment> findByProductId(Long productId);
    
    List<Investment> findByStatus(InvestmentStatus status);
    
    @Query("SELECT i FROM Investment i WHERE i.user.id = :userId AND i.status = 'ACTIVE'")
    List<Investment> findActiveByUserId(Long userId);
}

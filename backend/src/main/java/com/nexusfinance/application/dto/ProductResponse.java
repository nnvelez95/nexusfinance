package com.nexusfinance.application.dto;

import com.nexusfinance.domain.model.InvestmentProduct;
import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String name,
    String description,
    ProductStatus status,
    RiskLevel riskLevel,
    BigDecimal annualReturn,
    BigDecimal minimumInvestment,
    BigDecimal targetAmount,
    BigDecimal currentAmount,
    BigDecimal progressPercentage,
    Integer durationDays,
    LocalDate closingDate,
    Boolean isAvailable,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static ProductResponse fromEntity(InvestmentProduct product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getStatus(),
            product.getRiskLevel(),
            product.getAnnualReturn(),
            product.getMinimumInvestment(),
            product.getTargetAmount(),
            product.getCurrentAmount(),
            product.getProgressPercentage(),
            product.getDurationDays(),
            product.getClosingDate(),
            product.isAvailableForInvestment(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}

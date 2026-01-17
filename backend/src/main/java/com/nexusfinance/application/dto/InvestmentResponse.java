package com.nexusfinance.application.dto;

import com.nexusfinance.domain.model.Investment;
import com.nexusfinance.domain.model.InvestmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record InvestmentResponse(
    Long id,
    Long userId,
    String userEmail,
    Long productId,
    String productName,
    BigDecimal amount,
    BigDecimal expectedReturn,
    BigDecimal actualReturn,
    BigDecimal totalAmount,
    InvestmentStatus status,
    LocalDate startDate,
    LocalDate maturityDate,
    LocalDate completionDate,
    Boolean canBeCancelled,
    LocalDateTime createdAt
) {
    public static InvestmentResponse fromEntity(Investment investment) {
        return new InvestmentResponse(
            investment.getId(),
            investment.getUser().getId(),
            investment.getUser().getEmail(),
            investment.getProduct().getId(),
            investment.getProduct().getName(),
            investment.getAmount(),
            investment.getExpectedReturn(),
            investment.getActualReturn(),
            investment.getTotalAmount(),
            investment.getStatus(),
            investment.getStartDate(),
            investment.getMaturityDate(),
            investment.getCompletionDate(),
            investment.canBeCancelled(),
            investment.getCreatedAt()
        );
    }
}

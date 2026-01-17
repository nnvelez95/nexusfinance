package com.nexusfinance.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateInvestmentRequest(
    @NotNull(message = "Product ID is required")
    Long productId,
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.0", message = "Amount must be greater than 0")
    BigDecimal amount
) {}

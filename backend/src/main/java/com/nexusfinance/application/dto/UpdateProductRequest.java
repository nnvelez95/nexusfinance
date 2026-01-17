package com.nexusfinance.application.dto;

import com.nexusfinance.domain.model.ProductStatus;
import com.nexusfinance.domain.model.RiskLevel;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateProductRequest(
    @Size(max = 200, message = "Name must not exceed 200 characters")
    String name,
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    String description,
    
    ProductStatus status,
    
    RiskLevel riskLevel,
    
    @DecimalMin(value = "0.0", message = "Annual return must be positive")
    @DecimalMax(value = "100.0", message = "Annual return must not exceed 100%")
    BigDecimal annualReturn,
    
    @DecimalMin(value = "1.0", message = "Minimum investment must be greater than 0")
    BigDecimal minimumInvestment,
    
    @DecimalMin(value = "1.0", message = "Target amount must be greater than 0")
    BigDecimal targetAmount,
    
    @Min(value = 1, message = "Duration must be at least 1 day")
    @Max(value = 3650, message = "Duration must not exceed 10 years")
    Integer durationDays,
    
    @Future(message = "Closing date must be in the future")
    LocalDate closingDate
) {}

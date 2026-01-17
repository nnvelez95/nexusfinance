package com.nexusfinance.application.dto;

import com.nexusfinance.domain.model.RiskLevel;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateProductRequest(
    @NotBlank(message = "Product name is required")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    String name,
    
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    String description,
    
    @NotNull(message = "Risk level is required")
    RiskLevel riskLevel,
    
    @NotNull(message = "Annual return is required")
    @DecimalMin(value = "0.0", message = "Annual return must be positive")
    @DecimalMax(value = "100.0", message = "Annual return must not exceed 100%")
    BigDecimal annualReturn,
    
    @NotNull(message = "Minimum investment is required")
    @DecimalMin(value = "1.0", message = "Minimum investment must be greater than 0")
    BigDecimal minimumInvestment,
    
    @NotNull(message = "Target amount is required")
    @DecimalMin(value = "1.0", message = "Target amount must be greater than 0")
    BigDecimal targetAmount,
    
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 day")
    @Max(value = 3650, message = "Duration must not exceed 10 years (3650 days)")
    Integer durationDays,
    
    @NotNull(message = "Closing date is required")
    @Future(message = "Closing date must be in the future")
    LocalDate closingDate
) {}

package com.nexusfinance.domain.model;

/**
 * Estados posibles de una inversión.
 */
public enum InvestmentStatus {
    ACTIVE,      // Inversión activa
    COMPLETED,   // Inversión completada (al vencimiento)
    CANCELLED,   // Inversión cancelada por el usuario
    EXPIRED      // Inversión vencida
}

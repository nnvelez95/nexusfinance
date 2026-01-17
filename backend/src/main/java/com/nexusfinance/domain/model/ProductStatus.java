package com.nexusfinance.domain.model;

/**
 * Estados posibles de un producto de inversión.
 */
public enum ProductStatus {
    ACTIVE,      // Disponible para inversión
    INACTIVE,    // No disponible temporalmente
    SOLD_OUT,    // Agotado
    CLOSED       // Cerrado permanentemente
}
